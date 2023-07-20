package kz.arabro.planogram.nomenclature.adapter.controller.converter;

import kz.arabro.planogram.nomenclature.adapter.controller.ControllerError;
import kz.arabro.planogram.nomenclature.adapter.controller.response.CategoryResponse;
import kz.arabro.planogram.nomenclature.domain.entity.category.Category;

import java.util.List;

public class CategoryResponseConverter {

    public static CategoryResponse categoryToResponse(Category category) {
        if (category == null) {
            throw ControllerError.errCategoryIsNull();
        }

        var categoryId = category.getId().getValue().toString();
        var name = category.getName().getValue();
        var color = category.getColor().toString();
        String parentID = null;
        if (category.getParentID().isPresent()) {
            parentID = category.getParentID().get().getValue().toString();
        }

        var categoryResponse = new CategoryResponse();
        categoryResponse.setCategoryID(categoryId);
        categoryResponse.setName(name);
        categoryResponse.setColor(color);
        categoryResponse.setParentID(parentID);

        return categoryResponse;
    }

    public static List<CategoryResponse> categoriesToResponses(List<Category> categories) {
        if (categories == null) {
            throw ControllerError.errCategoriesIsNull();
        }

        return categories.stream().
                map(CategoryResponseConverter::categoryToResponse).
                toList();
    }

    private CategoryResponseConverter() {}
}
