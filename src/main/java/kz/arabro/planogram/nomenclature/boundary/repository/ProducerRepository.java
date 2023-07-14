package kz.arabro.planogram.nomenclature.boundary.repository;

import kz.arabro.planogram.nomenclature.domain.entity.Producer;
import kz.arabro.planogram.nomenclature.domain.entity.ProducerID;

import java.util.List;
import java.util.Optional;

public interface ProducerRepository {
    void save(Producer producer);
    void deleteById(ProducerID producerID);
    void update(Producer producer);
    Optional<Producer> findByID(ProducerID producerID);
    List<Producer> findAll();
}
