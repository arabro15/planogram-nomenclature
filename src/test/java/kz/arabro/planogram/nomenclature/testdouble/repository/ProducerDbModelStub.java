package kz.arabro.planogram.nomenclature.testdouble.repository;

import kz.arabro.planogram.nomenclature.adapter.repository.model.ProducerDbModel;
import kz.arabro.planogram.nomenclature.testdouble.entity.ProducerStub;

import java.util.ArrayList;
import java.util.List;

public class ProducerDbModelStub {

    public static ProducerDbModel getProducerDbModel() {
        var producer = ProducerStub.getProducer();

        var id = producer.getId().getValue();
        var name = producer.getName().getValue();

        var producerDbModel = new ProducerDbModel();
        producerDbModel.setId(id);
        producerDbModel.setName(name);

        return producerDbModel;
    }

    public static List<ProducerDbModel> getProducerDbModels(int count) {
        var producerDbModels = new ArrayList<ProducerDbModel>(count);

        for (int i = 0; i < count; i++) {
            producerDbModels.add(getProducerDbModel());
        }

        return producerDbModels;
    }
}
