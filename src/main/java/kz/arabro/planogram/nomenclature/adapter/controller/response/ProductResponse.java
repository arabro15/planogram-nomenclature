package kz.arabro.planogram.nomenclature.adapter.controller.response;

import lombok.Data;

@Data
public class ProductResponse {
    private String productID;
    private String code1C;
    private String rusName;
    private String kazName;
    private CategoryResponse category;
    private BrandResponse brand;
    private ProducerResponse producer;
    private String barcode;
    private String price;
    private String height;
    private String weight;
    private String length;
    private String imagePath;
}
