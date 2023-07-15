package kz.arabro.planogram.nomenclature.adapter.controller.converter;

import lombok.Data;

@Data
public class CategoryResponse {
    private String categoryID;
    private String name;
    private String color;
    private String parentID;
}
