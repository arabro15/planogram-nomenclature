package kz.arabro.planogram.nomenclature.domain.entity.brand;

import kz.arabro.planogram.nomenclature.domain.entity.product.Name;

public class BrandBuilder {
    private BrandID id;
    private Name name;

    public BrandBuilder setID(BrandID id) {
        this.id = id;
        return this;
    }

    public BrandBuilder setName(Name name) {
        this.name = name;
        return this;
    }

    public Brand build() {
        checkRequiredFields();

        return new Brand(id, name);
    }

    private void checkRequiredFields() {
        if (id == null) {
            throw BrandError.errNullIdBrandValue();
        }
        if(name == null) {
            throw BrandError.errNullNameBrandValue();
        }
    }
}
