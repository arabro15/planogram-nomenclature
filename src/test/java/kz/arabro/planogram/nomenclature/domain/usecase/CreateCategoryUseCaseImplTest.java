package kz.arabro.planogram.nomenclature.domain.usecase;

import kz.arabro.planogram.nomenclature.boundary.model.CategoryCreateInfo;
import kz.arabro.planogram.nomenclature.boundary.repository.CategoryRepository;
import kz.arabro.planogram.nomenclature.boundary.usecase.CreateCategoryUseCase;
import kz.arabro.planogram.nomenclature.domain.entity.category.Category;
import kz.arabro.planogram.nomenclature.domain.entity.category.Color;
import kz.arabro.planogram.nomenclature.domain.entity.product.ProductError;
import kz.arabro.planogram.nomenclature.domain.exception.CodedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateCategoryUseCaseImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    private CreateCategoryUseCase createCategoryUseCase;

    @BeforeEach
    void setUp() {
        this.createCategoryUseCase = new CreateCategoryUseCaseImpl(categoryRepository);
    }

    @Test
    void execute_InfoIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> createCategoryUseCase.execute(null));
        assertEquals(UseCaseError.CATEGORY_CREATE_INFO_IS_REQUIRED, ex.getCode());
    }

    @Test
    void execute_InfoNameIsNull_ThrowEx() {
        var color = "GREEN";
        var parentID = "c4edca76-71e1-4c4d-a0d5-670cfa47c179";

        var info = new CategoryCreateInfo(null);
        info.setColor(color);
        info.setParentID(parentID);

        var ex = assertThrows(CodedException.class, () -> createCategoryUseCase.execute(info));
        assertEquals(ProductError.EMPTY_NAME_VALUE, ex.getCode());
    }

    @Test
    void execute_SaveCategoryIsThrowEx_ThrowEx() {
        doThrow(RuntimeException.class).when(categoryRepository).save(any(Category.class));

        var name = "Напитки";
        var color = "GREEN";
        var parentID = "c4edca76-71e1-4c4d-a0d5-670cfa47c179";

        var info = new CategoryCreateInfo(name);
        info.setColor(color);
        info.setParentID(parentID);

        assertThrows(RuntimeException.class, () -> createCategoryUseCase.execute(info));
    }

    @Test
    void execute_InfoColorIsNull_ReturnCategoryWithoutColor() {
        var name = "Напитки";
        var parentID = "c4edca76-71e1-4c4d-a0d5-670cfa47c179";

        var info = new CategoryCreateInfo(name);
        info.setColor(null);
        info.setParentID(parentID);

        var category = createCategoryUseCase.execute(info);
        assertNotNull(category);
        assertEquals(name, category.getName().getValue());
        assertEquals(Color.NONE, category.getColor());
        assertTrue(category.getParentID().isPresent());
        assertEquals(parentID, category.getParentID().get().getValue().toString());
        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    void execute_InfoParentIsNull_ReturnCategoryWithoutParentID() {
        var name = "Напитки";
        var color = "GREEN";

        var info = new CategoryCreateInfo(name);
        info.setColor(color);
        info.setParentID(null);

        var category = createCategoryUseCase.execute(info);
        assertNotNull(category);
        assertEquals(name, category.getName().getValue());
        assertEquals(color, category.getColor().name());
        assertFalse(category.getParentID().isPresent());
        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    void execute_InfoColorAndParentIDIsNull_ReturnCategoryWithoutColorAndParentID() {
        var name = "Напитки";

        var info = new CategoryCreateInfo(name);
        info.setColor(null);
        info.setParentID(null);

        var category = createCategoryUseCase.execute(info);
        assertNotNull(category);
        assertEquals(name, category.getName().getValue());
        assertEquals(Color.NONE, category.getColor());
        assertFalse(category.getParentID().isPresent());
        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    void execute_AllParamsIsValid_ReturnCategoryWithColorAndParentID() {
        var name = "Напитки";
        var color = "GREEN";
        var parentID = "c4edca76-71e1-4c4d-a0d5-670cfa47c179";
        var info = new CategoryCreateInfo(name);
        info.setColor(color);
        info.setParentID(parentID);

        var category = createCategoryUseCase.execute(info);
        assertNotNull(category);
        assertEquals(name, category.getName().getValue());
        assertEquals(color, category.getColor().name());
        assertTrue(category.getParentID().isPresent());
        assertEquals(parentID, category.getParentID().get().getValue().toString());
        verify(categoryRepository, times(1)).save(category);
    }
}
