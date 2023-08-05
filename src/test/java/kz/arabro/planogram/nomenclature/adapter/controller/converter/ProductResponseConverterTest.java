package kz.arabro.planogram.nomenclature.adapter.controller.converter;

import kz.arabro.planogram.nomenclature.adapter.controller.ControllerError;
import kz.arabro.planogram.nomenclature.domain.exception.CodedException;
import kz.arabro.planogram.nomenclature.testdouble.entity.ProductStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductResponseConverterTest {

    @Test
    void productToResponse_ProductIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> ProductResponseConverter.productToResponse(null));
        assertEquals(ControllerError.PRODUCT_IS_REQUIRED, ex.getCode());
    }

    @Test
    void productToResponse_ValueIsValid_ReturnProductResponse() {
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

        var productResponse = ProductResponseConverter.productToResponse(product);

        assertNotNull(productResponse);
        assertEquals(productIDStr, productResponse.getProductID());
        assertEquals(code1C, productResponse.getCode1C());
        assertEquals(rusName, productResponse.getRusName());
        assertEquals(kazName, productResponse.getKazName());
        assertEquals(categoryIDStr, productResponse.getCategory().getCategoryID());
        assertEquals(brandIDStr, productResponse.getBrand().getBrandID());
        assertEquals(producerIDStr, productResponse.getProducer().getProducerID());
        assertEquals(barcode, productResponse.getBarcode());
        assertEquals(price, productResponse.getPrice());
        assertEquals(height, productResponse.getHeight());
        assertEquals(weight, productResponse.getWeight());
        assertEquals(length, productResponse.getLength());
        assertEquals(imagePath, productResponse.getImagePath());
    }

    @Test
    void productsToResponses_ProductsIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> ProductResponseConverter.productsToResponses(null));
        assertEquals(ControllerError.PRODUCTS_IS_REQUIRED, ex.getCode());
    }

    @Test
    void productsToResponses_ValueIsValid_ReturnProductResponses() {
        var count = 5;

        var products = ProductStub.getProducts(count);

        var productResponses = ProductResponseConverter.productsToResponses(products);

        assertNotNull(productResponses);

        for (int i = 0; i < count; i++) {
            var productIDStr = products.get(i).getProductID().getValue().toString();
            var code1C = products.get(i).getCode1C();
            var rusName = products.get(i).getRusName().getValue();
            var kazName = products.get(i).getKazName().getValue();
            var categoryIDStr = products.get(i).getCategory().getId().getValue().toString();
            var brandIDStr = products.get(i).getBrand().getId().getValue().toString();
            var producerIDStr = products.get(i).getProducer().getId().getValue().toString();
            var barcode = products.get(i).getBarcode().getValue();
            var price = products.get(i).getPrice().getValue();
            var height = String.valueOf(products.get(i).getSize().getHeight());
            var weight = String.valueOf(products.get(i).getSize().getWeight());
            var length = String.valueOf(products.get(i).getSize().getLength());
            var imagePath = products.get(i).getImagePath();

            var productResponseID = productResponses.get(i).getProductID();
            var productResponseCode1c = productResponses.get(i).getCode1C();
            var productResponseRusName = productResponses.get(i).getRusName();
            var productResponseKazName = productResponses.get(i).getKazName();
            var productResponseCategoryID = productResponses.get(i).getCategory().getCategoryID();
            var productResponseBrandID = productResponses.get(i).getBrand().getBrandID();
            var productResponseProducerID = productResponses.get(i).getProducer().getProducerID();
            var productResponseBarcode = productResponses.get(i).getBarcode();
            var productResponsePrice = productResponses.get(i).getPrice();
            var productResponseHeight = productResponses.get(i).getHeight();
            var productResponseWeight = productResponses.get(i).getWeight();
            var productResponseLength = productResponses.get(i).getLength();
            var productResponseImagePath = productResponses.get(i).getImagePath();

            assertEquals(productIDStr, productResponseID);
            assertEquals(code1C, productResponseCode1c);
            assertEquals(rusName, productResponseRusName);
            assertEquals(kazName, productResponseKazName);
            assertEquals(categoryIDStr, productResponseCategoryID);
            assertEquals(brandIDStr, productResponseBrandID);
            assertEquals(producerIDStr, productResponseProducerID);
            assertEquals(barcode, productResponseBarcode);
            assertEquals(price, productResponsePrice);
            assertEquals(height, productResponseHeight);
            assertEquals(weight, productResponseWeight);
            assertEquals(length, productResponseLength);
            assertEquals(imagePath, productResponseImagePath);
        }
    }
}