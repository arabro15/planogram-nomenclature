package kz.arabro.planogram.nomenclature.domain.usecase;

import kz.arabro.planogram.nomenclature.boundary.model.ProducerCreateInfo;
import kz.arabro.planogram.nomenclature.boundary.repository.ProducerRepository;
import kz.arabro.planogram.nomenclature.boundary.usecase.CreateProducerUseCase;
import kz.arabro.planogram.nomenclature.domain.entity.producer.Producer;
import kz.arabro.planogram.nomenclature.domain.exception.CodedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CreateProducerUseCaseImplTest {

    @Mock
    private ProducerRepository producerRepository;

    private CreateProducerUseCase createProducerUseCase;

    @BeforeEach
    void setUp() {
        this.createProducerUseCase = new CreateProducerUseCaseImpl(producerRepository);
    }

    @Test
    void execute_InfoIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> createProducerUseCase.execute(null));
        assertEquals(UseCaseError.PRODUCER_CREATE_INFO_IS_REQUIRED, ex.getCode());
    }

    @Test
    void execute_SaveProducerIsThrowEx_ThrowEx() {
        doThrow(RuntimeException.class).when(producerRepository).save(any(Producer.class));

        var info = new ProducerCreateInfo("RG Brands");

        assertThrows(RuntimeException.class, () -> createProducerUseCase.execute(info));
    }

    @Test
    void execute_NoThrowEx_ReturnProducer() {
        var info = new ProducerCreateInfo("RG Brands");

        var producer = createProducerUseCase.execute(info);
        assertNotNull(producer);
        assertEquals(producer.getName().getValue(), info.getName());
        verify(producerRepository, times(1)).save(producer);
    }
}
