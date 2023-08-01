package kz.arabro.planogram.nomenclature.boundary.usecase;

import kz.arabro.planogram.nomenclature.domain.entity.product.Product;

import java.util.List;

public interface ReadDataProductUseCase {

    Product findByID(String productID);
    Product findByCode1C(String code1C);
    List<Product> findAll();
    List<Product> findByProducer(String producerID);
    List<Product> findByCategory(String categoryID);
    List<Product> findByBrand(String brandID);

}
