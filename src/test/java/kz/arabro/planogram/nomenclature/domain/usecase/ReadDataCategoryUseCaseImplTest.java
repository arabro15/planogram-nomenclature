package kz.arabro.planogram.nomenclature.domain.usecase;

import kz.arabro.planogram.nomenclature.boundary.repository.BrandRepository;
import kz.arabro.planogram.nomenclature.boundary.repository.CategoryRepository;
import kz.arabro.planogram.nomenclature.boundary.usecase.ReadDataBrandUseCase;
import kz.arabro.planogram.nomenclature.boundary.usecase.ReadDataCategoryUseCase;
import kz.arabro.planogram.nomenclature.domain.entity.brand.BrandError;
import kz.arabro.planogram.nomenclature.domain.entity.brand.BrandID;
import kz.arabro.planogram.nomenclature.domain.entity.category.Category;
import kz.arabro.planogram.nomenclature.domain.entity.category.CategoryError;
import kz.arabro.planogram.nomenclature.domain.entity.category.CategoryID;
import kz.arabro.planogram.nomenclature.domain.exception.CodedException;
import kz.arabro.planogram.nomenclature.testdouble.entity.BrandStub;
import kz.arabro.planogram.nomenclature.testdouble.entity.CategoryStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReadDataCategoryUseCaseImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    private ReadDataCategoryUseCase readDataCategoryUseCase;

    @BeforeEach
    void setUp() {
        this.readDataCategoryUseCase = new ReadDataCategoryUseCaseImpl(categoryRepository);
    }

    @Test
    void findByID_CategoryIDStrIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> readDataCategoryUseCase.findByID(null));
        assertEquals(CategoryError.CATEGORY_ID_VALUE_IS_REQUIRED, ex.getCode());
    }

    @Test
    void findByID_CategoryIsNotFoundByID_ThrowEx() {
        var categoryIDStr = CategoryID.newID().getValue().toString();

        when(categoryRepository.findByID(any(CategoryID.class))).thenReturn(Optional.empty());

        var ex = assertThrows(CodedException.class, () -> readDataCategoryUseCase.findByID(categoryIDStr));
        assertEquals(UseCaseError.CATEGORY_IS_NOT_FOUND, ex.getCode());
    }

    @Test
    void findByID_CategoryExists_ReturnCategory() {
        var category = CategoryStub.getCategory();
        var categoryID = CategoryID.newID().getValue().toString();

        when(categoryRepository.findByID(any(CategoryID.class))).thenReturn(Optional.of(category));

        var actualCategory = readDataCategoryUseCase.findByID(categoryID);
        assertNotNull(actualCategory);
        assertEquals(category, actualCategory);
    }

    @Test
    void findByParentID_ParentIDStrIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> readDataCategoryUseCase.findByParentID(null));
        assertEquals(UseCaseError.CATEGORY_PARENT_ID_IS_REQUIRED, ex.getCode());
    }

    @Test
    void findByParentID_CategoriesIsNotFound_ThrowEx() {
        var parentID = CategoryID.newID().getValue().toString();
        List<Category> categories = emptyList();

        when(categoryRepository.findByParentID(any(CategoryID.class))).thenReturn(categories);

        var actualCategories = readDataCategoryUseCase.findByParentID(parentID);

        assertNotNull(actualCategories);
        assertTrue(actualCategories.isEmpty());
    }

    @Test
    void findByParentID_CategoriesExists_ReturnCategories() {
        var parentID = CategoryID.newID().getValue().toString();
        var categories = CategoryStub.getCategories(5);
        when(categoryRepository.findByParentID(any(CategoryID.class))).thenReturn(categories);

        var actualCategories = readDataCategoryUseCase.findByParentID(parentID);

        assertNotNull(actualCategories);
        assertEquals(categories, actualCategories);
    }





}