package kz.arabro.planogram.nomenclature.testdouble.controller;

import kz.arabro.planogram.nomenclature.adapter.controller.request.*;
import kz.arabro.planogram.nomenclature.domain.entity.category.CategoryID;
import kz.arabro.planogram.nomenclature.domain.entity.category.Color;
import kz.arabro.planogram.nomenclature.testdouble.entity.NameStub;

public class CategoryRequestStub {

    public static CreateCategoryRequest getCreateCategoryRequest() {
        var createCategoryRequest = new CreateCategoryRequest();
        createCategoryRequest.setName(NameStub.getName().getValue());
        createCategoryRequest.setColor(Color.RED.name());
        createCategoryRequest.setParentID(CategoryID.newID().getValue().toString());
        return createCategoryRequest;
    }

    public static DeleteCategoryRequest getDeleteCategoryRequest() {
        var deleteCategoryRequest = new DeleteCategoryRequest();
        deleteCategoryRequest.setCategoryID(CategoryID.newID().getValue().toString());
        return deleteCategoryRequest;
    }
    public static DeleteCategoriesByParentIDRequest getDeleteCategoriesByParentIDRequest() {
        var deleteCategoriesByParentIDRequest = new DeleteCategoriesByParentIDRequest();
        deleteCategoriesByParentIDRequest.setParentID(CategoryID.newID().getValue().toString());
        return deleteCategoriesByParentIDRequest;
    }

    public static EditCategoryRequest getEditCategoryRequest() {
        var editCategoryRequest = new EditCategoryRequest();
        editCategoryRequest.setCategoryID(CategoryID.newID().getValue().toString());
        editCategoryRequest.setName(NameStub.getName().getValue());
        editCategoryRequest.setColor(Color.YELLOW.name());
        editCategoryRequest.setParentID(CategoryID.newID().getValue().toString());

        return editCategoryRequest;
    }

    public static GetCategoryByIDRequest getCategoryByIDRequest() {
        var getCategoryByIDRequest = new GetCategoryByIDRequest();
        getCategoryByIDRequest.setCategoryID(CategoryID.newID().getValue().toString());
        return getCategoryByIDRequest;
    }

    public static GetCategoriesByParentIDRequest getCategoriesByParentIDRequest() {
        var getCategoriesByParentIDRequest = new GetCategoriesByParentIDRequest();
        getCategoriesByParentIDRequest.setParentID(CategoryID.newID().getValue().toString());
        return getCategoriesByParentIDRequest;
    }
}
