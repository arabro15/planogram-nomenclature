package kz.arabro.planogram.nomenclature.boundary.usecase;

import kz.arabro.planogram.nomenclature.boundary.model.ProducerCreateInfo;
import kz.arabro.planogram.nomenclature.domain.entity.producer.Producer;

public interface CreateProducerUseCase {
    Producer execute(ProducerCreateInfo info);

}
