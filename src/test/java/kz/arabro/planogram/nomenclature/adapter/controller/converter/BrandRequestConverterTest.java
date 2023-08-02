package kz.arabro.planogram.nomenclature.adapter.controller.converter;

import kz.arabro.planogram.nomenclature.adapter.controller.ControllerError;
import kz.arabro.planogram.nomenclature.adapter.controller.request.CreateBrandRequest;
import kz.arabro.planogram.nomenclature.adapter.controller.request.EditBrandRequest;
import kz.arabro.planogram.nomenclature.domain.entity.brand.BrandID;
import kz.arabro.planogram.nomenclature.domain.exception.CodedException;
import kz.arabro.planogram.nomenclature.testdouble.entity.NameStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrandRequestConverterTest {

    @Test
    void createBrandRequestToModel_CreateBrandRequestIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> BrandRequestConverter.createBrandRequestToModel(null));
        assertEquals(ControllerError.BRAND_REQUEST_IS_REQUIRED, ex.getCode());
    }

    @Test
    void createBrandRequestToModel_CreateBrandRequestNameIsNull_ThrowEx() {
        var createBrandRequest = new CreateBrandRequest();
        createBrandRequest.setName(null);

        var ex = assertThrows(CodedException.class, () -> BrandRequestConverter.createBrandRequestToModel(createBrandRequest));
        assertEquals(ControllerError.BRAND_NAME_IS_REQUIRED, ex.getCode());
    }

    @Test
    void createBrandRequestToModel_AllValueIsValid_ReturnBrandCreateInfo() {
        var name = NameStub.getName().getValue();

        var createBrandRequest = new CreateBrandRequest();
        createBrandRequest.setName(name);

        var brandCreateInfo = BrandRequestConverter.createBrandRequestToModel(createBrandRequest);

        assertNotNull(brandCreateInfo);
        assertEquals(name, brandCreateInfo.getName());
    }

    @Test
    void editBrandRequestToModel_EditBrandRequestIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> BrandRequestConverter.editBrandRequestToModel(null));
        assertEquals(ControllerError.BRAND_REQUEST_IS_REQUIRED, ex.getCode());
    }

    @Test
    void editBrandRequestToModel_EditBrandRequestBrandIDIsNull_ThrowEx() {
        var editBrandRequest = new EditBrandRequest();
        editBrandRequest.setBrandID(null);

        var ex = assertThrows(CodedException.class, () -> BrandRequestConverter.editBrandRequestToModel(editBrandRequest));
        assertEquals(ControllerError.BRAND_NAME_IS_REQUIRED, ex.getCode());
    }

    @Test
    void editBrandRequestToModel_AllValueIsValid_ReturnBrandEditInfo() {
        var brandIDStr = BrandID.newID().getValue().toString();
        var name = NameStub.getName().getValue();

        var editBrandRequest = new EditBrandRequest();
        editBrandRequest.setBrandID(brandIDStr);
        editBrandRequest.setName(name);

        var brandEditInfo = BrandRequestConverter.editBrandRequestToModel(editBrandRequest);

        assertNotNull(brandEditInfo);
        assertEquals(brandIDStr, brandEditInfo.getBrandID());
        assertEquals(name, brandEditInfo.getName());
    }
}