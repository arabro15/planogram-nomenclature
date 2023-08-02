package kz.arabro.planogram.nomenclature.adapter.repository;

import kz.arabro.planogram.nomenclature.adapter.repository.jpa.ProducerDao;
import kz.arabro.planogram.nomenclature.adapter.repository.model.ProducerDbModel;
import kz.arabro.planogram.nomenclature.boundary.repository.ProducerRepository;
import kz.arabro.planogram.nomenclature.domain.entity.producer.ProducerID;
import kz.arabro.planogram.nomenclature.domain.exception.CodedException;
import kz.arabro.planogram.nomenclature.testdouble.entity.ProducerStub;
import kz.arabro.planogram.nomenclature.testdouble.repository.ProducerDbModelStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProducerRepositoryImplTest {

    @Mock
    private ProducerDao producerDao;

    @Captor
    private ArgumentCaptor<ProducerDbModel> producerDbModelCaptor;

    @Captor
    private ArgumentCaptor<UUID> uuidCaptor;

    @Captor
    private ArgumentCaptor<String> stringCaptor;

    private ProducerRepository producerRepository;

    @BeforeEach
    void setUp() {
        this.producerRepository = new ProducerRepositoryImpl(producerDao);
    }

    @Test
    void save_ProducerIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> producerRepository.save(null));
        assertEquals(RepositoryError.PRODUCER_IS_REQUIRED, ex.getCode());
    }

    @Test
    void save_ValueIsValid_SaveProducer() {
        var producer = ProducerStub.getProducer();

        producerRepository.save(producer);
        verify(producerDao).save(producerDbModelCaptor.capture());

        var producerDbModel = producerDbModelCaptor.getValue();

        var producerID = producer.getId().getValue();
        var producerName = producer.getName().getValue();

        var producerDbModelID = producerDbModel.getId();
        var producerDbModelName = producerDbModel.getName();

        assertNotNull(producerDbModel);
        assertEquals(producerID, producerDbModelID);
        assertEquals(producerName, producerDbModelName);
    }

    @Test
    void deleteById_ProducerIDIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> producerRepository.deleteById(null));
        assertEquals(RepositoryError.PRODUCER_ID_IS_REQUIRED, ex.getCode());
    }

    @Test
    void deleteByID_ValueIsValid_DeleteProducer() {
        var producerID = ProducerStub.getProducer().getId();
        var producerIDValue = producerID.getValue();

        producerRepository.deleteById(producerID);
        verify(producerDao, times(1)).deleteById(producerIDValue);
    }

    @Test
    void update_ProducerIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> producerRepository.update(null));
        assertEquals(RepositoryError.PRODUCER_IS_REQUIRED, ex.getCode());
    }

    @Test
    void update_ValueIsValid_UpdateProducer() {
        var producer = ProducerStub.getProducer();

        var producerIDValue = producer.getId().getValue();
        var producerNameStr = producer.getName().getValue();

        producerRepository.update(producer);
        verify(producerDao).updateById(uuidCaptor.capture(), stringCaptor.capture());

        var producerIDValueCaptor = uuidCaptor.getValue();
        var producerNameStrCaptor = stringCaptor.getValue();

        assertEquals(producerIDValue, producerIDValueCaptor);
        assertEquals(producerNameStr, producerNameStrCaptor);
    }

    @Test
    void findByID_ProducerIDIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> producerRepository.findByID(null));
        assertEquals(RepositoryError.PRODUCER_ID_IS_REQUIRED, ex.getCode());
    }

    @Test
    void findByID_ValueIsValid_ReturnProducer() {
        var producerDbModel = ProducerDbModelStub.getProducerDbModel();
        when(producerDao.findById(any(UUID.class))).thenReturn(Optional.of(producerDbModel));

        var producerOpt = producerRepository.findByID(ProducerID.newID());
        assertNotNull(producerOpt);
        assertTrue(producerOpt.isPresent());

        assertEquals(producerDbModel.getId(), producerOpt.get().getId().getValue());
    }

    @Test
    void findAll_NotParams_ReturnProducers() {
        var count = 5;

        var producerDbModels = ProducerDbModelStub.getProducerDbModels(count);
        when(producerDao.findAll()).thenReturn(producerDbModels);

        var producers = producerRepository.findAll();
        assertNotNull(producers);
        assertEquals(producerDbModels.size(), producers.size());
        for (int i = 0; i < count; i++) {
            assertEquals(producerDbModels.get(i).getId(), producers.get(i).getId().getValue());
            assertEquals(producerDbModels.get(i).getName(), producers.get(i).getName().getValue());
        }
    }
}