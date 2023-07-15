package kz.arabro.planogram.nomenclature.adapter.controller.response;

import lombok.Data;

@Data
public class EditCategoryResponse {
    private String categoryID;
    private String name;
    private String color;
    private String parentID;
}
