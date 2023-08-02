package kz.arabro.planogram.nomenclature.adapter.repository.jpa;

import kz.arabro.planogram.nomenclature.adapter.repository.model.CategoryDbModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CategoryDao extends JpaRepository<CategoryDbModel, UUID> {

    void deleteByParentID(UUID parentID);
    List<CategoryDbModel> findByParentID(UUID parentID);

    @Modifying
    @Query("UPDATE CategoryDbModel " +
            "SET name = :name, color = :color, parentID = :parentID " +
            "WHERE id = :id")
    void updateById(@Param(value = "id") UUID id,
                    @Param(value = "name") String name,
                    @Param(value = "color") String color,
                    @Param(value = "parentID") UUID parentID);

}
