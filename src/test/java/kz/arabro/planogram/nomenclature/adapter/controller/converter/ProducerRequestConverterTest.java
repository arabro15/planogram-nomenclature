package kz.arabro.planogram.nomenclature.adapter.controller.converter;

import kz.arabro.planogram.nomenclature.adapter.controller.ControllerError;
import kz.arabro.planogram.nomenclature.adapter.controller.request.CreateProducerRequest;
import kz.arabro.planogram.nomenclature.adapter.controller.request.EditProducerRequest;
import kz.arabro.planogram.nomenclature.domain.entity.producer.ProducerID;
import kz.arabro.planogram.nomenclature.domain.exception.CodedException;
import kz.arabro.planogram.nomenclature.testdouble.entity.NameStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProducerRequestConverterTest {

    @Test
    void createProducerRequestToModel_CreateProducerRequestIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> ProducerRequestConverter.createProducerRequestToModel(null));
        assertEquals(ControllerError.PRODUCER_REQUEST_IS_REQUIRED, ex.getCode());
    }

    @Test
    void createProducerRequestToModel_CreateProducerRequestNameIsNull_ThrowEx() {
        var createProducerRequest = new CreateProducerRequest();
        createProducerRequest.setName(null);

        var ex = assertThrows(CodedException.class, () -> ProducerRequestConverter.createProducerRequestToModel(createProducerRequest));
        assertEquals(ControllerError.PRODUCER_NAME_IS_REQUIRED, ex.getCode());
    }

    @Test
    void createProducerRequestToModel_AllValueIsValid_ReturnProducerCreateInfo() {
        var name = NameStub.getName().getValue();

        var createProducerRequest = new CreateProducerRequest();
        createProducerRequest.setName(name);

        var producerCreateInfo = ProducerRequestConverter.createProducerRequestToModel(createProducerRequest);

        assertNotNull(producerCreateInfo);
        assertEquals(name, producerCreateInfo.getName());
    }

    @Test
    void editProducerRequestToModel_EditProducerRequestIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> ProducerRequestConverter.editProducerRequestToModel(null));
        assertEquals(ControllerError.PRODUCER_REQUEST_IS_REQUIRED, ex.getCode());
    }

    @Test
    void editProducerRequestToModel_EditProducerRequestProducerIDIsNull_ThrowEx() {
        var editProducerRequest = new EditProducerRequest();
        editProducerRequest.setProducerID(null);

        var ex = assertThrows(CodedException.class, () -> ProducerRequestConverter.editProducerRequestToModel(editProducerRequest));
        assertEquals(ControllerError.PRODUCER_REQUEST_IS_REQUIRED, ex.getCode());
    }

    @Test
    void editProducerRequestToModel_AllValueIsValid_ReturnProducerEditInfo() {
        var producerIDStr = ProducerID.newID().getValue().toString();
        var name = NameStub.getName().getValue();

        var editProducerRequest = new EditProducerRequest();
        editProducerRequest.setProducerID(producerIDStr);
        editProducerRequest.setName(name);

        var producerEditInfo = ProducerRequestConverter.editProducerRequestToModel(editProducerRequest);

        assertNotNull(producerEditInfo);
        assertEquals(producerIDStr, producerEditInfo.getProducerID());
        assertEquals(name, producerEditInfo.getName());
    }
}