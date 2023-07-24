package kz.arabro.planogram.nomenclature.domain.entity.product;

import kz.arabro.planogram.nomenclature.domain.exception.CodedException;
import kz.arabro.planogram.nomenclature.util.generator.StringGenerator;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ProductIDTest {

    @Test
    void newID_ReturnProductID() {
        var productID = ProductID.newID();
        assertNotNull(productID);
    }

    @Test
    void from_ValueStrIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> ProductID.from(null));
        assertEquals(ProductError.PRODUCT_ID_VALUE_IS_REQUIRED, ex.getCode());
        assertFalse(ex.getMessage().isEmpty());
    }

    @Test
    void from_ValueStrIsEmpty_ThrowEx() {
        var emptyValueStr = "";
        var ex = assertThrows(CodedException.class, () -> ProductID.from(emptyValueStr));
        assertEquals(ProductError.PRODUCT_ID_VALUE_IS_REQUIRED, ex.getCode());
        assertFalse(ex.getMessage().isEmpty());
    }

    @Test
    void from_IllegalFormatOfValueStr_ThrowEx() {
        var strWithIllegalFormat = StringGenerator.getRandomString();
        var ex = assertThrows(CodedException.class, () -> ProductID.from(strWithIllegalFormat));
        assertEquals(ProductError.ILLEGAL_PRODUCT_ID_VALUE, ex.getCode());
        assertFalse(ex.getMessage().isEmpty());
        assertNotNull(ex.getCause());
    }

    @Test
    void from_ValidValueStr_ReturnProductID() {
        var validValueStr = UUID.randomUUID().toString();
        var productID = ProductID.from(validValueStr);
        assertNotNull(productID);
        assertEquals(validValueStr, productID.getValue().toString());
    }

}
