package kz.arabro.planogram.nomenclature.domain.entity.product;


import java.util.regex.Pattern;

public class Price {

    private static final Pattern FORMAT_PATTERN = Pattern.compile("[^0-9-]");

    private final String value;

    private Price(String price) {
        this.value = price;
    }

    public static Price of(String priceStr) {
        if (priceStr == null || priceStr.isBlank()) {
            throw ProductError.errNullValuePrice();
        }

        var matcher = FORMAT_PATTERN.matcher(priceStr);
        if (matcher.find()) {
            throw ProductError.errIllegalPriceValue(priceStr);
        }

        return new Price(priceStr);
    }

    public String getValue() {
        return this.value;
    }
}
