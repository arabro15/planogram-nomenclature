package kz.arabro.planogram.nomenclature.adapter.controller.converter;

import kz.arabro.planogram.nomenclature.adapter.controller.ControllerError;
import kz.arabro.planogram.nomenclature.adapter.controller.response.ProducerResponse;
import kz.arabro.planogram.nomenclature.domain.entity.Producer;

import java.util.List;

public class ProducerResponseConverter {

    public static ProducerResponse producerToResponse(Producer producer) {
        if (producer == null) {
            throw ControllerError.errProducerIsNull();
        }

        var producerID = producer.getId().toString();
        var name = producer.getName().getValue();

        var producerResponse = new ProducerResponse();
        producerResponse.setProducerID(producerID);
        producerResponse.setName(name);

        return producerResponse;
    }

    public static List<ProducerResponse> producersToResponses(List<Producer> producers) {
        if (producers == null) {
            throw ControllerError.errProducersIsNull();
        }

        return producers.stream().
                map(ProducerResponseConverter::producerToResponse).
                toList();
    }

    private ProducerResponseConverter() {}

}
