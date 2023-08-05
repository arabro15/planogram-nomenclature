package kz.arabro.planogram.nomenclature.adapter.controller;

import kz.arabro.planogram.nomenclature.boundary.model.ProducerCreateInfo;
import kz.arabro.planogram.nomenclature.boundary.model.ProducerEditInfo;
import kz.arabro.planogram.nomenclature.boundary.usecase.CreateProducerUseCase;
import kz.arabro.planogram.nomenclature.boundary.usecase.DeleteProducerUseCase;
import kz.arabro.planogram.nomenclature.boundary.usecase.ReadDataProducerUseCase;
import kz.arabro.planogram.nomenclature.boundary.usecase.UpdateProducerUseCase;
import kz.arabro.planogram.nomenclature.domain.exception.CodedException;
import kz.arabro.planogram.nomenclature.testdouble.controller.ProducerRequestStub;
import kz.arabro.planogram.nomenclature.testdouble.entity.ProducerStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProducerControllerTest {

    @Mock
    private CreateProducerUseCase createProducerUseCase;

    @Mock
    private DeleteProducerUseCase deleteProducerUseCase;

    @Mock
    private UpdateProducerUseCase updateProducerUseCase;

    @Mock
    private ReadDataProducerUseCase readDataProducerUseCase;

    private ProducerController producerController;

    @BeforeEach
    void setUp() {
        this.producerController = new ProducerController(
                createProducerUseCase,
                deleteProducerUseCase,
                updateProducerUseCase,
                readDataProducerUseCase
        );
    }

    @Test
    void createProducer_CreateProducerRequestIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> producerController.createProducer(null));
        assertEquals(ControllerError.PRODUCER_REQUEST_IS_REQUIRED, ex.getCode());
    }

    @Test
    void createProducer_UseCaseIsThrowEx_ThrowEx() {
        var createProducerRequest = ProducerRequestStub.getCreateProducerRequest();

        when(createProducerUseCase.execute(any(ProducerCreateInfo.class))).thenThrow(RuntimeException.class);
        assertThrows(RuntimeException.class, () -> producerController.createProducer(createProducerRequest));
    }

    @Test
    void createProducer_NoException_ReturnCreateProducerResponse() {
        var producer = ProducerStub.getProducer();
        when(createProducerUseCase.execute(any(ProducerCreateInfo.class))).thenReturn(producer);

        var createProducerRequest = ProducerRequestStub.getCreateProducerRequest();
        var producerResponse = producerController.createProducer(createProducerRequest);
        assertEquals(producer.getId().getValue().toString(), producerResponse.getProducerID());
    }

    @Test
    void deleteProducer_DeleteProducerRequestIsThrowEx_ThrowEx() {
        doThrow(RuntimeException.class).when(deleteProducerUseCase).deleteProducerByID(anyString());

        var deleteProducerRequest = ProducerRequestStub.getDeleteProducerRequest();

        assertThrows(RuntimeException.class, () -> producerController.deleteProducer(deleteProducerRequest));
    }

    @Test
    void deleteProducer_NoException_ReturnHttpStatusOk() {
        doNothing().when(deleteProducerUseCase).deleteProducerByID(anyString());

        var deleteProducerRequest = ProducerRequestStub.getDeleteProducerRequest();

        var response = producerController.deleteProducer(deleteProducerRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void editProducer_EditProducerRequestIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> producerController.editProducer(null));
        assertEquals(ControllerError.PRODUCER_REQUEST_IS_REQUIRED, ex.getCode());
    }

    @Test
    void editProducer_UseCaseIsThrowEx_ThrowEx() {
        var editProducerRequest = ProducerRequestStub.getEditProducerRequest();

        doThrow(RuntimeException.class).when(updateProducerUseCase).update(any(ProducerEditInfo.class));
        assertThrows(RuntimeException.class, () -> producerController.editProducer(editProducerRequest));
    }

    @Test
    void editProducer_NoException_ReturnEditProducerResponse() {
        doNothing().when(updateProducerUseCase).update(any(ProducerEditInfo.class));

        var editProducerRequest = ProducerRequestStub.getEditProducerRequest();

        var editProducerResponse = producerController.editProducer(editProducerRequest);

        assertNotNull(editProducerResponse);

        var editProducerRequestID = editProducerRequest.getProducerID();
        var editProducerRequestName = editProducerRequest.getName();

        var editProducerResponseID = editProducerResponse.getProducerID();
        var editProducerResponseName = editProducerResponse.getName();

        assertEquals(editProducerRequestID, editProducerResponseID);
        assertEquals(editProducerRequestName, editProducerResponseName);
    }

    @Test
    void getProducerByID_GetProducerByIDRequestIsNull_RuntimeEx() {
        assertThrows(RuntimeException.class, () -> producerController.getProducerByID(null));
    }

    @Test
    void getProducerByID_NoException_ReturnProducerResponse() {
        var producer = ProducerStub.getProducer();

        when(readDataProducerUseCase.findByID(anyString())).thenReturn(producer);

        var getProducerByIDRequest = ProducerRequestStub.getProducerByIDRequest();

        var producerResponse = producerController.getProducerByID(getProducerByIDRequest);

        assertNotNull(producerResponse);
        assertEquals(producer.getId().getValue().toString(), producerResponse.getProducerID());
    }

    @Test
    void getAllProducers_NotValues_ReturnProducerResponses() {
        var count = 5;

        var producers = ProducerStub.getProducers(count);
        when(readDataProducerUseCase.findAll()).thenReturn(producers);

        var producerResponses = producerController.getAllProducers();

        assertNotNull(producerResponses);
        for (int i = 0; i < count; i++) {
            var producerID = producers.get(i).getId().getValue().toString();
            var producerName = producers.get(i).getName().getValue();

            var producerResponseID = producerResponses.get(i).getProducerID();
            var producerResponseName = producerResponses.get(i).getName();

            assertEquals(producerID, producerResponseID);
            assertEquals(producerName, producerResponseName);
        }
    }
}