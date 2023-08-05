package kz.arabro.planogram.nomenclature.integration.adapter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.arabro.planogram.nomenclature.adapter.controller.response.CategoryResponse;
import kz.arabro.planogram.nomenclature.adapter.controller.response.CreateCategoryResponse;
import kz.arabro.planogram.nomenclature.adapter.controller.response.EditCategoryResponse;
import kz.arabro.planogram.nomenclature.boundary.model.CategoryCreateInfo;
import kz.arabro.planogram.nomenclature.boundary.model.CategoryEditInfo;
import kz.arabro.planogram.nomenclature.boundary.usecase.CreateCategoryUseCase;
import kz.arabro.planogram.nomenclature.boundary.usecase.DeleteCategoryUseCase;
import kz.arabro.planogram.nomenclature.boundary.usecase.ReadDataCategoryUseCase;
import kz.arabro.planogram.nomenclature.boundary.usecase.UpdateCategoryUseCase;
import kz.arabro.planogram.nomenclature.testdouble.controller.CategoryRequestStub;
import kz.arabro.planogram.nomenclature.testdouble.entity.CategoryStub;
import kz.arabro.planogram.nomenclature.util.annotation.IntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
class CategoryControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private CreateCategoryUseCase createCategoryUseCase;

    @MockBean
    private DeleteCategoryUseCase deleteCategoryUseCase;

    @MockBean
    private UpdateCategoryUseCase updateCategoryUseCase;

    @MockBean
    private ReadDataCategoryUseCase readDataCategoryUseCase;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.
                webAppContextSetup(webApplicationContext).
                build();
    }

    @Test
    void createCategory_RequestIsNull_ReturnIsInternalServerError() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/api/v1/create-category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void createCategory_RequestIsValid_ReturnCreateCategoryResponse() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        var category = CategoryStub.getCategory();

        var createCategoryRequest = CategoryRequestStub.getCreateCategoryRequest();

        var createCategoryResponse = new CreateCategoryResponse();
        createCategoryResponse.setCategoryID(category.getId().getValue().toString());

        when(createCategoryUseCase.execute(any(CategoryCreateInfo.class))).thenReturn(category);

        mockMvc.perform(post("/api/v1/create-category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createCategoryRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(createCategoryResponse)));
    }

    @Test
    void deleteCategory_RequestIsNull_ReturnIsInternalServerError() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/api/v1/delete-by-id-category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void deleteCategory_RequestIsValid_ReturnStatusOk() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        var deleteCategoryRequest = CategoryRequestStub.getDeleteCategoryRequest();

        doNothing().when(deleteCategoryUseCase).deleteCategoryByID(anyString());

        mockMvc.perform(post("/api/v1/delete-by-id-category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(deleteCategoryRequest)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteCategoriesByParentID_RequestIsNull_ReturnIsInternalServerError() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/api/v1/delete-by-parent-id-categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void deleteCategoriesByParentID_RequestIsValid_ReturnStatusOk() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        var deleteCategoriesByParentIDRequest = CategoryRequestStub.getDeleteCategoriesByParentIDRequest();

        doNothing().when(deleteCategoryUseCase).deleteCategoriesByParentID(anyString());

        mockMvc.perform(post("/api/v1/delete-by-parent-id-categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(deleteCategoriesByParentIDRequest)))
                .andExpect(status().isOk());
    }

    @Test
    void editCategory_RequestIsNull_ReturnIsInternalServerError() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/api/v1/edit-category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void editCategory_RequestIsValid_ReturnEditBrandResponse() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        var editCategoryRequest = CategoryRequestStub.getEditCategoryRequest();

        doNothing().when(updateCategoryUseCase).update(any(CategoryEditInfo.class));

        var editCategoryResponse = new EditCategoryResponse();
        editCategoryResponse.setCategoryID(editCategoryRequest.getCategoryID());
        editCategoryResponse.setName(editCategoryRequest.getName());
        editCategoryResponse.setColor(editCategoryRequest.getColor());
        editCategoryResponse.setParentID(editCategoryRequest.getParentID());

        mockMvc.perform(post("/api/v1/edit-category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(editCategoryRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(editCategoryResponse)));
    }

    @Test
    void getCategoryByID_RequestIsNull_ReturnIsInternalServerError() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/api/v1/get-category-by-id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void getCategoryByID_RequestIsValid_ReturnCategoryResponse() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        var category = CategoryStub.getCategory();
        var getCategoryByIDRequest = CategoryRequestStub.getCategoryByIDRequest();

        when(readDataCategoryUseCase.findByID(anyString())).thenReturn(category);

        var categoryResponse = new CategoryResponse();
        categoryResponse.setCategoryID(category.getId().getValue().toString());
        categoryResponse.setName(category.getName().getValue());
        categoryResponse.setColor(category.getColor().name());
        var parentIDOpt = category.getParentID();
        assertTrue(parentIDOpt.isPresent());
        categoryResponse.setParentID(parentIDOpt.get().getValue().toString());

        mockMvc.perform(post("/api/v1/get-category-by-id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getCategoryByIDRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(categoryResponse)));
    }

    @Test
    void getCategoriesByParentID_RequestIsNull_ReturnIsInternalServerError() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/api/v1/get-categories-by-parent-id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void getCategoriesByParentID_RequestIsValid_ReturnCategoryResponses() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        var count = 5;

        var categories = CategoryStub.getCategories(count);
        var getCategoriesByParentIDRequest = CategoryRequestStub.getCategoriesByParentIDRequest();

        when(readDataCategoryUseCase.findByParentID(anyString())).thenReturn(categories);

        var categoryResponses = new ArrayList<CategoryResponse>(count);
        for (int i = 0; i < count; i++) {
            var categoryResponse = new CategoryResponse();
            categoryResponse.setCategoryID(categories.get(i).getId().getValue().toString());
            categoryResponse.setName(categories.get(i).getName().getValue());
            categoryResponse.setColor(categories.get(i).getColor().name());
            var parentIDOpt = categories.get(i).getParentID();
            assertTrue(parentIDOpt.isPresent());
            categoryResponse.setParentID(parentIDOpt.get().getValue().toString());

            categoryResponses.add(categoryResponse);
        }

        mockMvc.perform(post("/api/v1/get-categories-by-parent-id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getCategoriesByParentIDRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(categoryResponses)));
    }

    @Test
    void getAllCategories_RequestIsValid_ReturnBrandResponses() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        var count = 5;

        var categories = CategoryStub.getCategories(count);

        when(readDataCategoryUseCase.findAll()).thenReturn(categories);

        var categoryResponses = new ArrayList<CategoryResponse>(count);
        for (int i = 0; i < count; i++) {
            var categoryResponse = new CategoryResponse();
            categoryResponse.setCategoryID(categories.get(i).getId().getValue().toString());
            categoryResponse.setName(categories.get(i).getName().getValue());
            categoryResponse.setColor(categories.get(i).getColor().name());
            var parentIDOpt = categories.get(i).getParentID();
            assertTrue(parentIDOpt.isPresent());
            categoryResponse.setParentID(parentIDOpt.get().getValue().toString());

            categoryResponses.add(categoryResponse);
        }

        mockMvc.perform(post("/api/v1/get-all-categories"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(categoryResponses)));
    }
}