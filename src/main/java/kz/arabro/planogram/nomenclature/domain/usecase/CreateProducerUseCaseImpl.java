package kz.arabro.planogram.nomenclature.domain.usecase;

import kz.arabro.planogram.nomenclature.boundary.model.ProducerCreateInfo;
import kz.arabro.planogram.nomenclature.boundary.repository.ProducerRepository;
import kz.arabro.planogram.nomenclature.boundary.usecase.CreateProducerUseCase;
import kz.arabro.planogram.nomenclature.domain.entity.product.Name;
import kz.arabro.planogram.nomenclature.domain.entity.producer.Producer;
import kz.arabro.planogram.nomenclature.domain.entity.producer.ProducerBuilder;
import kz.arabro.planogram.nomenclature.domain.entity.producer.ProducerID;
import org.springframework.stereotype.Service;

@Service
public class CreateProducerUseCaseImpl implements CreateProducerUseCase {

    private final ProducerRepository producerRepository;

    public CreateProducerUseCaseImpl(ProducerRepository producerRepository) {
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
