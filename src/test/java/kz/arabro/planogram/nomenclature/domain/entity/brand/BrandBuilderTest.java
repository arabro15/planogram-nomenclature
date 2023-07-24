package kz.arabro.planogram.nomenclature.domain.entity.brand;

import kz.arabro.planogram.nomenclature.domain.exception.CodedException;
import kz.arabro.planogram.nomenclature.testdouble.entity.NameStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrandBuilderTest {

    @Test
    void build_BrandIDIsNull_ThrowEx() {
        var name = NameStub.getName();
        var builder = new BrandBuilder();

        var ex = assertThrows(CodedException.class, () -> builder.setName(name).build());
        assertEquals(BrandError.NULL_ID_BRAND_VALUE, ex.getCode());
    }

    @Test
    void build_NameIsNull_ThrowEx() {
        var brandID = BrandID.newID();
        var builder = new BrandBuilder();

        var ex = assertThrows(CodedException.class, () -> builder.setID(brandID).build());
        assertEquals(BrandError.NULL_NAME_BRAND_VALUE, ex.getCode());
    }

    @Test
    void build_AllParams_ReturnBrand() {
        var brandID = BrandID.newID();
        var name = NameStub.getName();

        var brand = new BrandBuilder().
                setID(brandID).
                setName(name).
                build();

        assertNotNull(brand);
        assertEquals(brandID, brand.getId());
        assertEquals(name, brand.getName());
    }


}