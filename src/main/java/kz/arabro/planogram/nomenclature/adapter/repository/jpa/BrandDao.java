package kz.arabro.planogram.nomenclature.adapter.repository.jpa;

import kz.arabro.planogram.nomenclature.adapter.repository.model.BrandDbModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BrandDao extends JpaRepository<BrandDbModel, UUID> {
}
