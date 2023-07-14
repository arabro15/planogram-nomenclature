package kz.arabro.planogram.nomenclature.domain.usecase;

import kz.arabro.planogram.nomenclature.boundary.repository.ProducerRepository;
import kz.arabro.planogram.nomenclature.boundary.usecase.ProducerReadDataUseCase;
import kz.arabro.planogram.nomenclature.domain.entity.Producer;
import kz.arabro.planogram.nomenclature.domain.entity.ProducerID;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProducerReadDataUseCaseImpl implements ProducerReadDataUseCase {

    private final ProducerRepository producerRepository;

    public ProducerReadDataUseCaseImpl(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    @Override
    public Producer findByID(String producerIDStr) {
        var producerID = ProducerID.from(producerIDStr);
        var producerOpt = producerRepository.findByID(producerID);
        return producerOpt.orElseThrow(() -> UseCaseError.errProducerNotFound(producerID));
    }

    @Override
    public List<Producer> findAll() {
        return producerRepository.findAll();
    }
}
