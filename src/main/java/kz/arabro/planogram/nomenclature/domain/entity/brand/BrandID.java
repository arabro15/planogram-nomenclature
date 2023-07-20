package kz.arabro.planogram.nomenclature.domain.entity.brand;

import java.util.Objects;
import java.util.UUID;

public class BrandID {
    private final UUID value;

    public static BrandID newID() {
        var value = UUID.randomUUID();
        return new BrandID(value);
    }

    public static BrandID from(String valueStr) {
        if (valueStr == null || valueStr.isBlank()) {
            throw BrandError.errBrandIDValueIsRequired();
        }

        try {
            var value = UUID.fromString(valueStr);
            return new BrandID(value);
        } catch (IllegalArgumentException e) {
            throw BrandError.errIllegalBrandIDValue(valueStr, e);
        }
    }

    private BrandID(UUID value) {
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
        var that = (BrandID) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
