package kz.arabro.planogram.nomenclature.adapter.controller;

import kz.arabro.planogram.nomenclature.boundary.model.ProductCreateInfo;
import kz.arabro.planogram.nomenclature.boundary.model.ProductEditInfo;
import kz.arabro.planogram.nomenclature.boundary.usecase.CreateProductUseCase;
import kz.arabro.planogram.nomenclature.boundary.usecase.DeleteProductUseCase;
import kz.arabro.planogram.nomenclature.boundary.usecase.ReadDataProductUseCase;
import kz.arabro.planogram.nomenclature.boundary.usecase.UpdateProductUseCase;
import kz.arabro.planogram.nomenclature.domain.exception.CodedException;
import kz.arabro.planogram.nomenclature.testdouble.controller.ProductRequestStub;
import kz.arabro.planogram.nomenclature.testdouble.entity.ProductStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private CreateProductUseCase createProductUseCase;

    @Mock
    private DeleteProductUseCase deleteProductUseCase;

    @Mock
    private UpdateProductUseCase updateProductUseCase;

    @Mock
    private ReadDataProductUseCase readDataProductUseCase;

    private ProductController productController;

    @BeforeEach
    void setUp() {
        this.productController = new ProductController(
                createProductUseCase,
                deleteProductUseCase,
                updateProductUseCase,
                readDataProductUseCase
        );
    }

    @Test
    void createProduct_CreateProductRequestIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> productController.createProduct(null));
        assertEquals(ControllerError.CREATE_PRODUCT_REQUEST_IS_REQUIRED, ex.getCode());
    }

    @Test
    void createProduct_UseCaseIsThrowEx_ThrowEx() {
        var createProductRequest = ProductRequestStub.getCreateProductRequest();

        when(createProductUseCase.execute(any(ProductCreateInfo.class))).thenThrow(RuntimeException.class);
        assertThrows(RuntimeException.class, () -> productController.createProduct(createProductRequest));
    }

    @Test
    void createProduct_NoException_ReturnCreateProductResponse() {
        var product = ProductStub.getProduct();
        when(createProductUseCase.execute(any(ProductCreateInfo.class))).thenReturn(product);

        var createProductRequest = ProductRequestStub.getCreateProductRequest();
        var productResponse = productController.createProduct(createProductRequest);
        assertEquals(product.getProductID().getValue().toString(), productResponse.getProductID());
    }

    @Test
    void deleteProduct_DeleteProductRequestIsThrowEx_ThrowEx() {
        doThrow(RuntimeException.class).when(deleteProductUseCase).deleteProductByID(anyString());

        var deleteProductRequest = ProductRequestStub.getDeleteProductRequest();

        assertThrows(RuntimeException.class, () -> productController.deleteProduct(deleteProductRequest));
    }

    @Test
    void deleteProduct_NoException_ReturnHttpStatusOk() {
        doNothing().when(deleteProductUseCase).deleteProductByID(anyString());

        var deleteProductRequest = ProductRequestStub.getDeleteProductRequest();

        var response = productController.deleteProduct(deleteProductRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void editProduct_EditProductRequestIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> productController.editProduct(null));
        assertEquals(ControllerError.EDIT_PRODUCT_REQUEST_IS_REQUIRED, ex.getCode());
    }

    @Test
    void editProduct_UseCaseIsThrowEx_ThrowEx() {
        var editProductRequest = ProductRequestStub.getEditProductRequest();

        doThrow(RuntimeException.class).when(updateProductUseCase).update(any(ProductEditInfo.class));
        assertThrows(RuntimeException.class, () -> productController.editProduct(editProductRequest));
    }

    @Test
    void editProduct_NoException_ReturnEditBrandResponse() {
        doNothing().when(updateProductUseCase).update(any(ProductEditInfo.class));

        var editProductRequest = ProductRequestStub.getEditProductRequest();

        var editProductResponse = productController.editProduct(editProductRequest);

        assertNotNull(editProductResponse);

        var editProductRequestID = editProductRequest.getProductID();
        var editProductResponseID = editProductResponse.getProductID();

        assertEquals(editProductRequestID, editProductResponseID);
    }

    @Test
    void getProductByID_GetProductByIDRequestIsNull_RuntimeEx() {
        assertThrows(RuntimeException.class, () -> productController.getProductByID(null));
    }

    @Test
    void getProductByID_NoException_ReturnProductResponse() {
        var product = ProductStub.getProduct();

        when(readDataProductUseCase.findByID(anyString())).thenReturn(product);

        var getProductByIDRequest = ProductRequestStub.getProductByIDRequest();

        var productResponse = productController.getProductByID(getProductByIDRequest);

        assertNotNull(productResponse);
        assertEquals(product.getProductID().getValue().toString(), productResponse.getProductID());
    }

    @Test
    void getProductByCode1C_GetProductByCode1CRequestIsNull_RuntimeEx() {
        assertThrows(RuntimeException.class, () -> productController.getProductByID(null));
    }

    @Test
    void getProductByCode1C_NoException_ReturnProductResponse() {
        var product = ProductStub.getProduct();

        when(readDataProductUseCase.findByCode1C(anyString())).thenReturn(product);

        var getProductByCode1CRequest = ProductRequestStub.getProductByCode1CRequest();

        var productResponse = productController.getProductByCode1C(getProductByCode1CRequest);

        assertNotNull(productResponse);
        assertEquals(product.getProductID().getValue().toString(), productResponse.getProductID());
    }

    @Test
    void getAllProducts_NotValues_ReturnProductResponses() {
        var count = 5;

        var products = ProductStub.getProducts(count);

        when(readDataProductUseCase.findAll()).thenReturn(products);

        var productResponses = productController.getAllProducts();

        assertNotNull(productResponses);

        for (int i = 0; i < count; i++) {
            assertEquals(
                    products.get(i).getProductID().getValue().toString(),
                    productResponses.get(i).getProductID()
            );
        }
    }

    @Test
    void getProductsByProducer_GetProductsByProducerIDRequestIsNull_RuntimeEx() {
        assertThrows(RuntimeException.class, () -> productController.getProductsByProducer(null));
    }

    @Test
    void getProductsByProducer_NoException_ReturnProductResponses() {
        var count = 5;

        var products = ProductStub.getProducts(count);

        when(readDataProductUseCase.findByProducer(anyString())).thenReturn(products);

        var getProductsByProducerIDRequest = ProductRequestStub.getProductsByProducerIDRequest();

        var productResponses = productController.getProductsByProducer(getProductsByProducerIDRequest);

        assertNotNull(productResponses);
        for (int i = 0; i < count; i++) {
            assertEquals(
                    products.get(i).getProductID().getValue().toString(),
                    productResponses.get(i).getProductID()
            );
        }
    }

    @Test
    void getProductsByCategory_GetProductsByCategoryIDRequestIsNull_RuntimeEx() {
        assertThrows(RuntimeException.class, () -> productController.getProductsByCategory(null));
    }

    @Test
    void getProductsByCategory_NoException_ReturnProductResponses() {
        var count = 5;

        var products = ProductStub.getProducts(count);

        when(readDataProductUseCase.findByCategory(anyString())).thenReturn(products);

        var getProductsByCategoryIDRequest = ProductRequestStub.getProductsByCategoryIDRequest();

        var productResponses = productController.getProductsByCategory(getProductsByCategoryIDRequest);

        assertNotNull(productResponses);
        for (int i = 0; i < count; i++) {
            assertEquals(
                    products.get(i).getProductID().getValue().toString(),
                    productResponses.get(i).getProductID()
            );
        }
    }

    @Test
    void getProductsByBrand_GetProductsByBrandIDRequestIsNull_RuntimeEx() {
        assertThrows(RuntimeException.class, () -> productController.getProductsByBrand(null));
    }

    @Test
    void getProductsByBrand_NoException_ReturnProductResponses() {
        var count = 5;

        var products = ProductStub.getProducts(count);

        when(readDataProductUseCase.findByBrand(anyString())).thenReturn(products);

        var getProductsByBrandIDRequest = ProductRequestStub.getProductsByBrandIDRequest();

        var productResponses = productController.getProductsByBrand(getProductsByBrandIDRequest);

        assertNotNull(productResponses);
        for (int i = 0; i < count; i++) {
            assertEquals(
                    products.get(i).getProductID().getValue().toString(),
                    productResponses.get(i).getProductID()
            );
        }
    }
}