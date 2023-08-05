package kz.arabro.planogram.nomenclature.domain.entity.product;


import java.util.regex.Pattern;

public class Barcode {

    private static final Pattern FORMAT_PATTERN = Pattern.compile("\\D");
    private final String value;

    private Barcode(String value) {
        this.value = value;
    }

    public static Barcode of(String value) {
        if (value == null) {
            throw ProductError.errBarcodeIsNull();
        }
        if (value.contains("-")) {
            throw ProductError.errNegativeBarcodeValue();
        }
        if (value.length() > 13) {
            throw ProductError.errLargeBarcodeValue();
        }
        var matcher = FORMAT_PATTERN.matcher(value);
        if (matcher.find()) {
            throw ProductError.errIllegalBarcodeValue(value);
        }

        return new Barcode(value);
    }

    public String getValue() {
        return value;
    }
}
