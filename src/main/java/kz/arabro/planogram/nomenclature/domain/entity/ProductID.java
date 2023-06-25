package kz.arabro.planogram.nomenclature.domain.entity;

import java.util.Objects;
import java.util.UUID;

public class ProductID {
    private final UUID value;

    public static ProductID newID() {
        var value = UUID.randomUUID();
        return new ProductID(value);
    }

    public static ProductID from(String valueStr) {
        if (valueStr == null || valueStr.isBlank()) {
            throw ProductError.errProductIDValueIsRequired();
        }

        try {
            var value = UUID.fromString(valueStr);
            return new ProductID(value);
        } catch (IllegalArgumentException e) {
            throw ProductError.errIllegalProductIDValue(valueStr, e);
        }
    }

    private ProductID(UUID value) {
        this.value = value;
    }

    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        var that = (ProductID) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
