package kz.arabro.planogram.nomenclature.adapter.controller.request;

import lombok.Data;

@Data
public class GetCategoryByParentIDRequest {
    private String parentID;
}
