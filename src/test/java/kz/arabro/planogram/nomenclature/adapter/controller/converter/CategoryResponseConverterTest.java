package kz.arabro.planogram.nomenclature.adapter.controller.converter;

import kz.arabro.planogram.nomenclature.adapter.controller.ControllerError;
import kz.arabro.planogram.nomenclature.domain.exception.CodedException;
import kz.arabro.planogram.nomenclature.testdouble.entity.CategoryStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryResponseConverterTest {

    @Test
    void categoryToResponse_CategoryIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> CategoryResponseConverter.categoryToResponse(null));
        assertEquals(ControllerError.CATEGORY_IS_NULL, ex.getCode());
    }

    @Test
    void categoryToResponse_ValueIsValid_ReturnCategoryResponse() {
        var category = CategoryStub.getCategory();
        var categoryIDStr = category.getId().getValue().toString();
        var categoryName = category.getName().getValue();
        var categoryColor = category.getColor().name();
        var categoryParentIDOpt = category.getParentID();

        var categoryResponse = CategoryResponseConverter.categoryToResponse(category);

        assertNotNull(categoryResponse);

        var categoryResponseID = categoryResponse.getCategoryID();
        var categoryResponseName = categoryResponse.getName();
        var categoryResponseColor = categoryResponse.getColor();
        var categoryResponseParentID = categoryResponse.getParentID();

        assertEquals(categoryIDStr, categoryResponseID);
        assertEquals(categoryName, categoryResponseName);
        assertEquals(categoryColor, categoryResponseColor);

        assertTrue(categoryParentIDOpt.isPresent());
        var categoryParentIDStr = categoryParentIDOpt.get().getValue().toString();
        assertEquals(categoryParentIDStr, categoryResponseParentID);
    }

    @Test
    void categoriesToResponses_CategoriesIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> CategoryResponseConverter.categoriesToResponses(null));
        assertEquals(ControllerError.CATEGORIES_IS_NULL, ex.getCode());
    }

    @Test
    void categoriesToResponses_ValueIsValid_ReturnCategoryResponses() {
        var count = 5;

        var categories = CategoryStub.getCategories(count);

        var categoryResponses = CategoryResponseConverter.categoriesToResponses(categories);

        assertNotNull(categoryResponses);

        for (int i = 0; i < count; i++) {
            var categoryIDStr = categories.get(i).getId().getValue().toString();
            var categoryName = categories.get(i).getName().getValue();
            var categoryColor = categories.get(i).getColor().name();
            var categoryParentIDOpt = categories.get(i).getParentID();

            var categoryResponseID = categoryResponses.get(i).getCategoryID();
            var categoryResponseName = categoryResponses.get(i).getName();
            var categoryResponseColor = categoryResponses.get(i).getColor();
            var categoryResponseParentID = categoryResponses.get(i).getParentID();

            assertEquals(categoryIDStr, categoryResponseID);
            assertEquals(categoryName, categoryResponseName);
            assertEquals(categoryColor, categoryResponseColor);

            assertTrue(categoryParentIDOpt.isPresent());
            var categoryParentIDStr = categoryParentIDOpt.get().getValue().toString();
            assertEquals(categoryParentIDStr, categoryResponseParentID);
        }
    }

}