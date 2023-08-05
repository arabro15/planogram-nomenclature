package kz.arabro.planogram.nomenclature.adapter.controller.converter;

import kz.arabro.planogram.nomenclature.adapter.controller.ControllerError;
import kz.arabro.planogram.nomenclature.adapter.controller.request.CreateProducerRequest;
import kz.arabro.planogram.nomenclature.adapter.controller.request.EditProducerRequest;
import kz.arabro.planogram.nomenclature.boundary.model.ProducerCreateInfo;
import kz.arabro.planogram.nomenclature.boundary.model.ProducerEditInfo;

public class ProducerRequestConverter {
    public static ProducerCreateInfo createProducerRequestToModel(CreateProducerRequest request) {
        if (request == null) {
            throw ControllerError.errProducerRequestIsRequired();
        }

        if (request.getName() == null) {
            throw ControllerError.errProducerRequestNullIsRequired();
        }

        return new ProducerCreateInfo(request.getName());
    }

    public static ProducerEditInfo editProducerRequestToModel(EditProducerRequest request) {
        if (request == null) {
            throw ControllerError.errProducerRequestIsRequired();
        }

        if (request.getProducerID() == null) {
            throw ControllerError.errProducerRequestIsRequired();
        }

        var producerEditInfo = new ProducerEditInfo();

        producerEditInfo.setProducerID(request.getProducerID());
        producerEditInfo.setName(request.getName());

        return producerEditInfo;
    }

    private ProducerRequestConverter() {}
}
