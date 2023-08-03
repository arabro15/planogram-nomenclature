package kz.arabro.planogram.nomenclature.testdouble.entity;

import kz.arabro.planogram.nomenclature.domain.entity.category.Category;
import kz.arabro.planogram.nomenclature.domain.entity.category.CategoryBuilder;
import kz.arabro.planogram.nomenclature.domain.entity.category.CategoryID;
import kz.arabro.planogram.nomenclature.domain.entity.category.Color;
import kz.arabro.planogram.nomenclature.domain.entity.product.Name;

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

    public static Category getCategoryByParentID() {
        var id = CategoryID.newID();
        var name = NameStub.getName();
        var color = Color.GRAY;
        var parentID = CategoryID.from("db9aacbd-87b8-4720-90e4-4e5135a004eb");

        return new CategoryBuilder().
                setID(id).
                setName(name).
                setColor(color).
                setParentID(parentID).
                build();
    }


    public static List<Category> getCategoriesByParentID(int count) {
        var categories = new ArrayList<Category>();

        for (int i = 0; i < count; i++) {
            categories.add(getCategoryByParentID());
        }

        return categories;

    }

    public static Category getCategoryForProduct() {
        var id = CategoryID.from("1dab5fa7-defa-4451-bc7c-fd339919aeb2");
        var name = Name.of("Spices");
        var color = Color.GREEN;
        var parentID = CategoryID.from("f6e6e050-daef-4640-9e27-042df4df5363");

        return new CategoryBuilder().
                setID(id).
                setName(name).
                setColor(color).
                setParentID(parentID).
                build();
    }
}
