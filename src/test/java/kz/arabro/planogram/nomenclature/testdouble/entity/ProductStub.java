package kz.arabro.planogram.nomenclature.testdouble.entity;

import kz.arabro.planogram.nomenclature.domain.entity.product.Product;
import kz.arabro.planogram.nomenclature.domain.entity.product.ProductBuilder;
import kz.arabro.planogram.nomenclature.domain.entity.product.ProductID;
import kz.arabro.planogram.nomenclature.util.generator.StringGenerator;

import java.util.ArrayList;
import java.util.List;

public class ProductStub {

    public static Product getProduct() {
        var id = ProductID.newID();
        var code1c = "123456789";
        var rusName = NameStub.getName();
        var kazName = NameStub.getName();
        var category = CategoryStub.getCategory();
        var brand = BrandStub.getBrand();
        var producer = ProducerStub.getProducer();
        var barcode = BarcodeStub.getBarcode();
        var price = PriceStub.getPrice();
        var size = SizeStub.getSize();
        var imagePath = StringGenerator.getRandomString();

        return new ProductBuilder().
                setProductID(id).
                setCode1C(code1c).
                setRusName(rusName).
                setKazName(kazName).
                setCategory(category).
                setBrand(brand).
                setProducer(producer).
                setBarcode(barcode).
                setPrice(price).
                setSize(size).
                setImagePath(imagePath).
                build();
    }

    public static List<Product> getProducts(int count) {
        var products = new ArrayList<Product>(count);

        for (int i = 0; i < count; i++) {
            products.add(getProduct());
        }

        return products;
    }
}
