package kz.arabro.planogram.nomenclature.adapter.repository.jpa;

import kz.arabro.planogram.nomenclature.adapter.repository.model.BrandDbModel;
import kz.arabro.planogram.nomenclature.adapter.repository.model.CategoryDbModel;
import kz.arabro.planogram.nomenclature.adapter.repository.model.ProducerDbModel;
import kz.arabro.planogram.nomenclature.adapter.repository.model.ProductDbModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductDao extends JpaRepository<ProductDbModel, UUID> {
    Optional<ProductDbModel> findByCode1C(String code1C);

    List<ProductDbModel> findAllByCategory(CategoryDbModel category);

    List<ProductDbModel> findAllByProducer(ProducerDbModel producer);

    List<ProductDbModel> findAllByBrand(BrandDbModel brand);

    @Modifying
    @Query("UPDATE ProductDbModel " +
            "SET code1C = :code1c, " +
            "rusName = :rusname, " +
            "kazName = :kazname, " +
            "category = :category, " +
            "brand = :brand, " +
            "producer = :producer, " +
            "barcode = :barcode, " +
            "price = :price, " +
            "height = :height," +
            "weight = :weight," +
            "length = :length," +
            "imagePath = :imagePath" +
            " WHERE id = :id")
    void updateById(@Param(value = "id") UUID id,
                    @Param(value = "code1c") String code1c,
                    @Param(value = "rusname") String rusName,
                    @Param(value = "kazname") String kazName,
                    @Param(value = "category") CategoryDbModel category,
                    @Param(value = "brand") BrandDbModel brand,
                    @Param(value = "producer") ProducerDbModel producer,
                    @Param(value = "barcode") String barcode,
                    @Param(value = "price") String price,
                    @Param(value = "height") String height,
                    @Param(value = "weight") String weight,
                    @Param(value = "length") String length,
                    @Param(value = "imagePath") String imagePath
    );
}
