package kz.arabro.planogram.nomenclature.domain.usecase;

import kz.arabro.planogram.nomenclature.adapter.repository.RepositoryError;
import kz.arabro.planogram.nomenclature.boundary.repository.ProducerRepository;
import kz.arabro.planogram.nomenclature.boundary.usecase.DeleteProducerUseCase;
import kz.arabro.planogram.nomenclature.domain.entity.producer.ProducerID;
import org.springframework.stereotype.Service;

@Service
public class DeleteProducerUseCaseImpl implements DeleteProducerUseCase {

    private final ProducerRepository producerRepository;

    public DeleteProducerUseCaseImpl(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    @Override
    public void deleteProducerByID(String producerID) {
        if (producerID == null) {
            throw RepositoryError.errProducerIdIsRequired();
        }

        producerRepository.deleteById(ProducerID.from(producerID));
    }
}
