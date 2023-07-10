package kz.arabro.planogram.nomenclature.domain.entity;

import java.util.Optional;
import java.util.UUID;

public class Category {
    private final CategoryID id;
    private Name name;
    private Color color;
    private CategoryID parentID;

    Category(CategoryID id, Name name, Color color, CategoryID parentID) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.parentID = parentID;
    }

    public CategoryID getId() {
        return this.id;
    }

    public Name getName() {
        return this.name;
    }

    public Color getColor() {
        return this.color;
    }

    public Optional<CategoryID> getParentID() {
        return Optional.ofNullable(this.parentID);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name=" + name +
                ", color=" + color +
                ", parentID=" + parentID +
                '}';
    }
}
