package kz.arabro.planogram.nomenclature.testdouble.controller;

import kz.arabro.planogram.nomenclature.adapter.controller.request.CreateBrandRequest;
import kz.arabro.planogram.nomenclature.adapter.controller.request.DeleteBrandRequest;
import kz.arabro.planogram.nomenclature.adapter.controller.request.EditBrandRequest;
import kz.arabro.planogram.nomenclature.adapter.controller.request.GetBrandByIDRequest;
import kz.arabro.planogram.nomenclature.domain.entity.brand.BrandID;
import kz.arabro.planogram.nomenclature.testdouble.entity.NameStub;

public class BrandRequestStub {

    public static CreateBrandRequest getCreateBrandRequest() {
        var createBrandRequest = new CreateBrandRequest();
        createBrandRequest.setName(NameStub.getName().getValue());
        return createBrandRequest;
    }

    public static DeleteBrandRequest getDeleteBrandRequest() {
        var deleteBrandRequest = new DeleteBrandRequest();
        deleteBrandRequest.setBrandID(BrandID.newID().getValue().toString());
        return deleteBrandRequest;
    }

    public static EditBrandRequest getEditBrandRequest() {
        var editBrandRequest = new EditBrandRequest();
        editBrandRequest.setBrandID(BrandID.newID().getValue().toString());
        editBrandRequest.setName(NameStub.getName().getValue());
        return editBrandRequest;
    }


    public static GetBrandByIDRequest getBrandByIDRequest() {
        var getBrandByIDRequest = new GetBrandByIDRequest();
        getBrandByIDRequest.setBrandID(BrandID.newID().getValue().toString());
        return getBrandByIDRequest;
    }
}
