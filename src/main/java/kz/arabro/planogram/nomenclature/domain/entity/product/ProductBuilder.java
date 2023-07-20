package kz.arabro.planogram.nomenclature.domain.entity.product;

import kz.arabro.planogram.nomenclature.domain.entity.brand.Brand;
import kz.arabro.planogram.nomenclature.domain.entity.category.Category;
import kz.arabro.planogram.nomenclature.domain.entity.producer.Producer;

public class ProductBuilder {
    private ProductID productID;
    private String code1C;
    private Name rusName;
    private Name kazName;
    private Category category;
    private Brand brand;
    private Producer producer;
    private Barcode barcode;
    private Price price;
    private Size size;
    private String imagePath;

    public ProductBuilder setProductID(ProductID productID) {
        this.productID = productID;
        return this;
    }

    public ProductBuilder setCode1C(String code1C) {
        this.code1C = code1C;
        return this;
    }

    public ProductBuilder setRusName(Name rusName) {
        this.rusName = rusName;
        return this;
    }

    public ProductBuilder setKazName(Name kazName) {
        this.kazName = kazName;
        return this;
    }

    public ProductBuilder setCategory(Category category) {
        this.category = category;
        return this;
    }

    public ProductBuilder setBrand(Brand brand) {
        this.brand = brand;
        return this;
    }

    public ProductBuilder setProducer(Producer producer) {
        this.producer = producer;
        return this;
    }

    public ProductBuilder setBarcode(Barcode barcode) {
        this.barcode = barcode;
        return this;
    }

    public ProductBuilder setPrice(Price price) {
        this.price = price;
        return this;
    }

    public ProductBuilder setSize(Size size) {
        this.size = size;
        return this;
    }

    public ProductBuilder setImagePath(String imagePath) {
        this.imagePath = imagePath;
        return this;
    }

    //CR: метод в билдере принято называть Build()
    public Product build() {
        checkRequiredFields();

        return new Product(
                productID,
                code1C,
                rusName,
                kazName,
                category,
                brand,
                producer,
                barcode,
                price,
                size,
                imagePath
        );
    }

    private void checkRequiredFields() {
        if (code1C == null) {
            throw ProductError.errNullCode1CProductValue();
        }
        if (rusName == null || kazName == null) {
            throw ProductError.errNullNameProductValue();
        }
        if (category == null) {
            throw ProductError.errNullCategoryProductValue();
        }
        if (brand == null) {
            throw ProductError.errNullBrandProductValue();
        }
        if (producer == null) {
            throw ProductError.errNullProducerProductValue();
        }
        if (barcode == null) {
            throw ProductError.errNullBarcodeProductValue();
        }
        if (size == null) {
            throw ProductError.errNullSizeProductValue();
        }
    }
//CR: пустые строки следует убрать

}