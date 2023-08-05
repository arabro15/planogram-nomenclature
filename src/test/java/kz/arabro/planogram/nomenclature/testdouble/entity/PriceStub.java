package kz.arabro.planogram.nomenclature.testdouble.entity;

import kz.arabro.planogram.nomenclature.domain.entity.product.Price;

public final class PriceStub {

    public static Price getPrice() {
        return Price.of("500");
    }
}
