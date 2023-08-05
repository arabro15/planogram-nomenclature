package kz.arabro.planogram.nomenclature.adapter.repository.jpa;

import kz.arabro.planogram.nomenclature.adapter.repository.model.BrandDbModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface BrandDao extends JpaRepository<BrandDbModel, UUID> {
    @Modifying
    @Query("""
            UPDATE BrandDbModel 
            SET name = :name 
            WHERE id = :id
            """)
    void updateById(@Param(value = "id") UUID id, @Param(value = "name") String name);
}
