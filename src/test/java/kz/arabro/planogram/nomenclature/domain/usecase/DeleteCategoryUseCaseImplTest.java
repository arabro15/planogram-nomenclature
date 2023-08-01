package kz.arabro.planogram.nomenclature.domain.usecase;

import kz.arabro.planogram.nomenclature.boundary.repository.CategoryRepository;
import kz.arabro.planogram.nomenclature.boundary.usecase.DeleteCategoryUseCase;
import kz.arabro.planogram.nomenclature.domain.entity.category.CategoryID;
import kz.arabro.planogram.nomenclature.domain.exception.CodedException;
import kz.arabro.planogram.nomenclature.testdouble.entity.CategoryStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteCategoryUseCaseImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    private DeleteCategoryUseCase deleteCategoryUseCase;

    @BeforeEach
    void setUp() {
        this.deleteCategoryUseCase = new DeleteCategoryUseCaseImpl(categoryRepository);
    }

    @Test
    void deleteCategoryByID_CategoryIDStrIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> deleteCategoryUseCase.deleteCategoryByID(null));
        assertEquals(UseCaseError.CATEGORY_ID_IS_REQUIRED, ex.getCode());
    }

    @Test
    void deleteCategoryByID_ValueIsValid_DeleteCategory() {
        var categoryID = CategoryStub.getCategory().getId();
        var categoryIDStr = categoryID.getValue().toString();

        deleteCategoryUseCase.deleteCategoryByID(categoryIDStr);
        verify(categoryRepository, times(1)).deleteById(categoryID);
    }

    @Test
    void deleteCategoriesByParentID_ParentIDStrIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> deleteCategoryUseCase.deleteCategoriesByParentID(null));
        assertEquals(UseCaseError.CATEGORY_PARENT_ID_IS_REQUIRED, ex.getCode());
    }

    @Test
    void deleteCategoriesByParentID_ValueIsValid_DeleteCategories() {
        var parentID = CategoryID.newID();
        var parentIDStr = parentID.getValue().toString();

        deleteCategoryUseCase.deleteCategoriesByParentID(parentIDStr);
        verify(categoryRepository, times(1)).deleteGroupCategoryByParentId(parentID);
    }


}