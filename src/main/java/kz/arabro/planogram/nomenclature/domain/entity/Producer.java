package kz.arabro.planogram.nomenclature.domain.entity;

public class Producer {

    private final ProducerID id;
    private final Name name;

    Producer(ProducerID id, Name name) {
        this.id = id;
        this.name = name;
    }

    public ProducerID getId() {
        return this.id;
    }

    public Name getName() {
        return this.name;
    }

}
