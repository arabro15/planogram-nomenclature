package kz.arabro.planogram.nomenclature.adapter.repository.converter;

import kz.arabro.planogram.nomenclature.adapter.repository.RepositoryError;
import kz.arabro.planogram.nomenclature.adapter.repository.model.ProductDbModel;
import kz.arabro.planogram.nomenclature.domain.entity.*;

import java.util.List;

public class ProductConverter {

    public static Product toEntity(ProductDbModel productDbModel) {
        if (productDbModel == null) {
            throw RepositoryError.errProductDbModelIsRequired();
        }

        var productId = ProductID.from(productDbModel.getId().toString());
        var code1C = productDbModel.getCode1C();
        var rusName = Name.of(productDbModel.getRusName());
        var kazName = Name.of(productDbModel.getKazName());

        var category = new CategoryBuilder().
                setID(CategoryID.from(productDbModel.getCategory())).
                build();
        var brand = new BrandBuilder().
                setID(BrandID.from(productDbModel.getBrand())).
                build();
        var producer = new ProducerBuilder().
                setId(ProducerID.from(productDbModel.getProducer())).
                build();

        var barcode = Barcode.of(productDbModel.getBarcode());
        var price = Price.of(productDbModel.getPrice());

        var height = Integer.parseInt(productDbModel.getHeight());
        var weight = Integer.parseInt(productDbModel.getWeight());
        var length = Integer.parseInt(productDbModel.getLength());
        var size = Size.of(height, weight, length);

        var imagePath = productDbModel.getImagePath();

        return new ProductBuilder().
                setProductID(productId).
                setCode1C(code1C).
                setRusName(rusName).
                setKazName(kazName).
                setCategory(category).
                setBrand(brand).
                setProducer(producer).
                setBarcode(barcode).
                setPrice(price).
                setSize(size).
                setImagePath(imagePath).
                createProduct();
    }

    public static List<Product> toEntities(List<ProductDbModel> productDbModels) {
        if (productDbModels == null) {
            throw RepositoryError.errProductDbModelsIsRequired();
        }

        return productDbModels.stream().
                map(ProductConverter::toEntity).
                toList();
    }

    public static ProductDbModel toModel(Product product) {
        if (product == null) {
            throw RepositoryError.errProductIsRequired();
        }

        var productID = product.getProductID().getValue();
        var code1C = product.getCode1C();
        var rusName = product.getRusName().getValue();
        var kazName = product.getKazName().getValue();
        var categoryID = product.getCategory().getId().getValue().toString();
        var brandID = product.getBrand().getId().getValue().toString();
        var producerID = product.getProducer().getId().getValue().toString();
        var barcode = product.getBarcode().getValue();
        var price = product.getPrice().getPrice();
        var height = String.valueOf(product.getSize().getHeight());
        var weight = String.valueOf(product.getSize().getWeight());
        var length = String.valueOf(product.getSize().getLength());
        var imagePath = product.getImagePath();

        var productDbModel = new ProductDbModel();
        productDbModel.setId(productID);
        productDbModel.setCode1C(code1C);
        productDbModel.setRusName(rusName);
        productDbModel.setKazName(kazName);
        productDbModel.setCategory(categoryID);
        productDbModel.setBrand(brandID);
        productDbModel.setProducer(producerID);
        productDbModel.setBarcode(barcode);
        productDbModel.setPrice(price);
        productDbModel.setHeight(height);
        productDbModel.setWeight(weight);
        productDbModel.setLength(length);
        productDbModel.setImagePath(imagePath);

        return productDbModel;
    }

    public static List<ProductDbModel> toModels(List<Product> products) {
        if (products == null) {
            throw RepositoryError.errProductsIsRequired();
        }

        return products.stream().
                map(ProductConverter::toModel).
                toList();
    }

    private ProductConverter() {}

}