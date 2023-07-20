package kz.arabro.planogram.nomenclature.adapter.controller.converter;

import kz.arabro.planogram.nomenclature.adapter.controller.ControllerError;
import kz.arabro.planogram.nomenclature.adapter.controller.response.ProductResponse;
import kz.arabro.planogram.nomenclature.domain.entity.product.Product;

import java.util.List;

public class ProductResponseConverter {

    public static ProductResponse productToResponse(Product product) {
        if (product == null) {
            throw ControllerError.errProductIsRequired();
        }

        var productID = product.getProductID().getValue().toString();
        var code1C = product.getCode1C();
        var rusName = product.getRusName().getValue();
        var kazName = product.getKazName().getValue();
        var category = CategoryResponseConverter.categoryToResponse(product.getCategory());
        var brand = BrandResponseConverter.brandToResponse(product.getBrand());
        var producer = ProducerResponseConverter.producerToResponse(product.getProducer());
        var barcode = product.getBarcode().getValue();
        var price = product.getPrice().getPrice();
        var height = String.valueOf(product.getSize().getHeight());
        var weight = String.valueOf(product.getSize().getWeight());
        var length = String.valueOf(product.getSize().getLength());
        var imagePath = product.getImagePath();

        var productResponse = new ProductResponse();
        productResponse.setProductID(productID);
        productResponse.setCode1C(code1C);
        productResponse.setRusName(rusName);
        productResponse.setKazName(kazName);
        productResponse.setCategory(category);
        productResponse.setBrand(brand);
        productResponse.setProducer(producer);
        productResponse.setBarcode(barcode);
        productResponse.setPrice(price);
        productResponse.setHeight(height);
        productResponse.setWeight(weight);
        productResponse.setLength(length);
        productResponse.setImagePath(imagePath);

        return productResponse;
    }

    public static List<ProductResponse> productsToResponses(List<Product> products) {
        if (products == null) {
            throw ControllerError.errProductsIsRequired();
        }

        return products.stream().
                map(ProductResponseConverter::productToResponse).
                toList();
    }

    private ProductResponseConverter() {}
}
