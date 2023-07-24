package kz.arabro.planogram.nomenclature.testdouble.entity;

import kz.arabro.planogram.nomenclature.domain.entity.product.Size;

public final class SizeStub {
    public static Size getSize() {
        var startRandomInt = 0;
        var endRandomInt = 15;
        var height = startRandomInt + (int) (Math.random() * endRandomInt);
        var weight = startRandomInt + (int) (Math.random() * endRandomInt);
        var length = startRandomInt + (int) (Math.random() * endRandomInt);

        return Size.of(height, weight, length);
    }

}
