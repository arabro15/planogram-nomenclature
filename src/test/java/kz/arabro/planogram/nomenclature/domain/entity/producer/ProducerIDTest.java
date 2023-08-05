package kz.arabro.planogram.nomenclature.domain.entity.producer;

import kz.arabro.planogram.nomenclature.domain.exception.CodedException;
import kz.arabro.planogram.nomenclature.util.generator.StringGenerator;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProducerIDTest {

    @Test
    void newID_ReturnProducerID() {
        var producerID = ProducerID.newID();
        assertNotNull(producerID);
    }

    @Test
    void from_ValueStrIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> ProducerID.from(null));
        assertEquals(ProducerError.PRODUCER_ID_VALUE_IS_REQUIRED, ex.getCode());
        assertFalse(ex.getMessage().isEmpty());
    }

    @Test
    void from_ValueStrIsEmpty_ThrowEx() {
        var emptyValueStr = "";
        var ex = assertThrows(CodedException.class, () -> ProducerID.from(emptyValueStr));
        assertEquals(ProducerError.PRODUCER_ID_VALUE_IS_REQUIRED, ex.getCode());
        assertFalse(ex.getMessage().isEmpty());
    }

    @Test
    void from_IllegalFormatOfValueStr_ThrowEx() {
        var strWithIllegalFormat = StringGenerator.getRandomString();
        var ex = assertThrows(CodedException.class, () -> ProducerID.from(strWithIllegalFormat));
        assertEquals(ProducerError.ILLEGAL_PRODUCER_ID_VALUE, ex.getCode());
        assertFalse(ex.getMessage().isEmpty());
        assertNotNull(ex.getCause());
    }

    @Test
    void from_ValidValueStr_ReturnProducerID() {
        var validValueStr = UUID.randomUUID().toString();
        var producerID = ProducerID.from(validValueStr);
        assertNotNull(producerID);
        assertEquals(validValueStr, producerID.getValue().toString());
    }

}
