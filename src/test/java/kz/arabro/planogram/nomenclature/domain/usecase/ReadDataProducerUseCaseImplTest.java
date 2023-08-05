package kz.arabro.planogram.nomenclature.domain.usecase;

import kz.arabro.planogram.nomenclature.boundary.repository.ProducerRepository;
import kz.arabro.planogram.nomenclature.boundary.usecase.ReadDataProducerUseCase;
import kz.arabro.planogram.nomenclature.domain.entity.producer.Producer;
import kz.arabro.planogram.nomenclature.domain.entity.producer.ProducerError;
import kz.arabro.planogram.nomenclature.domain.entity.producer.ProducerID;
import kz.arabro.planogram.nomenclature.domain.exception.CodedException;
import kz.arabro.planogram.nomenclature.testdouble.entity.ProducerStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReadDataProducerUseCaseImplTest {

    @Mock
    private ProducerRepository producerRepository;

    private ReadDataProducerUseCase readDataProducerUseCase;

    @BeforeEach
    void setUp() {
        this.readDataProducerUseCase = new ReadDataProducerUseCaseImpl(producerRepository);
    }

    @Test
    void findByID_ProducerIDStrIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> readDataProducerUseCase.findByID(null));
        assertEquals(ProducerError.PRODUCER_ID_VALUE_IS_REQUIRED, ex.getCode());
    }

    @Test
    void findByID_ProducerIsNotFoundByID_ThrowEx() {
        var producerID = ProducerID.newID().getValue().toString();

        when(producerRepository.findByID(any(ProducerID.class))).thenReturn(Optional.empty());

        var ex = assertThrows(CodedException.class, () -> readDataProducerUseCase.findByID(producerID));
        assertEquals(UseCaseError.PRODUCER_IS_NOT_FOUND, ex.getCode());
    }

    @Test
    void findByID_ProducerExists_ReturnProducer() {
        var producer = ProducerStub.getProducer();
        var producerID = producer.getId().getValue().toString();

        when(producerRepository.findByID(any(ProducerID.class))).thenReturn(Optional.of(producer));

        var actualProducer = readDataProducerUseCase.findByID(producerID);
        assertNotNull(actualProducer);
        assertEquals(producer, actualProducer);
    }

    @Test
    void findAll_ProducerRepositoryIsRuntimeEx_ThrowEx() {
        when(producerRepository.findAll()).thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class, () -> readDataProducerUseCase.findAll());
    }

    @Test
    void findAll_ProducersIsEmpty_ReturnNull() {
        List<Producer> producers = emptyList();
        when(producerRepository.findAll()).thenReturn(producers);

        var actualProducers = readDataProducerUseCase.findAll();

        assertNotNull(actualProducers);
        assertTrue(actualProducers.isEmpty());
    }

    @Test
    void findAll_ProducersExists_ReturnProducers() {
        var producers = ProducerStub.getProducers(5);
        when(producerRepository.findAll()).thenReturn(producers);

        var actualProducers = readDataProducerUseCase.findAll();

        assertEquals(producers, actualProducers);
    }


}