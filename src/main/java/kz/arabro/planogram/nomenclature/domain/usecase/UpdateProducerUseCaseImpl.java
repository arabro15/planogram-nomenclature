package kz.arabro.planogram.nomenclature.domain.usecase;

import kz.arabro.planogram.nomenclature.boundary.model.ProducerEditInfo;
import kz.arabro.planogram.nomenclature.boundary.repository.ProducerRepository;
import kz.arabro.planogram.nomenclature.boundary.usecase.UpdateProducerUseCase;
import kz.arabro.planogram.nomenclature.domain.entity.product.Name;
import kz.arabro.planogram.nomenclature.domain.entity.producer.ProducerBuilder;
import kz.arabro.planogram.nomenclature.domain.entity.producer.ProducerID;
import org.springframework.stereotype.Service;

@Service
public class UpdateProducerUseCaseImpl implements UpdateProducerUseCase {

    private final ProducerRepository producerRepository;

    public UpdateProducerUseCaseImpl(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    @Override
    public void update(ProducerEditInfo info) {
        if (info == null) {
            throw UseCaseError.errProducerEditInfoIsRequired();
        }

        var producerID = ProducerID.from(info.getProducerID());
        var name = Name.of(info.getName());

        var producer = new ProducerBuilder().
                setId(producerID).
                setName(name).
                build();

        producerRepository.update(producer);
    }
}
