package kz.arabro.planogram.nomenclature.boundary.model;

import lombok.Data;

@Data
public class ProductEditInfo {
    private String id;
    private String code1C;
    private String rusName;
    private String kazName;
    private String category;
    private String brand;
    private String producer;
    private String barcode;
    private String price;
    private String height;
    private String weight;
    private String length;
    private String imagePath;
}
