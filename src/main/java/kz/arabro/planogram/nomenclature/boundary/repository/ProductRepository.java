package kz.arabro.planogram.nomenclature.boundary.repository;

import kz.arabro.planogram.nomenclature.domain.entity.*;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    void save(Product product);
    void deleteById(ProductID productID);
    void update(Product product);
    Optional<Product> findByID(ProductID productID);
    Optional<Product> findByCode1C(String code1C);
    List<Product> findAll();
    List<Product> findAllByProducer(ProducerID producerID);
    List<Product> findAllByCategory(CategoryID categoryID);
    List<Product> findAllByBrand(BrandID brandID);


}
