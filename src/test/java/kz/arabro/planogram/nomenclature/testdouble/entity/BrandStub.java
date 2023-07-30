package kz.arabro.planogram.nomenclature.testdouble.entity;

import kz.arabro.planogram.nomenclature.domain.entity.brand.Brand;
import kz.arabro.planogram.nomenclature.domain.entity.brand.BrandBuilder;
import kz.arabro.planogram.nomenclature.domain.entity.brand.BrandID;

import java.util.ArrayList;
import java.util.List;

public final class BrandStub {

    public static Brand getBrand() {
        var id = BrandID.newID();
        var name = NameStub.getName();

        return new BrandBuilder().
                setID(id).
                setName(name).
                build();
    }

    public static List<Brand> getBrands(int count) {
        var brands = new ArrayList<Brand>(count);

        for (int i = 0; i < count; i++) {
            brands.add(getBrand());
        }

        return brands;
    }

}
