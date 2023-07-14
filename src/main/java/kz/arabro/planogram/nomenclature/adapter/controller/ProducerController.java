package kz.arabro.planogram.nomenclature.adapter.controller;

import kz.arabro.planogram.nomenclature.adapter.controller.converter.ProducerRequestConverter;
import kz.arabro.planogram.nomenclature.adapter.controller.converter.ProducerResponseConverter;
import kz.arabro.planogram.nomenclature.adapter.controller.request.CreateProducerRequest;
import kz.arabro.planogram.nomenclature.adapter.controller.request.DeleteProducerRequest;
import kz.arabro.planogram.nomenclature.adapter.controller.request.EditProducerRequest;
import kz.arabro.planogram.nomenclature.adapter.controller.request.GetProducerByIDRequest;
import kz.arabro.planogram.nomenclature.adapter.controller.response.CreateProducerResponse;
import kz.arabro.planogram.nomenclature.adapter.controller.response.EditProducerResponse;
import kz.arabro.planogram.nomenclature.adapter.controller.response.ProducerResponse;
import kz.arabro.planogram.nomenclature.boundary.usecase.ProducerCreatorUseCase;
import kz.arabro.planogram.nomenclature.boundary.usecase.ProducerDeleteUseCase;
import kz.arabro.planogram.nomenclature.boundary.usecase.ProducerEditorUseCase;
import kz.arabro.planogram.nomenclature.boundary.usecase.ProducerReadDataUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
public class ProducerController {

    private final ProducerCreatorUseCase producerCreatorUseCase;
    private final ProducerDeleteUseCase producerDeleteUseCase;
    private final ProducerEditorUseCase producerEditorUseCase;
    private final ProducerReadDataUseCase producerReadDataUseCase;

    public ProducerController(ProducerCreatorUseCase producerCreatorUseCase,
                              ProducerDeleteUseCase producerDeleteUseCase,
                              ProducerEditorUseCase producerEditorUseCase,
                              ProducerReadDataUseCase producerReadDataUseCase) {
        this.producerCreatorUseCase = producerCreatorUseCase;
        this.producerDeleteUseCase = producerDeleteUseCase;
        this.producerEditorUseCase = producerEditorUseCase;
        this.producerReadDataUseCase = producerReadDataUseCase;
    }

    @PostMapping(path = "/create-producer")
    public CreateProducerResponse createProducer(@RequestBody CreateProducerRequest request) {
        var info = ProducerRequestConverter.createProducerRequestToModel(request);

        var producer = producerCreatorUseCase.execute(info);

        var response = new CreateProducerResponse();
        response.setProducerID(producer.getId().toString());
        return response;
    }

    @PostMapping(path = "/delete-by-id-producer")
    public ResponseEntity<Object> deleteProducer(@RequestBody DeleteProducerRequest request) {
        producerDeleteUseCase.deleteProducerByID(request.getProducerID());
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping(path = "/edit-producer")
    public EditProducerResponse editProducer(@RequestBody EditProducerRequest request) {
        var producer = ProducerRequestConverter.editProducerRequestToModel(request);

        producerEditorUseCase.update(producer);

        var response = new EditProducerResponse();
        response.setProducerID(producer.getProducerID());
        response.setName(producer.getName());

        return response;
    }

    @PostMapping(path = "/get-producer-by-id")
    public ProducerResponse getProducerByID(@RequestBody GetProducerByIDRequest request) {
        var producer = producerReadDataUseCase.findByID(request.getProducerID());
        return ProducerResponseConverter.producerToResponse(producer);
    }

    @PostMapping(path = "/get-all-producers")
    public List<ProducerResponse> getAllProducers() {
        var producers = producerReadDataUseCase.findAll();
        return ProducerResponseConverter.producersToResponses(producers);
    }



}
