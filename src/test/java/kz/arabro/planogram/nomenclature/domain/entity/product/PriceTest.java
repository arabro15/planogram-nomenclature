package kz.arabro.planogram.nomenclature.domain.entity.product;

import kz.arabro.planogram.nomenclature.domain.exception.CodedException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PriceTest {

    @Test
    void of_PriceIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, ()-> Price.of(null));
        assertEquals(ProductError.NULL_PRICE_VALUE, ex.getCode());
    }

    @Test
    void of_PriceIsBlank_ThrowEx() {
        var blankValue = " ";
        var ex = assertThrows(CodedException.class, ()-> Name.of(blankValue));
        assertEquals(ProductError.EMPTY_NAME_VALUE, ex.getCode());
    }

    @Test
    void of_IllegalValue_ThrowEx() {
        var illegalStrings = List.of("@b", "boeing#4", "airbus!", "%");

        for (var illegalValue : illegalStrings) {
            var ex = assertThrows(CodedException.class, () -> Price.of(illegalValue));
            assertEquals(ProductError.ILLEGAL_PRICE_VALUE, ex.getCode());
        }
    }

    @Test
    void of_ValueIsValid_ReturnPrice() {
        var priceStr = "500";
        var price = Price.of(priceStr);

        assertNotNull(price);
        assertEquals(priceStr, price.getValue());
    }
}
