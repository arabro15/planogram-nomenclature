package kz.arabro.planogram.nomenclature.adapter.controller;

import kz.arabro.planogram.nomenclature.adapter.controller.converter.CategoryRequestConverter;
import kz.arabro.planogram.nomenclature.adapter.controller.converter.CategoryResponseConverter;
import kz.arabro.planogram.nomenclature.adapter.controller.request.*;
import kz.arabro.planogram.nomenclature.adapter.controller.response.CategoryResponse;
import kz.arabro.planogram.nomenclature.adapter.controller.response.CreateCategoryResponse;
import kz.arabro.planogram.nomenclature.adapter.controller.response.EditCategoryResponse;
import kz.arabro.planogram.nomenclature.boundary.usecase.CategoryCreatorUseCase;
import kz.arabro.planogram.nomenclature.boundary.usecase.CategoryDeleteUseCase;
import kz.arabro.planogram.nomenclature.boundary.usecase.CategoryEditorUseCase;
import kz.arabro.planogram.nomenclature.boundary.usecase.CategoryReadDataUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
public class CategoryController {
    private final CategoryCreatorUseCase categoryCreatorUseCase;
    private final CategoryDeleteUseCase categoryDeleteUseCase;
    private final CategoryEditorUseCase categoryEditorUseCase;
    private final CategoryReadDataUseCase categoryReadDataUseCase;

    public CategoryController(CategoryCreatorUseCase categoryCreatorUseCase,
                              CategoryDeleteUseCase categoryDeleteUseCase,
                              CategoryEditorUseCase categoryEditorUseCase,
                              CategoryReadDataUseCase categoryReadDataUseCase) {
        this.categoryCreatorUseCase = categoryCreatorUseCase;
        this.categoryDeleteUseCase = categoryDeleteUseCase;
        this.categoryEditorUseCase = categoryEditorUseCase;
        this.categoryReadDataUseCase = categoryReadDataUseCase;
    }

    @PostMapping(path = "/create-category")
    public CreateCategoryResponse createCategory(@RequestBody CreateCategoryRequest request) {
        var info = CategoryRequestConverter.createCategoryRequestToModel(request);

        var category = categoryCreatorUseCase.execute(info);

        var response = new CreateCategoryResponse();
        response.setCategoryID(category.getId().getValue().toString());

        return response;
    }

    @PostMapping(path = "/delete-by-id-category")
    public ResponseEntity<Object> deleteCategory(@RequestBody DeleteCategoryRequest request) {
        categoryDeleteUseCase.deleteCategoryByID(request.getCategoryID());
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping(path = "/delete-by-parent-id-categories")
    public ResponseEntity<Object> deleteCategoriesByParentID(@RequestBody DeleteCategoriesByParentIDRequest request) {
        categoryDeleteUseCase.deleteCategoryByParentID(request.getParentID());
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping(path = "/edit-category")
    public EditCategoryResponse editCategory(@RequestBody EditCategoryRequest request) {
        var category = CategoryRequestConverter.editCategoryRequestToModel(request);

        categoryEditorUseCase.update(category);

        var response = new EditCategoryResponse();
        response.setCategoryID(category.getCategoryID());
        response.setName(category.getName());
        response.setColor(category.getColor());
        response.setParentID(category.getParentID());

        return response;
    }

    @PostMapping(path = "/get-category-by-id")
    public CategoryResponse getCategoeyByID(@RequestBody GetCategoryByIDRequest request) {
        var category = categoryReadDataUseCase.findByID(request.getCategoryID());
        return CategoryResponseConverter.categoryToResponse(category);
    }

    @PostMapping(path = "/get-categories-by-parent-id")
    public List<CategoryResponse> getCategoriesByParentID(@RequestBody GetCategoriesByParentIDRequest request) {
        var categories = categoryReadDataUseCase.findAllByParentID(request.getParentID());
        return CategoryResponseConverter.categoriesToResponses(categories);
    }

    @PostMapping(path = "/get-all-categories")
    public List<CategoryResponse> getAllCategories() {
        var categories = categoryReadDataUseCase.findAll();
        return CategoryResponseConverter.categoriesToResponses(categories);
    }


}
