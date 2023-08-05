package kz.arabro.planogram.nomenclature.domain.usecase;

import kz.arabro.planogram.nomenclature.boundary.repository.ProducerRepository;
import kz.arabro.planogram.nomenclature.boundary.usecase.DeleteProducerUseCase;
import kz.arabro.planogram.nomenclature.domain.exception.CodedException;
import kz.arabro.planogram.nomenclature.testdouble.entity.ProducerStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteProducerUseCaseImplTest {

    @Mock
    private ProducerRepository producerRepository;

    private DeleteProducerUseCase deleteProducerUseCase;

    @BeforeEach
    void setUp() {
        this.deleteProducerUseCase = new DeleteProducerUseCaseImpl(producerRepository);
    }

    @Test
    void deleteProducerByID_ProducerIDIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> deleteProducerUseCase.deleteProducerByID(null));
        assertEquals(UseCaseError.PRODUCER_ID_IS_REQUIRED, ex.getCode());
    }

    @Test
    void deleteProducerByID_ValueIsValid_DeleteProducer() {
        var producerID = ProducerStub.getProducer().getId();
        var producerIDStr = producerID.getValue().toString();

        deleteProducerUseCase.deleteProducerByID(producerIDStr);
        verify(producerRepository, times(1)).deleteById(producerID);
    }
}