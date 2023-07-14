package kz.arabro.planogram.nomenclature.boundary.usecase;

import kz.arabro.planogram.nomenclature.boundary.model.ProducerCreateInfo;
import kz.arabro.planogram.nomenclature.domain.entity.Producer;

public interface ProducerCreatorUseCase {
    Producer execute(ProducerCreateInfo info);

}
