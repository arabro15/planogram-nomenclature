package kz.arabro.planogram.nomenclature.domain.entity.product;

import kz.arabro.planogram.nomenclature.domain.exception.CodedException;
import kz.arabro.planogram.nomenclature.util.generator.StringGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NameTest {

    @Test
    void of_NameIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, ()-> Name.of(null));
        assertEquals(ProductError.EMPTY_NAME_VALUE, ex.getCode());
    }

    @Test
    void of_NameIsBlank_ThrowEx() {
        var blankValue = " ";
        var ex = assertThrows(CodedException.class, ()-> Name.of(blankValue));
        assertEquals(ProductError.EMPTY_NAME_VALUE, ex.getCode());
    }

    @Test
    void of_ValueIsValid_ReturnName() {
        var nameStr = StringGenerator.getRandomString();
        var name = Name.of(nameStr);

        assertNotNull(name);
        assertEquals(nameStr, name.getValue());
    }
}
