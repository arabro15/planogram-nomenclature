package kz.arabro.planogram.nomenclature.domain.usecase;

import kz.arabro.planogram.nomenclature.adapter.repository.RepositoryError;
import kz.arabro.planogram.nomenclature.boundary.repository.ProducerRepository;
import kz.arabro.planogram.nomenclature.boundary.usecase.ProducerDeleteUseCase;
import kz.arabro.planogram.nomenclature.domain.entity.ProducerID;
import org.springframework.stereotype.Service;

@Service
public class ProducerDeleteUseCaseImpl implements ProducerDeleteUseCase {

    private final ProducerRepository producerRepository;

    public ProducerDeleteUseCaseImpl(ProducerRepository producerRepository) {
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
