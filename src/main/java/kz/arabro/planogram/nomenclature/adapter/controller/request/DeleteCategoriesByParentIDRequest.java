package kz.arabro.planogram.nomenclature.adapter.controller.request;

import lombok.Data;

@Data
public class DeleteCategoriesByParentIDRequest {
    private String parentID;
}
