package kz.arabro.planogram.nomenclature.domain.entity.product;

import kz.arabro.planogram.nomenclature.domain.exception.CodedException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BarcodeTest {

    @Test
    void of_BarcodeIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, ()-> Barcode.of(null));
        assertEquals(ProductError.NULL_BARCODE_VALUE, ex.getCode());
    }
    @Test
    void of_BarcodeIsNegative_ThrowEx() {
        var negativeValue = "-132113223";
        var ex = assertThrows(CodedException.class, ()-> Barcode.of(negativeValue));
        assertEquals(ProductError.NEGATIVE_BARCODE_VALUE, ex.getCode());
    }

    @Test
    void of_BarcodeIsLarge_ThrowEx() {
        var largeValue = "12345678901234";
        var ex = assertThrows(CodedException.class, ()-> Barcode.of(largeValue));
        assertEquals(ProductError.LARGE_BARCODE_VALUE, ex.getCode());
    }

    @Test
    void of_IllegalValue_ThrowEx() {
        var illegalStrings = List.of("@b", "boeing#4", "airbus!", "%", "abc");

        for (var illegalValue : illegalStrings) {
            var ex = assertThrows(CodedException.class, () -> Barcode.of(illegalValue));
            assertEquals(ProductError.ILLEGAL_BARCODE_VALUE, ex.getCode());
        }
    }

    @Test
    void of_ValueIsValid_ReturnBarcode() {
        var barcodeStr = "1234567890123";
        var barcode = Barcode.of(barcodeStr);

        assertNotNull(barcode);
        assertEquals(barcodeStr, barcode.getValue());
    }
}
