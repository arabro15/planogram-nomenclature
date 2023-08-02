package kz.arabro.planogram.nomenclature.adapter.controller.converter;

import kz.arabro.planogram.nomenclature.adapter.controller.ControllerError;
import kz.arabro.planogram.nomenclature.adapter.controller.request.CreateProductRequest;
import kz.arabro.planogram.nomenclature.adapter.controller.request.EditProductRequest;
import kz.arabro.planogram.nomenclature.domain.exception.CodedException;
import kz.arabro.planogram.nomenclature.testdouble.entity.ProductStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductRequestConverterTest {

    @Test
    void createProductRequestToModel_CreateProductRequestIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> ProductRequestConverter.createProductRequestToModel(null));
        assertEquals(ControllerError.CREATE_PRODUCT_REQUEST_IS_REQUIRED, ex.getCode());
    }

    @Test
    void createProductRequestToModel_AllValueIsValid_ReturnProductCreateInfo() {
        var product = ProductStub.getProduct();

        var code1C = product.getCode1C();
        var rusName = product.getRusName().getValue();
        var kazName = product.getKazName().getValue();
        var categoryIDStr = product.getCategory().getId().getValue().toString();
        var brandIDStr = product.getBrand().getId().getValue().toString();
        var producerIDStr = product.getProducer().getId().getValue().toString();
        var barcode = product.getBarcode().getValue();
        var price = product.getPrice().getValue();
        var height = String.valueOf(product.getSize().getHeight());
        var weight = String.valueOf(product.getSize().getWeight());
        var length = String.valueOf(product.getSize().getLength());
        var imagePath = product.getImagePath();

        var createProductRequest = new CreateProductRequest();
        createProductRequest.setCode1C(code1C);
        createProductRequest.setRusName(rusName);
        createProductRequest.setKazName(kazName);
        createProductRequest.setCategoryID(categoryIDStr);
        createProductRequest.setBrandID(brandIDStr);
        createProductRequest.setProducerID(producerIDStr);
        createProductRequest.setBarcode(barcode);
        createProductRequest.setPrice(price);
        createProductRequest.setHeight(height);
        createProductRequest.setWeight(weight);
        createProductRequest.setLength(length);
        createProductRequest.setImagePath(imagePath);

        var productCreateInfo = ProductRequestConverter.createProductRequestToModel(createProductRequest);

        assertNotNull(productCreateInfo);
        assertEquals(code1C, productCreateInfo.getCode1C());
        assertEquals(rusName, productCreateInfo.getRusName());
        assertEquals(kazName, productCreateInfo.getKazName());
        assertEquals(categoryIDStr, productCreateInfo.getCategory());
        assertEquals(brandIDStr, productCreateInfo.getBrand());
        assertEquals(producerIDStr, productCreateInfo.getProducer());
        assertEquals(barcode, productCreateInfo.getBarcode());
        assertEquals(price, productCreateInfo.getPrice());
        assertEquals(height, productCreateInfo.getHeight());
        assertEquals(weight, productCreateInfo.getWeight());
        assertEquals(length, productCreateInfo.getLength());
        assertEquals(imagePath, productCreateInfo.getImagePath());
    }

    @Test
    void editProductRequestToModel_EditProductRequestIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> ProductRequestConverter.editProductRequestToModel(null));
        assertEquals(ControllerError.EDIT_PRODUCT_REQUEST_IS_REQUIRED, ex.getCode());
    }

    @Test
    void editProductRequestToModel_AllValueIsValid_ReturnProductEditInfo() {
        var product = ProductStub.getProduct();

        var productIDStr = product.getProductID().getValue().toString();
        var code1C = product.getCode1C();
        var rusName = product.getRusName().getValue();
        var kazName = product.getKazName().getValue();
        var categoryIDStr = product.getCategory().getId().getValue().toString();
        var brandIDStr = product.getBrand().getId().getValue().toString();
        var producerIDStr = product.getProducer().getId().getValue().toString();
        var barcode = product.getBarcode().getValue();
        var price = product.getPrice().getValue();
        var height = String.valueOf(product.getSize().getHeight());
        var weight = String.valueOf(product.getSize().getWeight());
        var length = String.valueOf(product.getSize().getLength());
        var imagePath = product.getImagePath();


        var EditProductRequest = new EditProductRequest();
        EditProductRequest.setProductID(productIDStr);
        EditProductRequest.setCode1C(code1C);
        EditProductRequest.setRusName(rusName);
        EditProductRequest.setKazName(kazName);
        EditProductRequest.setCategoryID(categoryIDStr);
        EditProductRequest.setBrandID(brandIDStr);
        EditProductRequest.setProducerID(producerIDStr);
        EditProductRequest.setBarcode(barcode);
        EditProductRequest.setPrice(price);
        EditProductRequest.setHeight(height);
        EditProductRequest.setWeight(weight);
        EditProductRequest.setLength(length);
        EditProductRequest.setImagePath(imagePath);

        var productEditInfo = ProductRequestConverter.editProductRequestToModel(EditProductRequest);

        assertNotNull(productEditInfo);
        assertEquals(productIDStr, productEditInfo.getId());
        assertEquals(code1C, productEditInfo.getCode1C());
        assertEquals(rusName, productEditInfo.getRusName());
        assertEquals(kazName, productEditInfo.getKazName());
        assertEquals(categoryIDStr, productEditInfo.getCategory());
        assertEquals(brandIDStr, productEditInfo.getBrand());
        assertEquals(producerIDStr, productEditInfo.getProducer());
        assertEquals(barcode, productEditInfo.getBarcode());
        assertEquals(price, productEditInfo.getPrice());
        assertEquals(height, productEditInfo.getHeight());
        assertEquals(weight, productEditInfo.getWeight());
        assertEquals(length, productEditInfo.getLength());
        assertEquals(imagePath, productEditInfo.getImagePath());
    }
}