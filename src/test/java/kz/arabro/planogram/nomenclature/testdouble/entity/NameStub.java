package kz.arabro.planogram.nomenclature.testdouble.entity;

import kz.arabro.planogram.nomenclature.domain.entity.product.Name;
import kz.arabro.planogram.nomenclature.util.generator.StringGenerator;

public final class NameStub {

    public static Name getName() {
        return Name.of(StringGenerator.getRandomString());
    }
}
