package kz.arabro.planogram.nomenclature.adapter.repository.converter;

import kz.arabro.planogram.nomenclature.adapter.repository.RepositoryError;
import kz.arabro.planogram.nomenclature.adapter.repository.model.CategoryDbModel;
import kz.arabro.planogram.nomenclature.domain.entity.category.Category;
import kz.arabro.planogram.nomenclature.domain.entity.category.CategoryBuilder;
import kz.arabro.planogram.nomenclature.domain.entity.category.CategoryID;
import kz.arabro.planogram.nomenclature.domain.entity.category.Color;
import kz.arabro.planogram.nomenclature.domain.entity.product.Name;

import java.util.List;
import java.util.UUID;

public class CategoryConverter {

    public static Category toEntity(CategoryDbModel categoryDbModel) {
        if (categoryDbModel == null) {
            throw RepositoryError.errCategoryDbModelIsRequired();
        }

        var id = CategoryID.from(categoryDbModel.getId().toString());
        var name = Name.of(categoryDbModel.getName());
        var color = Color.valueOf(categoryDbModel.getColor());
        var parentID = CategoryID.from(categoryDbModel.getParentID().toString());

        return new CategoryBuilder().
                setID(id).
                setName(name).
                setColor(color).
                setParentID(parentID).
                build();
    }

    public static List<Category> toEntities(List<CategoryDbModel> categoryDbModels) {
        if (categoryDbModels == null) {
            throw RepositoryError.errCategoryDbModelsIsRequired();
        }

        return categoryDbModels.stream().
                map(CategoryConverter::toEntity).
                toList();
    }

    public static CategoryDbModel toModel(Category category) {
        if (category == null) {
            throw RepositoryError.errCategoryIsRequired();
        }

        var id = category.getId().getValue();
        var name = category.getName().getValue();
        var color = category.getColor().toString();
        UUID parentID = null;
        if (category.getParentID().isPresent()){
            parentID = category.getParentID().get().getValue();
        }

        var categoryDbModel = new CategoryDbModel();
        categoryDbModel.setId(id);
        categoryDbModel.setName(name);
        categoryDbModel.setColor(color);
        categoryDbModel.setParentID(parentID);

        return categoryDbModel;
    }

    public static List<CategoryDbModel> toModels(List<Category> categories) {
        if (categories == null) {
            throw RepositoryError.errCategoriesIsRequired();
        }

        return categories.stream().
                map(CategoryConverter::toModel).
                toList();
    }
}
