package kz.arabro.planogram.nomenclature.adapter.repository.jpa;

import kz.arabro.planogram.nomenclature.adapter.repository.model.ProductDbModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductDao extends JpaRepository<ProductDbModel, UUID> {
    Optional<ProductDbModel> findByCode1C(String code1C);
    List<ProductDbModel> findAllByCategory(String categoryID);
    List<ProductDbModel> findAllByProducer(String producerID);
    List<ProductDbModel> findAllByBrand(String brandID);
}
