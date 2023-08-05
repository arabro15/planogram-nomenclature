package kz.arabro.planogram.nomenclature.domain.usecase;

import kz.arabro.planogram.nomenclature.boundary.model.CategoryEditInfo;
import kz.arabro.planogram.nomenclature.boundary.repository.CategoryRepository;
import kz.arabro.planogram.nomenclature.boundary.usecase.UpdateCategoryUseCase;
import kz.arabro.planogram.nomenclature.domain.entity.category.Category;
import kz.arabro.planogram.nomenclature.domain.exception.CodedException;
import kz.arabro.planogram.nomenclature.testdouble.entity.CategoryStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UpdateCategoryUseCaseImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    private UpdateCategoryUseCase updateCategoryUseCase;

    @BeforeEach
    void setUp() {
        this.updateCategoryUseCase = new UpdateCategoryUseCaseImpl(categoryRepository);
    }

    @Test
    void update_CategoryEditInfoIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> updateCategoryUseCase.update(null));
        assertEquals(UseCaseError.CATEGORY_EDIT_INFO_IS_REQUIRED, ex.getCode());
    }

    @Test
    void update_ValueIsValid_UpdateCategory() {
        var category = CategoryStub.getCategory();

        var categoryIDStr = category.getId().getValue().toString();
        var name = category.getName().getValue();
        var color = category.getColor().name();
        String parentID = null;
        if (category.getParentID().isPresent()){
            parentID = category.getParentID().get().getValue().toString();
        }

        var categoryEditInfo = new CategoryEditInfo(
                categoryIDStr,
                name
        );
        categoryEditInfo.setColor(color);
        categoryEditInfo.setParentID(parentID);

        updateCategoryUseCase.update(categoryEditInfo);
        verify(categoryRepository, times(1)).update(any(Category.class));
    }

}