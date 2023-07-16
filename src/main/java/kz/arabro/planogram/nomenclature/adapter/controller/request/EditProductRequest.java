package kz.arabro.planogram.nomenclature.adapter.controller.request;

import lombok.Data;

@Data
public class EditProductRequest {
    private String productID;
    private String code1C;
    private String rusName;
    private String kazName;
    private String categoryID;
    private String brandID;
    private String producerID;
    private String barcode;
    private String price;
    private String height;
    private String weight;
    private String length;
    private String imagePath;
}
