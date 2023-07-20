package kz.arabro.planogram.nomenclature.domain.entity.product;


public class Price {

    private String price;

    private Price(String price) {
        this.price = price;
    }

    public static Price of(String priceStr) {
        if (priceStr == null) {
            throw ProductError.errNullValuePrice();
        }
        return new Price(priceStr);
    }

    public String getPrice() {
        return this.price;
    }
}
