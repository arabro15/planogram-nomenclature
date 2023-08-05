package kz.arabro.planogram.nomenclature.domain.entity.brand;

import kz.arabro.planogram.nomenclature.domain.exception.CodedException;
import kz.arabro.planogram.nomenclature.util.generator.StringGenerator;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BrandIDTest {

    @Test
    void newID_ReturnBrandID() {
        var brandID = BrandID.newID();
        assertNotNull(brandID);
    }

    @Test
    void from_ValueStrIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> BrandID.from(null));
        assertEquals(BrandError.BRAND_ID_VALUE_IS_REQUIRED, ex.getCode());
        assertFalse(ex.getMessage().isEmpty());
    }

    @Test
    void from_ValueStrIsEmpty_ThrowEx() {
        var emptyValueStr = "";
        var ex = assertThrows(CodedException.class, () -> BrandID.from(emptyValueStr));
        assertEquals(BrandError.BRAND_ID_VALUE_IS_REQUIRED, ex.getCode());
        assertFalse(ex.getMessage().isEmpty());
    }

    @Test
    void from_IllegalFormatOfValueStr_ThrowEx() {
        var strWithIllegalFormat = StringGenerator.getRandomString();
        var ex = assertThrows(CodedException.class, () -> BrandID.from(strWithIllegalFormat));
        assertEquals(BrandError.ILLEGAL_BRAND_ID_VALUE, ex.getCode());
        assertFalse(ex.getMessage().isEmpty());
        assertNotNull(ex.getCause());
    }

    @Test
    void from_ValidValueStr_ReturnBrandID() {
        var validValueStr = UUID.randomUUID().toString();
        var brandID = BrandID.from(validValueStr);
        assertNotNull(brandID);
        assertEquals(validValueStr, brandID.getValue().toString());
    }

}