package kz.arabro.planogram.nomenclature.domain.entity.category;

import kz.arabro.planogram.nomenclature.domain.exception.CodedException;
import kz.arabro.planogram.nomenclature.util.generator.StringGenerator;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CategoryIDTest {

    @Test
    void newID_ReturnCategoryID() {
        var categoryID = CategoryID.newID();
        assertNotNull(categoryID);
    }

    @Test
    void from_ValueStrIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> CategoryID.from(null));
        assertEquals(CategoryError.CATEGORY_ID_VALUE_IS_REQUIRED, ex.getCode());
        assertFalse(ex.getMessage().isEmpty());
    }

    @Test
    void from_ValueStrIsEmpty_ThrowEx() {
        var emptyValueStr = "";
        var ex = assertThrows(CodedException.class, () -> CategoryID.from(emptyValueStr));
        assertEquals(CategoryError.CATEGORY_ID_VALUE_IS_REQUIRED, ex.getCode());
        assertFalse(ex.getMessage().isEmpty());
    }

    @Test
    void from_IllegalFormatOfValueStr_ThrowEx() {
        var strWithIllegalFormat = StringGenerator.getRandomString();
        var ex = assertThrows(CodedException.class, () -> CategoryID.from(strWithIllegalFormat));
        assertEquals(CategoryError.ILLEGAL_CATEGORY_ID_VALUE, ex.getCode());
        assertFalse(ex.getMessage().isEmpty());
        assertNotNull(ex.getCause());
    }

    @Test
    void from_ValidValueStr_ReturnCategoryID() {
        var validValueStr = UUID.randomUUID().toString();
        var categoryID = CategoryID.from(validValueStr);
        assertNotNull(categoryID);
        assertEquals(validValueStr, categoryID.getValue().toString());
    }

}
