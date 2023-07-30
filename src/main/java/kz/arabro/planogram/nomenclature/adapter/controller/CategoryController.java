package kz.arabro.planogram.nomenclature.adapter.controller;

import kz.arabro.planogram.nomenclature.adapter.controller.converter.CategoryRequestConverter;
import kz.arabro.planogram.nomenclature.adapter.controller.converter.CategoryResponseConverter;
import kz.arabro.planogram.nomenclature.adapter.controller.request.*;
import kz.arabro.planogram.nomenclature.adapter.controller.response.CategoryResponse;
import kz.arabro.planogram.nomenclature.adapter.controller.response.CreateCategoryResponse;
import kz.arabro.planogram.nomenclature.adapter.controller.response.EditCategoryResponse;
import kz.arabro.planogram.nomenclature.boundary.usecase.CreateCategoryUseCase;
import kz.arabro.planogram.nomenclature.boundary.usecase.DeleteCategoryUseCase;
import kz.arabro.planogram.nomenclature.boundary.usecase.ReadDataCategoryUseCase;
import kz.arabro.planogram.nomenclature.boundary.usecase.UpdateCategoryUseCase;
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
    private final CreateCategoryUseCase createCategoryUseCase;
    private final DeleteCategoryUseCase deleteCategoryUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;
    private final ReadDataCategoryUseCase readDataCategoryUseCase;

    public CategoryController(CreateCategoryUseCase createCategoryUseCase,
                              DeleteCategoryUseCase deleteCategoryUseCase,
                              UpdateCategoryUseCase updateCategoryUseCase,
                              ReadDataCategoryUseCase readDataCategoryUseCase) {
        this.createCategoryUseCase = createCategoryUseCase;
        this.deleteCategoryUseCase = deleteCategoryUseCase;
        this.updateCategoryUseCase = updateCategoryUseCase;
        this.readDataCategoryUseCase = readDataCategoryUseCase;
    }

    @PostMapping(path = "/create-category")
    public CreateCategoryResponse createCategory(@RequestBody CreateCategoryRequest request) {
        var info = CategoryRequestConverter.createCategoryRequestToModel(request);

        var category = createCategoryUseCase.execute(info);

        var response = new CreateCategoryResponse();
        response.setCategoryID(category.getId().getValue().toString());

        return response;
    }

    @PostMapping(path = "/delete-by-id-category")
    public ResponseEntity<Object> deleteCategory(@RequestBody DeleteCategoryRequest request) {
        deleteCategoryUseCase.deleteCategoryByID(request.getCategoryID());
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping(path = "/delete-by-parent-id-categories")
    public ResponseEntity<Object> deleteCategoriesByParentID(@RequestBody DeleteCategoriesByParentIDRequest request) {
        deleteCategoryUseCase.deleteCategoryByParentID(request.getParentID());
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping(path = "/edit-category")
    public EditCategoryResponse editCategory(@RequestBody EditCategoryRequest request) {
        var category = CategoryRequestConverter.editCategoryRequestToModel(request);

        updateCategoryUseCase.update(category);

        var response = new EditCategoryResponse();
        response.setCategoryID(category.getCategoryID());
        response.setName(category.getName());
        response.setColor(category.getColor());
        response.setParentID(category.getParentID());

        return response;
    }

    @PostMapping(path = "/get-category-by-id")
    public CategoryResponse getCategoeyByID(@RequestBody GetCategoryByIDRequest request) {
        var category = readDataCategoryUseCase.findByID(request.getCategoryID());
        return CategoryResponseConverter.categoryToResponse(category);
    }

    @PostMapping(path = "/get-categories-by-parent-id")
    public List<CategoryResponse> getCategoriesByParentID(@RequestBody GetCategoriesByParentIDRequest request) {
        var categories = readDataCategoryUseCase.findByParentID(request.getParentID());
        return CategoryResponseConverter.categoriesToResponses(categories);
    }

    @PostMapping(path = "/get-all-categories")
    public List<CategoryResponse> getAllCategories() {
        var categories = readDataCategoryUseCase.findAll();
        return CategoryResponseConverter.categoriesToResponses(categories);
    }


}
