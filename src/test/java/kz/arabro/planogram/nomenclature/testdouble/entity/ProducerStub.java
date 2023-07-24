package kz.arabro.planogram.nomenclature.testdouble.entity;

import kz.arabro.planogram.nomenclature.domain.entity.producer.Producer;
import kz.arabro.planogram.nomenclature.domain.entity.producer.ProducerBuilder;
import kz.arabro.planogram.nomenclature.domain.entity.producer.ProducerID;

public final class ProducerStub {

    public static Producer getProducer() {
        var id = ProducerID.newID();
        var name = NameStub.getName();

        return new ProducerBuilder().
                setId(id).
                setName(name).
                build();
    }
}
