package kz.arabro.planogram.nomenclature.adapter.repository.converter;

import kz.arabro.planogram.nomenclature.adapter.repository.RepositoryError;
import kz.arabro.planogram.nomenclature.adapter.repository.model.ProducerDbModel;
import kz.arabro.planogram.nomenclature.domain.entity.Name;
import kz.arabro.planogram.nomenclature.domain.entity.Producer;
import kz.arabro.planogram.nomenclature.domain.entity.ProducerBuilder;
import kz.arabro.planogram.nomenclature.domain.entity.ProducerID;

import java.util.List;

public class ProducerConverter {

    public static Producer toEntity(ProducerDbModel producerDbModel) {
        if(producerDbModel == null) {
            throw RepositoryError.errProducerDbModelIsRequired();
        }

        var id = ProducerID.from(producerDbModel.getId().toString());
        var name = Name.of(producerDbModel.getName());

        return new ProducerBuilder().
                setId(id).
                setName(name).
                build();
    }

    public static List<Producer> toEntities(List<ProducerDbModel> producerDbModels) {
        if(producerDbModels == null) {
            throw RepositoryError.errProducerDbModelsIsRequired();
        }

        return producerDbModels.stream().
                map(ProducerConverter::toEntity).
                toList();
    }

    public static ProducerDbModel toModel(Producer producer) {
        if(producer == null) {
            throw RepositoryError.errProducerIsRequired();
        }

        var id = producer.getId().getValue();
        var name = producer.getName().getValue();

        var producerDbModel = new ProducerDbModel();
        producerDbModel.setId(id);
        producerDbModel.setName(name);

        return producerDbModel;
    }

    public static List<ProducerDbModel> toModels(List<Producer> producers) {
        if(producers == null) {
            throw RepositoryError.errProducersIsRequired();
        }

        return producers.stream().
                map(ProducerConverter::toModel).
                toList();
    }

    private ProducerConverter() {}
}
