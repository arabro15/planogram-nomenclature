package kz.arabro.planogram.nomenclature.domain.entity;


public class Barcode {

    private final String value;

    public Barcode(String value) {
        this.value = value;
    }

    public static Barcode of(String value) {
        if (value.contains("-")) {
            throw ProductError.errNegativeBarcodeValue();
        }
        if (value.length() > 13) {
            throw ProductError.errLargeBarcodeValue();
        }

        return new Barcode(value);
    }

    public String getValue() {
        return value;
    }
}
