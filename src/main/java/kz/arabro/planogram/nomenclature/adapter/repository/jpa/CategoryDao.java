package kz.arabro.planogram.nomenclature.adapter.repository.jpa;

import kz.arabro.planogram.nomenclature.adapter.repository.model.CategoryDbModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CategoryDao extends JpaRepository<CategoryDbModel, UUID> {
    void deleteAllByParentID(UUID parentID);
    List<CategoryDbModel> findAllByParentID(UUID parentID);
}
