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
        var parentIDOpt = category.getParentID();

        var categoryResponse = new CategoryResponse();
        categoryResponse.setCategoryID(categoryId);
        categoryResponse.setName(name);
        categoryResponse.setColor(color);

        if (parentIDOpt.isPresent()) {
            var parentID = parentIDOpt.get().getValue().toString();
            categoryResponse.setParentID(parentID);
        }

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
