package kz.arabro.planogram.nomenclature.domain.usecase;

import kz.arabro.planogram.nomenclature.boundary.model.ProducerCreateInfo;
import kz.arabro.planogram.nomenclature.boundary.repository.ProducerRepository;
import kz.arabro.planogram.nomenclature.boundary.usecase.ProducerCreatorUseCase;
import kz.arabro.planogram.nomenclature.domain.entity.Name;
import kz.arabro.planogram.nomenclature.domain.entity.Producer;
import kz.arabro.planogram.nomenclature.domain.entity.ProducerBuilder;
import kz.arabro.planogram.nomenclature.domain.entity.ProducerID;
import org.springframework.stereotype.Service;

@Service
public class ProducerCreatorUseCaseImpl implements ProducerCreatorUseCase {

    private final ProducerRepository producerRepository;

    public ProducerCreatorUseCaseImpl(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    @Override
    public Producer execute(ProducerCreateInfo info) {
        if (info == null) {
            throw UseCaseError.errProducerCreateInfoIsRequired();
        }
        var producer = new ProducerBuilder().
                setId(ProducerID.newID()).
                setName(Name.of(info.getName())).
                build();

        producerRepository.save(producer);
        return producer;
    }
}
