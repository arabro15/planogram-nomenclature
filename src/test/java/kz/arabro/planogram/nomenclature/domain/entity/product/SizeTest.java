package kz.arabro.planogram.nomenclature.domain.entity.product;

import kz.arabro.planogram.nomenclature.domain.exception.CodedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SizeTest {

    @Test
    void of_HeightIsNegativeValue_ThrowEx() {
        var negativeValue = -123;
        var positiveValue = 10;
        var ex = assertThrows(CodedException.class,
                () -> Size.of(
                        negativeValue,
                        positiveValue,
                        positiveValue
                ));

        assertEquals(ProductError.NEGATIVE_SIZE_HEIGHT_VALUE, ex.getCode());
    }

    @Test
    void of_WeightIsNegativeValue_ThrowEx() {
        var negativeValue = -123;
        var positiveValue = 10;
        var ex = assertThrows(CodedException.class,
                () -> Size.of(
                        positiveValue,
                        negativeValue,
                        positiveValue
                ));

        assertEquals(ProductError.NEGATIVE_SIZE_WEIGHT_VALUE, ex.getCode());
    }

    @Test
    void of_LengthIsNegativeValue_ThrowEx() {
        var negativeValue = -123;
        var positiveValue = 10;
        var ex = assertThrows(CodedException.class,
                () -> Size.of(
                        positiveValue,
                        positiveValue,
                        negativeValue
                ));

        assertEquals(ProductError.NEGATIVE_SIZE_LENGTH_VALUE, ex.getCode());
    }

    @Test
    void of_AllParamsIsValid_ReturnSize() {
        var height = 10;
        var weight = 7;
        var length = 3;

        var size = Size.of(height, weight, length);

        assertNotNull(size);
        assertEquals(height, size.getHeight());
        assertEquals(weight, size.getWeight());
        assertEquals(length, size.getLength());
    }
}
