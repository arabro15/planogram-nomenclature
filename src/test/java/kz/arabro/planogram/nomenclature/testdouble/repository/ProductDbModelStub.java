package kz.arabro.planogram.nomenclature.testdouble.repository;

import kz.arabro.planogram.nomenclature.adapter.repository.converter.BrandConverter;
import kz.arabro.planogram.nomenclature.adapter.repository.converter.CategoryConverter;
import kz.arabro.planogram.nomenclature.adapter.repository.converter.ProducerConverter;
import kz.arabro.planogram.nomenclature.adapter.repository.model.ProductDbModel;
import kz.arabro.planogram.nomenclature.testdouble.entity.ProductStub;

import java.util.ArrayList;
import java.util.List;

public class ProductDbModelStub {

    public static ProductDbModel getProductDbModel() {
        var product = ProductStub.getProduct();

        var productID = product.getProductID().getValue();
        var code1C = product.getCode1C();
        var rusName = product.getRusName().getValue();
        var kazName = product.getKazName().getValue();
        var category = CategoryConverter.toModel(product.getCategory());
        var brand = BrandConverter.toModel(product.getBrand());
        var producerID = ProducerConverter.toModel(product.getProducer());
        var barcode = product.getBarcode().getValue();
        var price = product.getPrice().getValue();
        var height = String.valueOf(product.getSize().getHeight());
        var weight = String.valueOf(product.getSize().getWeight());
        var length = String.valueOf(product.getSize().getLength());
        var imagePath = product.getImagePath();

        var productDbModel = new ProductDbModel();
        productDbModel.setId(productID);
        productDbModel.setCode1C(code1C);
        productDbModel.setRusName(rusName);
        productDbModel.setKazName(kazName);
        productDbModel.setCategory(category);
        productDbModel.setBrand(brand);
        productDbModel.setProducer(producerID);
        productDbModel.setBarcode(barcode);
        productDbModel.setPrice(price);
        productDbModel.setHeight(height);
        productDbModel.setWeight(weight);
        productDbModel.setLength(length);
        productDbModel.setImagePath(imagePath);

        return productDbModel;
    }

    public static List<ProductDbModel> getProductDbModels(int count) {
        var productDbModels = new ArrayList<ProductDbModel>(count);

        for (int i = 0; i < count; i++) {
            productDbModels.add(getProductDbModel());
        }

        return productDbModels;
    }
}
