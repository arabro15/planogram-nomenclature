package kz.arabro.planogram.nomenclature.domain.entity.product;


public class Barcode {

    private final String value;

    //+CR: Конструктор остался публичным, и это потенциальный источник ошибки.
    // так как инвариант здесь не проверяется.
    // В таких классах (object-value) конструктор делают приватным
    private Barcode(String value) {
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
