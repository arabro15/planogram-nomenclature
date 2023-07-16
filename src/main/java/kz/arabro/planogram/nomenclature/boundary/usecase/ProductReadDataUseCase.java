package kz.arabro.planogram.nomenclature.boundary.usecase;

import kz.arabro.planogram.nomenclature.domain.entity.Product;

import java.util.List;

public interface ProductReadDataUseCase {

    Product findByID(String productID);
    Product findByCode1C(String code1C);
    List<Product> findAll();
    List<Product> findAllByProducer(String producerID);
    List<Product> findAllByCategory(String categoryID);
    List<Product> findAllByBrand(String brandID);

}
