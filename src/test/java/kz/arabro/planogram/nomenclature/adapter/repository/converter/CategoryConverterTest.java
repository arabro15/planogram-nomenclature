package kz.arabro.planogram.nomenclature.adapter.repository.converter;

import kz.arabro.planogram.nomenclature.adapter.repository.RepositoryError;
import kz.arabro.planogram.nomenclature.domain.exception.CodedException;
import kz.arabro.planogram.nomenclature.testdouble.entity.CategoryStub;
import kz.arabro.planogram.nomenclature.testdouble.repository.CategoryDbModelStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryConverterTest {

    @Test
    void toEntity_CategoryDbModelIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> CategoryConverter.toEntity(null));
        assertEquals(RepositoryError.CATEGORY_DB_MODEL_IS_REQUIRED, ex.getCode());
    }

    @Test
    void toEntity_AllParamsIsValid_ReturnCategory() {
        var categoryDbModel = CategoryDbModelStub.getCategoryDbModel();
        var category = CategoryConverter.toEntity(categoryDbModel);

        var categoryDbModelID = categoryDbModel.getId();
        var categoryDbModelName = categoryDbModel.getName();
        var categoryDbModelColor = categoryDbModel.getColor();
        var categoryDbModelParentID = categoryDbModel.getParentID();

        var categoryID = category.getId().getValue();
        var categoryName = category.getName().getValue();
        var categoryColor = category.getColor().name();
        var categoryParentIDOpt = category.getParentID();

        assertNotNull(category);

        assertEquals(categoryDbModelID, categoryID);
        assertEquals(categoryDbModelName, categoryName);
        assertEquals(categoryDbModelColor, categoryColor);

        if (categoryDbModelParentID != null) {
            if (categoryParentIDOpt.isPresent()) {
                var categoryParentID = categoryParentIDOpt.get().getValue();
                assertEquals(categoryDbModelParentID, categoryParentID);
            }
        }
    }

    @Test
    void toEntities_CategoryDbModelsIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> CategoryConverter.toEntities(null));
        assertEquals(RepositoryError.CATEGORY_DB_MODELS_IS_REQUIRED, ex.getCode());
    }

    @Test
    void toEntities_AllParamsIsValid_ReturnBrandDbModels() {
        var count = 5;

        var categoryDbModels = CategoryDbModelStub.getCategoryDbModels(count);
        var categories = CategoryConverter.toEntities(categoryDbModels);

        assertNotNull(categories);

        for (int i = 0; i < count; i++) {
            var categoryDbModelID = categoryDbModels.get(i).getId();
            var categoryDbModelName = categoryDbModels.get(i).getName();
            var categoryDbModelColor = categoryDbModels.get(i).getColor();
            var categoryDbModelParentID = categoryDbModels.get(i).getParentID();

            var categoryID = categories.get(i).getId().getValue();
            var categoryName = categories.get(i).getName().getValue();
            var categoryColor = categories.get(i).getColor().name();
            var categoryParentIDOpt = categories.get(i).getParentID();


            assertEquals(categoryDbModelID, categoryID);
            assertEquals(categoryDbModelName, categoryName);
            assertEquals(categoryDbModelColor, categoryColor);

            if (categoryDbModelParentID != null) {
                if (categoryParentIDOpt.isPresent()) {
                    var categoryParentID = categoryParentIDOpt.get().getValue();
                    assertEquals(categoryDbModelParentID, categoryParentID);
                }
            }
        }
    }

    @Test
    void toModel_CategoryIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> CategoryConverter.toModel(null));
        assertEquals(RepositoryError.CATEGORY_IS_REQUIRED, ex.getCode());
    }

    @Test
    void toModel_AllParamsIsValid_ReturnCategoryDbModel() {
        var category = CategoryStub.getCategory();
        var categoryDbModel = CategoryConverter.toModel(category);

        var categoryID = category.getId().getValue();
        var categoryName = category.getName().getValue();
        var categoryColor = category.getColor().name();
        var categoryParentIDOpt = category.getParentID();

        var categoryDbModelID = categoryDbModel.getId();
        var categoryDbModelName = categoryDbModel.getName();
        var categoryDbModelColor = categoryDbModel.getColor();
        var categoryDbModelParentID = categoryDbModel.getParentID();

        assertNotNull(categoryDbModel);

        assertEquals(categoryID, categoryDbModelID);
        assertEquals(categoryName, categoryDbModelName);
        assertEquals(categoryColor, categoryDbModelColor);

        if (categoryParentIDOpt.isPresent()) {
            if (categoryDbModelParentID != null) {
                var categoryParentID = categoryParentIDOpt.get().getValue();
                assertEquals(categoryParentID, categoryDbModelParentID);
            }
        }
    }

    @Test
    void toModels_CategoriesIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> CategoryConverter.toModels(null));
        assertEquals(RepositoryError.CATEGORIES_IS_REQUIRED, ex.getCode());
    }

    @Test
    void toModels_AllParamsIsValid_ReturnBrandDbModels() {
        var count = 5;

        var categories = CategoryStub.getCategories(count);
        var categoryDbModels = CategoryConverter.toModels(categories);

        assertNotNull(categoryDbModels);

        for (int i = 0; i < count; i++) {
            var categoryID = categories.get(i).getId().getValue();
            var categoryName = categories.get(i).getName().getValue();
            var categoryColor = categories.get(i).getColor().name();
            var categoryParentIDOpt = categories.get(i).getParentID();

            var categoryDbModelID = categoryDbModels.get(i).getId();
            var categoryDbModelName = categoryDbModels.get(i).getName();
            var categoryDbModelColor = categoryDbModels.get(i).getColor();
            var categoryDbModelParentID = categoryDbModels.get(i).getParentID();

            assertEquals(categoryID, categoryDbModelID);
            assertEquals(categoryName, categoryDbModelName);
            assertEquals(categoryColor, categoryDbModelColor);

            if (categoryParentIDOpt.isPresent()) {
                if (categoryDbModelParentID != null) {
                    var categoryParentID = categoryParentIDOpt.get().getValue();
                    assertEquals(categoryParentID, categoryDbModelParentID);
                }
            }
        }
    }
}