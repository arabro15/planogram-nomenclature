package kz.arabro.planogram.nomenclature.domain.usecase;

import kz.arabro.planogram.nomenclature.boundary.model.ProducerEditInfo;
import kz.arabro.planogram.nomenclature.boundary.repository.ProducerRepository;
import kz.arabro.planogram.nomenclature.boundary.usecase.UpdateProducerUseCase;
import kz.arabro.planogram.nomenclature.domain.entity.producer.Producer;
import kz.arabro.planogram.nomenclature.domain.exception.CodedException;
import kz.arabro.planogram.nomenclature.testdouble.entity.ProducerStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UpdateProducerUseCaseImplTest {

    @Mock
    private ProducerRepository producerRepository;

    private UpdateProducerUseCase updateProducerUseCase;

    @BeforeEach
    void setUp() {
        this.updateProducerUseCase = new UpdateProducerUseCaseImpl(producerRepository);
    }

    @Test
    void update_ProducerEditInfoIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> updateProducerUseCase.update(null));
        assertEquals(UseCaseError.PRODUCER_EDIT_INFO_IS_REQUIRED, ex.getCode());
    }

    @Test
    void update_ValueIsValid_UpdateProducer() {
        var producer = ProducerStub.getProducer();

        var producerIDStr = producer.getId().getValue().toString();
        var name = producer.getName().getValue();

        var producerEditInfo = new ProducerEditInfo();
        producerEditInfo.setProducerID(producerIDStr);
        producerEditInfo.setName(name);

        updateProducerUseCase.update(producerEditInfo);
        verify(producerRepository, times(1)).update(any(Producer.class));
    }

}