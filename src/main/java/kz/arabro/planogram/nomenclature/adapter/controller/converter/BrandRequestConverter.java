package kz.arabro.planogram.nomenclature.adapter.controller.converter;

import kz.arabro.planogram.nomenclature.adapter.controller.ControllerError;
import kz.arabro.planogram.nomenclature.adapter.controller.request.CreateBrandRequest;
import kz.arabro.planogram.nomenclature.adapter.controller.request.EditBrandRequest;
import kz.arabro.planogram.nomenclature.boundary.model.BrandCreateInfo;
import kz.arabro.planogram.nomenclature.boundary.model.BrandEditInfo;

public class BrandRequestConverter {

    public static BrandCreateInfo createBrandRequestToModel(CreateBrandRequest request) {
        if (request == null) {
            throw ControllerError.errBrandRequestIsRequired();
        }

        if (request.getName() == null) {
            throw ControllerError.errBrandRequestNullIsRequired();
        }

        return new BrandCreateInfo(request.getName());
    }

    public static BrandEditInfo editBrandRequestToModel(EditBrandRequest request) {
        if (request == null) {
            throw ControllerError.errBrandRequestIsRequired();
        }

        if (request.getBrandID() == null) {
            throw ControllerError.errBrandRequestNullIsRequired();
        }

        var brandEditInfo = new BrandEditInfo();

        brandEditInfo.setBrandID(request.getBrandID());
        brandEditInfo.setName(request.getName());

        return brandEditInfo;
    }

    private BrandRequestConverter() {}
}
