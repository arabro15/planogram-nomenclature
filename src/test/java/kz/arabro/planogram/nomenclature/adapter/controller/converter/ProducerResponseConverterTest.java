package kz.arabro.planogram.nomenclature.adapter.controller.converter;

import kz.arabro.planogram.nomenclature.adapter.controller.ControllerError;
import kz.arabro.planogram.nomenclature.domain.exception.CodedException;
import kz.arabro.planogram.nomenclature.testdouble.entity.ProducerStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProducerResponseConverterTest {
    @Test
    void producerToResponse_ProducerIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> ProducerResponseConverter.producerToResponse(null));
        assertEquals(ControllerError.PRODUCER_IS_NULL, ex.getCode());
    }

    @Test
    void producerToResponse_ValueIsValid_ReturnProducerResponse() {
        var producer = ProducerStub.getProducer();
        var producerIDStr = producer.getId().getValue().toString();
        var producerName = producer.getName().getValue();

        var producerResponse = ProducerResponseConverter.producerToResponse(producer);

        assertNotNull(producerResponse);

        var producerResponseID = producerResponse.getProducerID();
        var producerResponseName = producerResponse.getName();

        assertEquals(producerIDStr, producerResponseID);
        assertEquals(producerName, producerResponseName);
    }

    @Test
    void producersToResponses_ProducersIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> ProducerResponseConverter.producersToResponses(null));
        assertEquals(ControllerError.PRODUCERS_IS_NULL, ex.getCode());
    }

    @Test
    void producersToResponses_ValueIsValid_ReturnProducerResponses() {
        var count = 5;

        var producers = ProducerStub.getProducers(count);

        var producerResponses = ProducerResponseConverter.producersToResponses(producers);

        assertNotNull(producerResponses);

        for (int i = 0; i < count; i++) {
            var producerIDStr = producers.get(i).getId().getValue().toString();
            var producerName = producers.get(i).getName().getValue();

            var producerResponseID = producerResponses.get(i).getProducerID();
            var producerResponseName = producerResponses.get(i).getName();

            assertEquals(producerIDStr, producerResponseID);
            assertEquals(producerName, producerResponseName);
        }
    }
}