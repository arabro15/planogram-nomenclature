package kz.arabro.planogram.nomenclature.adapter.repository.converter;

import kz.arabro.planogram.nomenclature.adapter.repository.RepositoryError;
import kz.arabro.planogram.nomenclature.domain.exception.CodedException;
import kz.arabro.planogram.nomenclature.testdouble.entity.ProducerStub;
import kz.arabro.planogram.nomenclature.testdouble.repository.ProducerDbModelStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProducerConverterTest {

    @Test
    void toEntity_ProducerDbModelIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> ProducerConverter.toEntity(null));
        assertEquals(RepositoryError.PRODUCER_DB_MODEL_IS_REQUIRED, ex.getCode());
    }

    @Test
    void toEntity_AllParamsIsValid_ReturnProducer() {
        var producerDbModel = ProducerDbModelStub.getProducerDbModel();
        var producer = ProducerConverter.toEntity(producerDbModel);

        var producerDbModelID = producerDbModel.getId();
        var producerDbModelName = producerDbModel.getName();

        var producerID = producer.getId().getValue();
        var producerName = producer.getName().getValue();

        assertNotNull(producer);
        assertEquals(producerDbModelID, producerID);
        assertEquals(producerDbModelName, producerName);
    }

    @Test
    void toEntities_ProducerDbModelsIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> ProducerConverter.toEntities(null));
        assertEquals(RepositoryError.PRODUCER_DB_MODELS_IS_REQUIRED, ex.getCode());
    }

    @Test
    void toEntities_AllParamsIsValid_ReturnProducerDbModels() {
        var count = 5;

        var producerDbModels = ProducerDbModelStub.getProducerDbModels(count);
        var producers = ProducerConverter.toEntities(producerDbModels);

        assertNotNull(producers);

        for (int i = 0; i < count; i++) {
            var producerDbModelID = producerDbModels.get(i).getId();
            var producerDbModelName = producerDbModels.get(i).getName();

            var producerID = producers.get(i).getId().getValue();
            var producerName = producers.get(i).getName().getValue();

            assertEquals(producerDbModelID, producerID);
            assertEquals(producerDbModelName, producerName);
        }
    }

    @Test
    void toModel_ProducerIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> ProducerConverter.toModel(null));
        assertEquals(RepositoryError.PRODUCER_IS_REQUIRED, ex.getCode());
    }

    @Test
    void toModel_AllParamsIsValid_ReturnProducerDbModel() {
        var producer = ProducerStub.getProducer();
        var producerDbModel = ProducerConverter.toModel(producer);

        var producerID = producer.getId().getValue();
        var producerName = producer.getName().getValue();

        var producerDbModelID = producerDbModel.getId();
        var producerDbModelName = producerDbModel.getName();

        assertNotNull(producerDbModel);
        assertEquals(producerID, producerDbModelID);
        assertEquals(producerName, producerDbModelName);
    }

    @Test
    void toModels_ProducersIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> ProducerConverter.toModels(null));
        assertEquals(RepositoryError.PRODUCERS_IS_REQUIRED, ex.getCode());
    }

    @Test
    void toModels_AllParamsIsValid_ReturnProducerDbModels() {
        var count = 5;

        var producers = ProducerStub.getProducers(count);
        var producerDbModels = ProducerConverter.toModels(producers);

        assertNotNull(producerDbModels);

        for (int i = 0; i < count; i++) {
            var producerID = producers.get(i).getId().getValue();
            var producerName = producers.get(i).getName().getValue();

            var producerDbModelID = producerDbModels.get(i).getId();
            var producerDbModelName = producerDbModels.get(i).getName();

            assertEquals(producerID, producerDbModelID);
            assertEquals(producerName, producerDbModelName);
        }
    }
}