package kz.arabro.planogram.nomenclature.boundary.repository;

import kz.arabro.planogram.nomenclature.domain.entity.brand.BrandID;
import kz.arabro.planogram.nomenclature.domain.entity.category.CategoryID;
import kz.arabro.planogram.nomenclature.domain.entity.producer.ProducerID;
import kz.arabro.planogram.nomenclature.domain.entity.product.Product;
import kz.arabro.planogram.nomenclature.domain.entity.product.ProductID;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    void save(Product product);
    void deleteById(ProductID productID);
    void update(Product product);
    Optional<Product> findByID(ProductID productID);
    Optional<Product> findByCode1C(String code1C);
    List<Product> findAll();
    List<Product> findByProducer(ProducerID producerID);
    List<Product> findByCategory(CategoryID categoryID);
    List<Product> findByBrand(BrandID brandID);
}
