package kz.arabro.planogram.nomenclature.adapter.controller.converter;

import kz.arabro.planogram.nomenclature.adapter.controller.ControllerError;
import kz.arabro.planogram.nomenclature.adapter.controller.request.CreateProductRequest;
import kz.arabro.planogram.nomenclature.adapter.controller.request.EditProductRequest;
import kz.arabro.planogram.nomenclature.boundary.model.ProductCreateInfo;
import kz.arabro.planogram.nomenclature.boundary.model.ProductEditInfo;

public class ProductRequestConverter {

    public static ProductCreateInfo createProductRequestToModel(CreateProductRequest request) {
        if (request == null) {
            throw ControllerError.errCreateProductRequestIsRequired();
        }

        var code1C = request.getCode1C();
        var rusName = request.getRusName();
        var kazName = request.getKazName();
        var category = request.getCategoryID();
        var brand = request.getBrandID();
        var producer = request.getProducerID();
        var barcode = request.getBarcode();
        var price = request.getPrice();
        var height = request.getHeight();
        var weight = request.getWeight();
        var length = request.getLength();
        var imagePath = request.getImagePath();

        var productCreateInfo = new ProductCreateInfo();
        productCreateInfo.setCode1C(code1C);
        productCreateInfo.setRusName(rusName);
        productCreateInfo.setKazName(kazName);
        productCreateInfo.setCategory(category);
        productCreateInfo.setBrand(brand);
        productCreateInfo.setProducer(producer);
        productCreateInfo.setBarcode(barcode);
        productCreateInfo.setPrice(price);
        productCreateInfo.setHeight(height);
        productCreateInfo.setWeight(weight);
        productCreateInfo.setLength(length);
        productCreateInfo.setImagePath(imagePath);

        return productCreateInfo;
    }

    public static ProductEditInfo editProductRequestToModel(EditProductRequest request) {
        if (request == null) {
            throw ControllerError.errEditProductRequestIsRequired();
        }

        var productID = request.getProductID();
        var code1C = request.getCode1C();
        var rusName = request.getRusName();
        var kazName = request.getKazName();
        var category = request.getCategoryID();
        var brand = request.getBrandID();
        var producer = request.getProducerID();
        var barcode = request.getBarcode();
        var price = request.getPrice();
        var height = request.getHeight();
        var weight = request.getWeight();
        var length = request.getLength();
        var imagePath = request.getImagePath();

        var productEditInfo = new ProductEditInfo();
        productEditInfo.setId(productID);
        productEditInfo.setCode1C(code1C);
        productEditInfo.setRusName(rusName);
        productEditInfo.setKazName(kazName);
        productEditInfo.setCategory(category);
        productEditInfo.setBrand(brand);
        productEditInfo.setProducer(producer);
        productEditInfo.setBarcode(barcode);
        productEditInfo.setPrice(price);
        productEditInfo.setHeight(height);
        productEditInfo.setWeight(weight);
        productEditInfo.setLength(length);
        productEditInfo.setImagePath(imagePath);

        return productEditInfo;
    }

    private ProductRequestConverter() {}

}
