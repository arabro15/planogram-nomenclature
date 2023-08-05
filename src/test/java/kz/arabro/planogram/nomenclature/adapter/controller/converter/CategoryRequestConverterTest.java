package kz.arabro.planogram.nomenclature.adapter.controller.converter;

import kz.arabro.planogram.nomenclature.adapter.controller.ControllerError;
import kz.arabro.planogram.nomenclature.adapter.controller.request.CreateCategoryRequest;
import kz.arabro.planogram.nomenclature.adapter.controller.request.EditCategoryRequest;
import kz.arabro.planogram.nomenclature.domain.entity.category.CategoryID;
import kz.arabro.planogram.nomenclature.domain.entity.category.Color;
import kz.arabro.planogram.nomenclature.domain.exception.CodedException;
import kz.arabro.planogram.nomenclature.testdouble.entity.NameStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryRequestConverterTest {

    @Test
    void createCategoryRequestToModel_CreateCategoryRequestIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> CategoryRequestConverter.createCategoryRequestToModel(null));
        assertEquals(ControllerError.CATEGORY_REQUEST_IS_REQUIRED, ex.getCode());
    }

    @Test
    void createCategoryRequestToModel_CreateCategoryRequestNameIsNull_ThrowEx() {
        var createCategoryRequest = new CreateCategoryRequest();
        createCategoryRequest.setName(null);

        var ex = assertThrows(CodedException.class, () -> CategoryRequestConverter.createCategoryRequestToModel(createCategoryRequest));
        assertEquals(ControllerError.CATEGORY_REQUEST_NULL_IS_REQUIRED, ex.getCode());
    }

    @Test
    void createCategoryRequestToModel_AllValueIsValid_ReturnCategoryCreateInfo() {
        var name = NameStub.getName().getValue();
        var color = Color.RED.name();
        var parentID = CategoryID.newID().getValue().toString();

        var createCategoryRequest = new CreateCategoryRequest();
        createCategoryRequest.setName(name);
        createCategoryRequest.setColor(color);
        createCategoryRequest.setParentID(parentID);

        var categoryCreateInfo = CategoryRequestConverter.createCategoryRequestToModel(createCategoryRequest);

        assertNotNull(categoryCreateInfo);
        assertEquals(name, categoryCreateInfo.getName());
        assertEquals(color, categoryCreateInfo.getColor());
        assertEquals(parentID, categoryCreateInfo.getParentID());
    }

    @Test
    void editCategoryRequestToModel_EditCategoryRequestIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> CategoryRequestConverter.editCategoryRequestToModel(null));
        assertEquals(ControllerError.CATEGORY_REQUEST_IS_REQUIRED, ex.getCode());
    }

    @Test
    void editCategoryRequestToModel_EditCategoryRequestCategoryIDIsNull_ThrowEx() {
        var editCategoryRequest = new EditCategoryRequest();
        editCategoryRequest.setCategoryID(null);

        var ex = assertThrows(CodedException.class, () -> CategoryRequestConverter.editCategoryRequestToModel(editCategoryRequest));
        assertEquals(ControllerError.CATEGORY_REQUEST_NULL_IS_REQUIRED, ex.getCode());
    }

    @Test
    void editCategoryRequestToModel_AllValueIsValid_ReturnCategoryEditInfo() {
        var categoryIDStr = CategoryID.newID().getValue().toString();
        var name = NameStub.getName().getValue();
        var color = Color.RED.name();
        var parentIDStr = CategoryID.newID().getValue().toString();

        var editCategoryRequest = new EditCategoryRequest();
        editCategoryRequest.setCategoryID(categoryIDStr);
        editCategoryRequest.setName(name);
        editCategoryRequest.setColor(color);
        editCategoryRequest.setParentID(parentIDStr);

        var categoryEditInfo = CategoryRequestConverter.editCategoryRequestToModel(editCategoryRequest);

        assertNotNull(categoryEditInfo);
        assertEquals(categoryIDStr, categoryEditInfo.getCategoryID());
        assertEquals(name, categoryEditInfo.getName());
        assertEquals(color, categoryEditInfo.getColor());
        assertEquals(parentIDStr, categoryEditInfo.getParentID());
    }

}