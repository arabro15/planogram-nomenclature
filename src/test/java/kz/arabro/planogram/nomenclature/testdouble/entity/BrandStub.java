package kz.arabro.planogram.nomenclature.testdouble.entity;

import kz.arabro.planogram.nomenclature.domain.entity.brand.Brand;
import kz.arabro.planogram.nomenclature.domain.entity.brand.BrandBuilder;
import kz.arabro.planogram.nomenclature.domain.entity.brand.BrandID;
import kz.arabro.planogram.nomenclature.domain.entity.product.Name;

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

    public static Brand getBrandForProduct() {
        var id = BrandID.from("df47d092-7b33-4b00-b700-9a6b5db44103");
        var name = Name.of("Pepsi");

        return new BrandBuilder().
                setID(id).
                setName(name).
                build();
    }
}
