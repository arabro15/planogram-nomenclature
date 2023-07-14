package kz.arabro.planogram.nomenclature.domain.entity;

public class Brand {

    private final BrandID id;
    private final Name name;

    Brand(BrandID id, Name name) {
        this.id = id;
        this.name = name;
    }

    public Name getName() {
        return this.name;
    }

    public BrandID getId() {
        return this.id;
    }
}
