package kz.arabro.planogram.nomenclature.domain.entity.producer;

import kz.arabro.planogram.nomenclature.domain.entity.product.Name;

public class ProducerBuilder {

    private ProducerID id;
    private Name name;

    public ProducerBuilder setId(ProducerID id) {
        this.id = id;
        return this;
    }

    public ProducerBuilder setName(Name name) {
        this.name = name;
        return this;
    }

    public Producer build() {
        checkRequiredFields();

        return new Producer(id, name);
    }

    private void checkRequiredFields() {
        if (id == null) {
            throw ProducerError.errNullIdProducerValue();
        }
        if (name == null) {
            throw ProducerError.errNullNameProducerValue();
        }
    }
}
