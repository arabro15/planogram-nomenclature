package kz.arabro.planogram.nomenclature.boundary.model;

import lombok.Data;

@Data
public class CategoryEditInfo {
    private final String categoryID;
    private final String name;
    private String color;
    private String parentID;
}
