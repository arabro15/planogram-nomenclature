package kz.arabro.planogram.nomenclature.testdouble.entity;

import kz.arabro.planogram.nomenclature.domain.entity.brand.Brand;
import kz.arabro.planogram.nomenclature.domain.entity.brand.BrandBuilder;
import kz.arabro.planogram.nomenclature.domain.entity.brand.BrandID;

public final class BrandStub {

    public static Brand getBrand() {
        var id = BrandID.newID();
        var name = NameStub.getName();

        return new BrandBuilder().
                setID(id).
                setName(name).
                build();
    }
}
