package kz.arabro.planogram.nomenclature.domain.entity.category;

import java.util.Objects;
import java.util.UUID;

public class CategoryID {

    private final UUID value;

    public static CategoryID newID() {
        var value = UUID.randomUUID();
        return new CategoryID(value);
    }

    public static CategoryID from(String valueStr) {
        if (valueStr == null || valueStr.isBlank()) {
            throw CategoryError.errCategoryIDValueIsRequired();
        }

        try {
            var value = UUID.fromString(valueStr);
            return new CategoryID(value);
        } catch (IllegalArgumentException e) {
            throw CategoryError.errIllegalCategoryIDValue(valueStr, e);
        }
    }

    private CategoryID(UUID value) {
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
        var that = (CategoryID) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
