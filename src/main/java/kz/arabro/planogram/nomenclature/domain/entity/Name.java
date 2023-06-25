package kz.arabro.planogram.nomenclature.domain.entity;


public class Name {

    public final String value;

    public Name(String nameStr) {
        this.value = nameStr;
    }

    public static Name of(String nameStr) {
        if (nameStr == null || nameStr.isBlank()) {
            throw ProductError.errEmptyNameValue();
        }
        return new Name(nameStr);
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return "Name{" +
                "value='" + value + '\'' +
                '}';
    }
}
