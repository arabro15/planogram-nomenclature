package kz.arabro.planogram.nomenclature.adapter.controller;

import kz.arabro.planogram.nomenclature.boundary.model.CategoryCreateInfo;
import kz.arabro.planogram.nomenclature.boundary.model.CategoryEditInfo;
import kz.arabro.planogram.nomenclature.boundary.usecase.CreateCategoryUseCase;
import kz.arabro.planogram.nomenclature.boundary.usecase.DeleteCategoryUseCase;
import kz.arabro.planogram.nomenclature.boundary.usecase.ReadDataCategoryUseCase;
import kz.arabro.planogram.nomenclature.boundary.usecase.UpdateCategoryUseCase;
import kz.arabro.planogram.nomenclature.domain.exception.CodedException;
import kz.arabro.planogram.nomenclature.testdouble.controller.CategoryRequestStub;
import kz.arabro.planogram.nomenclature.testdouble.entity.CategoryStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    @Mock
    private CreateCategoryUseCase createCategoryUseCase;

    @Mock
    private DeleteCategoryUseCase deleteCategoryUseCase;

    @Mock
    private UpdateCategoryUseCase updateCategoryUseCase;

    @Mock
    private ReadDataCategoryUseCase readDataCategoryUseCase;

    private CategoryController categoryController;

    @BeforeEach
    void setUp() {
        this.categoryController = new CategoryController(
                createCategoryUseCase,
                deleteCategoryUseCase,
                updateCategoryUseCase,
                readDataCategoryUseCase
        );
    }

    @Test
    void createCategory_CreateCategoryRequestIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, ()-> categoryController.createCategory(null));
        assertEquals(ControllerError.CATEGORY_REQUEST_IS_REQUIRED, ex.getCode());
    }

    @Test
    void createCategory_UseCaseIsThrowEx_ThrowEx() {
        var createCategoryRequest = CategoryRequestStub.getCreateCategoryRequest();

        when(createCategoryUseCase.execute(any(CategoryCreateInfo.class))).thenThrow(RuntimeException.class);
        assertThrows(RuntimeException.class, () -> categoryController.createCategory(createCategoryRequest));
    }

    @Test
    void createCategory_NoException_ReturnCreateCategoryResponse() {
        var category = CategoryStub.getCategory();
        when(createCategoryUseCase.execute(any(CategoryCreateInfo.class))).thenReturn(category);

        var createCategoryRequest = CategoryRequestStub.getCreateCategoryRequest();
        var categoryResponse = categoryController.createCategory(createCategoryRequest);

        assertNotNull(categoryResponse);
        assertEquals(category.getId().getValue().toString(), categoryResponse.getCategoryID());
    }

    @Test
    void deleteCategory_DeleteCategoryRequestIsThrowEx_ThrowEx() {
        doThrow(RuntimeException.class).when(deleteCategoryUseCase).deleteCategoryByID(anyString());

        var deleteCategoryRequest = CategoryRequestStub.getDeleteCategoryRequest();

        assertThrows(RuntimeException.class, () -> categoryController.deleteCategory(deleteCategoryRequest));
    }

    @Test
    void deleteBrand_NoException_ReturnHttpStatusOk() {
        doNothing().when(deleteCategoryUseCase).deleteCategoryByID(anyString());

        var deleteCategoryRequest = CategoryRequestStub.getDeleteCategoryRequest();

        var response = categoryController.deleteCategory(deleteCategoryRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void deleteCategoriesByParentID_DeleteCategoriesByParentIDRequestIsThrowEx_ThrowEx() {
        doThrow(RuntimeException.class).when(deleteCategoryUseCase).deleteCategoriesByParentID(anyString());

        var deleteCategoriesByParentIDRequest = CategoryRequestStub.getDeleteCategoriesByParentIDRequest();

        assertThrows(RuntimeException.class, () -> categoryController.deleteCategoriesByParentID(deleteCategoriesByParentIDRequest));
    }

    @Test
    void deleteCategoriesByParentID_NoException_ReturnHttpStatusOk() {
        doNothing().when(deleteCategoryUseCase).deleteCategoriesByParentID(anyString());

        var deleteCategoriesByParentIDRequest = CategoryRequestStub.getDeleteCategoriesByParentIDRequest();

        var response = categoryController.deleteCategoriesByParentID(deleteCategoriesByParentIDRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void editCategory_EditCategoryRequestIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> categoryController.editCategory(null));
        assertEquals(ControllerError.CATEGORY_REQUEST_IS_REQUIRED, ex.getCode());
    }

    @Test
    void editCategory_UseCaseIsThrowEx_ThrowEx() {
        var editCategoryRequest = CategoryRequestStub.getEditCategoryRequest();

        doThrow(RuntimeException.class).when(updateCategoryUseCase).update(any(CategoryEditInfo.class));
        assertThrows(RuntimeException.class, () -> categoryController.editCategory(editCategoryRequest));
    }

    @Test
    void editBrand_NoException_ReturnEditBrandResponse() {
        doNothing().when(updateCategoryUseCase).update(any(CategoryEditInfo.class));

        var editCategoryRequest = CategoryRequestStub.getEditCategoryRequest();

        var editCategoryResponse = categoryController.editCategory(editCategoryRequest);

        assertNotNull(editCategoryResponse);

        var editCategoryRequestID = editCategoryResponse.getCategoryID();
        var editCategoryRequestName = editCategoryResponse.getName();
        var editCategoryRequestColor = editCategoryResponse.getColor();
        var editCategoryRequestParentID = editCategoryResponse.getParentID();

        var editCategoryResponseID = editCategoryRequest.getCategoryID();
        var editCategoryResponseName = editCategoryRequest.getName();
        var editCategoryResponseColor = editCategoryRequest.getColor();
        var editCategoryResponseParentID = editCategoryRequest.getParentID();

        assertEquals(editCategoryRequestID, editCategoryResponseID);
        assertEquals(editCategoryRequestName, editCategoryResponseName);
        assertEquals(editCategoryRequestColor, editCategoryResponseColor);
        assertEquals(editCategoryRequestParentID, editCategoryResponseParentID);
    }

    @Test
    void getCategoryByID_GetCategoryByIDRequestIsNull_RuntimeEx() {
        assertThrows(RuntimeException.class, () -> categoryController.getCategoryByID(null));
    }

    @Test
    void getCategoryByID_NoException_ReturnCategoryResponse() {
        var category = CategoryStub.getCategory();

        when(readDataCategoryUseCase.findByID(anyString())).thenReturn(category);

        var getCategoryByIDRequest = CategoryRequestStub.getCategoryByIDRequest();

        var categoryResponse = categoryController.getCategoryByID(getCategoryByIDRequest);

        assertNotNull(categoryResponse);
        assertEquals(category.getId().getValue().toString(), categoryResponse.getCategoryID());
    }

    @Test
    void getCategoriesByParentID_ReadDataCategoryUseCaseIsThrowEx_RuntimeEx() {
        var getCategoriesByParentIDRequest = CategoryRequestStub.getCategoriesByParentIDRequest();
        when(readDataCategoryUseCase.findByParentID(anyString())).thenThrow(RuntimeException.class);

        assertThrows(RuntimeException.class, () -> categoryController.getCategoriesByParentID(getCategoriesByParentIDRequest));
    }

    @Test
    void getCategoriesByParentID_NoException_ReturnCategoryResponses() {
        var count = 5;
        var categories = CategoryStub.getCategories(count);

        when(readDataCategoryUseCase.findByParentID(anyString())).thenReturn(categories);

        var getCategoriesByParentIDRequest = CategoryRequestStub.getCategoriesByParentIDRequest();

        var categoryResponses = categoryController.getCategoriesByParentID(getCategoriesByParentIDRequest);

        assertNotNull(categoryResponses);
        for (int i = 0; i < count; i++) {
            var categoryID = categories.get(i).getId().getValue().toString();
            var categoryName = categories.get(i).getName().getValue();
            var categoryColor = categories.get(i).getColor().name();
            var categoryParentIDOpt = categories.get(i).getParentID();

            var categoryResponseID = categoryResponses.get(i).getCategoryID();
            var categoryResponseName = categoryResponses.get(i).getName();
            var categoryResponseColor = categoryResponses.get(i).getColor();
            var categoryResponseParentID = categoryResponses.get(i).getParentID();

            assertEquals(categoryID, categoryResponseID);
            assertEquals(categoryName, categoryResponseName);
            assertEquals(categoryColor, categoryResponseColor);
            assertTrue(categoryParentIDOpt.isPresent());
            var categoryParentID = categoryParentIDOpt.get().getValue().toString();
            assertEquals(categoryParentID, categoryResponseParentID);
        }
    }

    @Test
    void getAllCategories_NotValues_ReturnCategoryResponses() {
        var count = 5;
        var categories = CategoryStub.getCategories(count);

        when(readDataCategoryUseCase.findAll()).thenReturn(categories);

        var categoryResponses = categoryController.getAllCategories();

        assertNotNull(categoryResponses);
        for (int i = 0; i < count; i++) {
            var categoryID = categories.get(i).getId().getValue().toString();
            var categoryName = categories.get(i).getName().getValue();
            var categoryColor = categories.get(i).getColor().name();
            var categoryParentIDOpt = categories.get(i).getParentID();

            var categoryResponseID = categoryResponses.get(i).getCategoryID();
            var categoryResponseName = categoryResponses.get(i).getName();
            var categoryResponseColor = categoryResponses.get(i).getColor();
            var categoryResponseParentID = categoryResponses.get(i).getParentID();

            assertEquals(categoryID, categoryResponseID);
            assertEquals(categoryName, categoryResponseName);
            assertEquals(categoryColor, categoryResponseColor);
            assertTrue(categoryParentIDOpt.isPresent());
            var categoryParentID = categoryParentIDOpt.get().getValue().toString();
            assertEquals(categoryParentID, categoryResponseParentID);
        }
    }




}