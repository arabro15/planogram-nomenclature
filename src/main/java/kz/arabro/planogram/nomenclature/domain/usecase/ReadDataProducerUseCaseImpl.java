package kz.arabro.planogram.nomenclature.domain.usecase;

import kz.arabro.planogram.nomenclature.boundary.repository.ProducerRepository;
import kz.arabro.planogram.nomenclature.boundary.usecase.ReadDataProducerUseCase;
import kz.arabro.planogram.nomenclature.domain.entity.producer.Producer;
import kz.arabro.planogram.nomenclature.domain.entity.producer.ProducerID;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadDataProducerUseCaseImpl implements ReadDataProducerUseCase {

    private final ProducerRepository producerRepository;

    public ReadDataProducerUseCaseImpl(ProducerRepository producerRepository) {
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
