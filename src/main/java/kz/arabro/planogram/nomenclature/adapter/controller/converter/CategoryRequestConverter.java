package kz.arabro.planogram.nomenclature.adapter.controller.converter;

import kz.arabro.planogram.nomenclature.adapter.controller.ControllerError;
import kz.arabro.planogram.nomenclature.adapter.controller.request.CreateCategoryRequest;
import kz.arabro.planogram.nomenclature.adapter.controller.request.EditCategoryRequest;
import kz.arabro.planogram.nomenclature.boundary.model.CategoryCreateInfo;
import kz.arabro.planogram.nomenclature.boundary.model.CategoryEditInfo;

public class CategoryRequestConverter {

    public static CategoryCreateInfo createCategoryRequestToModel(CreateCategoryRequest request) {
        if (request == null) {
            throw ControllerError.errCategoryRequestIsRequired();
        }

        if (request.getName() == null) {
            throw ControllerError.errCategoryRequestNullIsRequired();
        }

        var name = request.getName();
        var color = request.getColor();
        var parentID = request.getParentID();

        var categoryCreateInfo = new CategoryCreateInfo(name);
        categoryCreateInfo.setColor(color);
        categoryCreateInfo.setParentID(parentID);

        return categoryCreateInfo;
    }

    public static CategoryEditInfo editCategoryRequestToModel(EditCategoryRequest request) {
        if (request == null) {
            throw ControllerError.errCategoryRequestIsRequired();
        }

        if (request.getCategoryID() == null) {
            throw ControllerError.errCategoryRequestNullIsRequired();
        }

        var categoryEditInfo = new CategoryEditInfo(
                request.getCategoryID(),
                request.getName()
        );

        categoryEditInfo.setColor(request.getColor());
        categoryEditInfo.setParentID(request.getParentID());

        return categoryEditInfo;
    }

    private CategoryRequestConverter() {}

}
