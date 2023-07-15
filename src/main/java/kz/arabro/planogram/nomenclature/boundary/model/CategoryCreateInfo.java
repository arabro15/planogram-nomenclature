package kz.arabro.planogram.nomenclature.boundary.model;

import lombok.Data;

@Data
public class CategoryCreateInfo {
    private final String name;
    private String color;
    private String parentID;
}
