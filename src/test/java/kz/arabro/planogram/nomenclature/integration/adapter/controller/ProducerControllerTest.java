package kz.arabro.planogram.nomenclature.integration.adapter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.arabro.planogram.nomenclature.adapter.controller.response.CreateProducerResponse;
import kz.arabro.planogram.nomenclature.adapter.controller.response.EditProducerResponse;
import kz.arabro.planogram.nomenclature.adapter.controller.response.ProducerResponse;
import kz.arabro.planogram.nomenclature.boundary.model.ProducerEditInfo;
import kz.arabro.planogram.nomenclature.boundary.usecase.CreateProducerUseCase;
import kz.arabro.planogram.nomenclature.boundary.usecase.DeleteProducerUseCase;
import kz.arabro.planogram.nomenclature.boundary.usecase.ReadDataProducerUseCase;
import kz.arabro.planogram.nomenclature.boundary.usecase.UpdateProducerUseCase;
import kz.arabro.planogram.nomenclature.testdouble.controller.ProducerRequestStub;
import kz.arabro.planogram.nomenclature.testdouble.entity.ProducerStub;
import kz.arabro.planogram.nomenclature.util.annotation.IntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
class ProducerControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private CreateProducerUseCase createProducerUseCase;

    @MockBean
    private DeleteProducerUseCase deleteProducerUseCase;

    @MockBean
    private UpdateProducerUseCase updateProducerUseCase;

    @MockBean
    private ReadDataProducerUseCase readDataProducerUseCase;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.
                webAppContextSetup(webApplicationContext).
                build();
    }

    @Test
    void createProducer_RequestIsNull_ReturnIsInternalServerError() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/api/v1/create-producer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void createProducer_RequestIsValid_ReturnCreateBrandResponse() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        var createProducerRequest = ProducerRequestStub.getCreateProducerRequest();

        var producer = ProducerStub.getProducer();
        when(createProducerUseCase.execute(any())).thenReturn(producer);

        var createProducerResponse = new CreateProducerResponse();
        createProducerResponse.setProducerID(producer.getId().getValue().toString());

        mockMvc.perform(post("/api/v1/create-producer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createProducerRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(createProducerResponse)));
    }

    @Test
    void deleteProducer_RequestIsInvalid_ReturnIsInternalServerError() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/api/v1/delete-by-id-producer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void deleteProducer_RequestIsValid_ReturnIsStatusOk() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        var deleteProducerRequest = ProducerRequestStub.getDeleteProducerRequest();

        doNothing().when(deleteProducerUseCase).deleteProducerByID(anyString());

        mockMvc.perform(post("/api/v1/delete-by-id-producer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(deleteProducerRequest)))
                .andExpect(status().isOk());
    }

    @Test
    void editProducer_RequestIsInvalid_ReturnIsInternalServerError() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/api/v1/edit-producer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void editProducer_RequestIsValid_ReturnEditProducerResponse() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        var editProducerRequest = ProducerRequestStub.getEditProducerRequest();

        doNothing().when(updateProducerUseCase).update(any(ProducerEditInfo.class));

        var editProducerResponse = new EditProducerResponse();
        editProducerResponse.setProducerID(editProducerRequest.getProducerID());
        editProducerResponse.setName(editProducerRequest.getName());

        mockMvc.perform(post("/api/v1/edit-producer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(editProducerRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(editProducerResponse)));
    }

    @Test
    void getProducerByID_RequestIsInvalid_ReturnIsInternalServerError() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/api/v1/get-producer-by-id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void getBrandByID_RequestIsValid_ReturnBrandResponse() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        var producer = ProducerStub.getProducer();

        var getProducerByIDRequest = ProducerRequestStub.getProducerByIDRequest();

        when(readDataProducerUseCase.findByID(anyString())).thenReturn(producer);

        var producerResponse = new ProducerResponse();
        producerResponse.setProducerID(producer.getId().getValue().toString());
        producerResponse.setName(producer.getName().getValue());

        mockMvc.perform(post("/api/v1/get-producer-by-id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getProducerByIDRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(producerResponse)));
    }

    @Test
    void getAllProducers_RequestIsValid_ReturnBrandResponses() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        var count = 5;

        var producers = ProducerStub.getProducers(count);

        var producerResponses = new ArrayList<ProducerResponse>(count);

        for (int i = 0; i < count; i++) {
            var producerResponse = new ProducerResponse();
            producerResponse.setProducerID(producers.get(i).getId().getValue().toString());
            producerResponse.setName(producers.get(i).getName().getValue());

            producerResponses.add(producerResponse);
        }

        when(readDataProducerUseCase.findAll()).thenReturn(producers);

        mockMvc.perform(post("/api/v1/get-all-producers"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(producerResponses)));
    }
}