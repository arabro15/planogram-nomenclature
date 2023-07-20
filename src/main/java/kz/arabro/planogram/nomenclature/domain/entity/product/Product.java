package kz.arabro.planogram.nomenclature.domain.entity.product;

import kz.arabro.planogram.nomenclature.domain.entity.brand.Brand;
import kz.arabro.planogram.nomenclature.domain.entity.category.Category;
import kz.arabro.planogram.nomenclature.domain.entity.producer.Producer;

public class Product {

    private final ProductID productID;
    private final String code1C;
    private Name rusName;
    private Name kazName;
    private Category category;
    private Brand brand;
    private Producer producer;
    private Barcode barcode;
    private Price price;
    private Size size;
    private String imagePath;

    Product(
            ProductID productID,
            String code1C,
            Name rusName,
            Name kazName,
            Category category,
            Brand brand,
            Producer producer,
            Barcode barcode,
            Price price,
            Size size,
            String imagePath
    ) {
        this.productID = productID;
        this.code1C = code1C;
        this.rusName = rusName;
        this.kazName = kazName;
        this.category = category;
        this.brand = brand;
        this.producer = producer;
        this.barcode = barcode;
        this.price = price;
        this.size = size;
        this.imagePath = imagePath;
    }

    public ProductID getProductID() {
        return productID;
    }

    public String getCode1C() {
        return code1C;
    }

    public Name getRusName() {
        return rusName;
    }

    public Name getKazName() {
        return kazName;
    }

    public Category getCategory() {
        return category;
    }

    public Brand getBrand() {
        return brand;
    }

    public Producer getProducer() {
        return producer;
    }

    public Barcode getBarcode() {
        return barcode;
    }

    public Size getSize() {
        return size;
    }

    public String getImagePath() {
        return imagePath;
    }

    public Price getPrice() {
        return price;
    }
}
