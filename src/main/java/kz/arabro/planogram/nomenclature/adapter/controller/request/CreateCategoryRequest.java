package kz.arabro.planogram.nomenclature.adapter.controller.request;

import lombok.Data;

@Data
public class CreateCategoryRequest {
    private String name;
    private String color;
    private String parentID;
}
