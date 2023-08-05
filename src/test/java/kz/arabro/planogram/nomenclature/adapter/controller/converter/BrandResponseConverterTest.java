package kz.arabro.planogram.nomenclature.adapter.controller.converter;

import kz.arabro.planogram.nomenclature.adapter.controller.ControllerError;
import kz.arabro.planogram.nomenclature.domain.exception.CodedException;
import kz.arabro.planogram.nomenclature.testdouble.entity.BrandStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrandResponseConverterTest {

    @Test
    void brandToResponse_BrandIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> BrandResponseConverter.brandToResponse(null));
        assertEquals(ControllerError.BRAND_IS_NULL, ex.getCode());
    }

    @Test
    void brandToResponse_ValueIsValid_ReturnBrandResponse() {
        var brand = BrandStub.getBrand();
        var brandIDStr = brand.getId().getValue().toString();
        var brandName = brand.getName().getValue();

        var brandResponse = BrandResponseConverter.brandToResponse(brand);

        assertNotNull(brandResponse);

        var brandResponseID = brandResponse.getBrandID();
        var brandResponseName = brandResponse.getName();

        assertEquals(brandIDStr, brandResponseID);
        assertEquals(brandName, brandResponseName);
    }

    @Test
    void brandsToResponses_BrandsIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> BrandResponseConverter.brandsToResponses(null));
        assertEquals(ControllerError.BRANDS_IS_NULL, ex.getCode());
    }

    @Test
    void brandsToResponses_ValueIsValid_ReturnBrandResponses() {
        var count = 5;

        var brands = BrandStub.getBrands(count);

        var brandResponses = BrandResponseConverter.brandsToResponses(brands);

        assertNotNull(brandResponses);

        for (int i = 0; i < count; i++) {
            var brandIDStr = brands.get(i).getId().getValue().toString();
            var brandName = brands.get(i).getName().getValue();

            var brandResponseID = brandResponses.get(i).getBrandID();
            var brandResponseName = brandResponses.get(i).getName();

            assertEquals(brandIDStr, brandResponseID);
            assertEquals(brandName, brandResponseName);
        }
    }
}