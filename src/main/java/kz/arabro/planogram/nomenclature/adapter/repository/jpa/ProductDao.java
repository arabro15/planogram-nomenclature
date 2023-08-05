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

    List<ProductDbModel> findByCategory(CategoryDbModel category);

    List<ProductDbModel> findByProducer(ProducerDbModel producer);

    List<ProductDbModel> findByBrand(BrandDbModel brand);

    @Modifying
    @Query("""
            UPDATE ProductDbModel
            SET code1C = :#{#dbModel.code1C},
                rusName = :#{#dbModel.rusName},
                kazName = :#{#dbModel.kazName},
                category = :#{#dbModel.category},
                brand = :#{#dbModel.brand},
                producer = :#{#dbModel.producer},
                barcode = :#{#dbModel.barcode},
                price = :#{#dbModel.price},
                height = :#{#dbModel.height},
                weight = :#{#dbModel.weight},
                length = :#{#dbModel.length},
                imagePath = :#{#dbModel.imagePath}
            WHERE id = :#{#dbModel.id}
            """)
    void updateById(@Param(value = "dbModel") ProductDbModel dbModel);
}
