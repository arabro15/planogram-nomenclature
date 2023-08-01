package kz.arabro.planogram.nomenclature.testdouble.entity;

import kz.arabro.planogram.nomenclature.domain.entity.producer.Producer;
import kz.arabro.planogram.nomenclature.domain.entity.producer.ProducerBuilder;
import kz.arabro.planogram.nomenclature.domain.entity.producer.ProducerID;

import java.util.ArrayList;
import java.util.List;

public final class ProducerStub {

    public static Producer getProducer() {
        var id = ProducerID.newID();
        var name = NameStub.getName();

        return new ProducerBuilder().
                setId(id).
                setName(name).
                build();
    }

    public static List<Producer> getProducers(int count) {
        var producers = new ArrayList<Producer>(count);

        for (int i = 0; i < count; i++) {
            producers.add(getProducer());
        }

        return producers;
    }
}
