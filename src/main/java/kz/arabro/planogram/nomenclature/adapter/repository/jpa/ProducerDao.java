package kz.arabro.planogram.nomenclature.adapter.repository.jpa;

import kz.arabro.planogram.nomenclature.adapter.repository.model.ProducerDbModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProducerDao extends JpaRepository<ProducerDbModel, UUID> {
}
