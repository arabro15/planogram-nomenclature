package kz.arabro.planogram.nomenclature.adapter.controller.request;

import lombok.Data;

@Data
public class EditCategoryRequest {
    private String categoryID;
    private String name;
    private String color;
    private String parentID;
}
