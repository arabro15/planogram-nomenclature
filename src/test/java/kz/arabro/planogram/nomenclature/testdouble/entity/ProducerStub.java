package kz.arabro.planogram.nomenclature.testdouble.entity;

import kz.arabro.planogram.nomenclature.domain.entity.producer.Producer;
import kz.arabro.planogram.nomenclature.domain.entity.producer.ProducerBuilder;
import kz.arabro.planogram.nomenclature.domain.entity.producer.ProducerID;
import kz.arabro.planogram.nomenclature.domain.entity.product.Name;

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

    public static Producer getProducerForProduct() {
        var id = ProducerID.from("0e6a9bf2-1242-4dda-a3e5-7d83cc301cb0");
        var name = Name.of("Coca-cola");

        return new ProducerBuilder().
                setId(id).
                setName(name).
                build();
    }
}
