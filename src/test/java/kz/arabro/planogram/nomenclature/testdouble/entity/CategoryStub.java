package kz.arabro.planogram.nomenclature.testdouble.entity;

import kz.arabro.planogram.nomenclature.domain.entity.category.Category;
import kz.arabro.planogram.nomenclature.domain.entity.category.CategoryBuilder;
import kz.arabro.planogram.nomenclature.domain.entity.category.CategoryID;
import kz.arabro.planogram.nomenclature.domain.entity.category.Color;

import java.util.ArrayList;
import java.util.List;

public final class CategoryStub {

    public static Category getCategory() {
        var id = CategoryID.newID();
        var name = NameStub.getName();
        var color = Color.GRAY;
        var parentID = CategoryID.newID();

        return new CategoryBuilder().
                setID(id).
                setName(name).
                setColor(color).
                setParentID(parentID).
                build();
    }

    public static List<Category> getCategories(int count) {
        var categories = new ArrayList<Category>();

        for (int i = 0; i < count; i++) {
            categories.add(getCategory());
        }

        return categories;
    }



}
