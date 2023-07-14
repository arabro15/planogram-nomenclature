package kz.arabro.planogram.nomenclature.boundary.usecase;

import kz.arabro.planogram.nomenclature.domain.entity.Producer;

import java.util.List;

public interface ProducerReadDataUseCase {
    Producer findByID(String producerID);
    List<Producer> findAll();
}
