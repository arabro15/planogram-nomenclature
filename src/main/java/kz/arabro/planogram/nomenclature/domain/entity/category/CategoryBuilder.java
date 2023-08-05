package kz.arabro.planogram.nomenclature.domain.entity.category;

import kz.arabro.planogram.nomenclature.domain.entity.product.Name;

public class CategoryBuilder {
    private CategoryID id;
    private Name name;
    private Color color;
    private CategoryID parentID;

    public CategoryBuilder setID(CategoryID id) {
        this.id = id;
        return this;
    }

    public CategoryBuilder setName(Name name) {
        this.name = name;
        return this;
    }

    public CategoryBuilder setColor(Color color) {
        this.color = color;
        return this;
    }

    public CategoryBuilder setParentID(CategoryID parentID) {
        this.parentID = parentID;
        return this;
    }

    public Category build() {
        checkRequiredFields();
        return new Category(id, name, color, parentID);
    }

    public void checkRequiredFields() {
        if (id == null) {
            throw CategoryError.errNullIdCategoryValue();
        }
        if(name == null) {
            throw CategoryError.errNullNameCategoryValue();
        }
        if(color == null) {
            throw CategoryError.errNullColorCategoryValue();
        }
    }
}

