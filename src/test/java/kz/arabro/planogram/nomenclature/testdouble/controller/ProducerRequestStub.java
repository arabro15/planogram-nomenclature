package kz.arabro.planogram.nomenclature.testdouble.controller;

import kz.arabro.planogram.nomenclature.adapter.controller.request.CreateProducerRequest;
import kz.arabro.planogram.nomenclature.adapter.controller.request.DeleteProducerRequest;
import kz.arabro.planogram.nomenclature.adapter.controller.request.EditProducerRequest;
import kz.arabro.planogram.nomenclature.adapter.controller.request.GetProducerByIDRequest;
import kz.arabro.planogram.nomenclature.domain.entity.brand.BrandID;
import kz.arabro.planogram.nomenclature.testdouble.entity.NameStub;

public class ProducerRequestStub {

    public static CreateProducerRequest getCreateProducerRequest() {
        var createProducerRequest = new CreateProducerRequest();
        createProducerRequest.setName(NameStub.getName().getValue());
        return createProducerRequest;
    }

    public static DeleteProducerRequest getDeleteProducerRequest() {
        var deleteProducerRequest = new DeleteProducerRequest();
        deleteProducerRequest.setProducerID(BrandID.newID().getValue().toString());
        return deleteProducerRequest;
    }

    public static EditProducerRequest getEditProducerRequest() {
        var editProducerRequest = new EditProducerRequest();
        editProducerRequest.setProducerID(BrandID.newID().getValue().toString());
        editProducerRequest.setName(NameStub.getName().getValue());
        return editProducerRequest;
    }


    public static GetProducerByIDRequest getProducerByIDRequest() {
        var getProducerByIDRequest = new GetProducerByIDRequest();
        getProducerByIDRequest.setProducerID(BrandID.newID().getValue().toString());
        return getProducerByIDRequest;
    }
}
