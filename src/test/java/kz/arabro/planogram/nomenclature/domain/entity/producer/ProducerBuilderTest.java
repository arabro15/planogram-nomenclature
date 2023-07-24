package kz.arabro.planogram.nomenclature.domain.entity.producer;

import kz.arabro.planogram.nomenclature.domain.exception.CodedException;
import kz.arabro.planogram.nomenclature.testdouble.entity.NameStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProducerBuilderTest {

    @Test
    void build_ProducerIDIsNull_ThrowEx() {
        var name = NameStub.getName();
        var builder = new ProducerBuilder();

        var ex = assertThrows(CodedException.class, () -> builder.setName(name).build());
        assertEquals(ProducerError.NULL_ID_PRODUCER_VALUE, ex.getCode());
    }

    @Test
    void build_NameIsNull_ThrowEx() {
        var producerID = ProducerID.newID();
        var builder = new ProducerBuilder();

        var ex = assertThrows(CodedException.class, () -> builder.setId(producerID).build());
        assertEquals(ProducerError.NULL_NAME_PRODUCER_VALUE, ex.getCode());
    }

    @Test
    void build_AllParams_ReturnBrand() {
        var producerID = ProducerID.newID();
        var name = NameStub.getName();

        var brand = new ProducerBuilder().
                setId(producerID).
                setName(name).
                build();

        assertNotNull(brand);
        assertEquals(producerID, brand.getId());
        assertEquals(name, brand.getName());
    }

}
