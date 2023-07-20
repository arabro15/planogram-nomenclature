package kz.arabro.planogram.nomenclature.domain.entity.product;


public class Name {

    //CR: зачем данное поле публичное? +Признаю сглупил
    private final String value;

    //CR: Конструктор остался публичным, и это потенциальный источник ошибки.
    // так как инвариант здесь не проверяется.
    // В таких классах (object-value) конструктор делают приватным
    private Name(String nameStr) {
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
