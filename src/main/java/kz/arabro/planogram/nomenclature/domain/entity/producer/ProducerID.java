package kz.arabro.planogram.nomenclature.domain.entity.producer;

import java.util.Objects;
import java.util.UUID;

public class ProducerID {

    private final UUID value;

    public static ProducerID newID() {
        var value = UUID.randomUUID();
        return new ProducerID(value);
    }

    public static ProducerID from(String valueStr) {
        if (valueStr == null || valueStr.isBlank()) {
            throw ProducerError.errProducerIDValueIsRequired();
        }

        try {
            var value = UUID.fromString(valueStr);
            return new ProducerID(value);
        } catch (IllegalArgumentException e) {
            throw ProducerError.errIllegalProducerIDValue(valueStr, e);
        }
    }

    private ProducerID(UUID value) {
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
        var that = (ProducerID) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
