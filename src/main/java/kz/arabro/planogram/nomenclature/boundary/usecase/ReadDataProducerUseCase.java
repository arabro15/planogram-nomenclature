package kz.arabro.planogram.nomenclature.boundary.usecase;

import kz.arabro.planogram.nomenclature.domain.entity.producer.Producer;

import java.util.List;

public interface ReadDataProducerUseCase {
    Producer findByID(String producerID);
    List<Producer> findAll();
}
