package kz.arabro.planogram.nomenclature.integration.adapter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.arabro.planogram.nomenclature.adapter.controller.response.*;
import kz.arabro.planogram.nomenclature.boundary.model.ProductEditInfo;
import kz.arabro.planogram.nomenclature.boundary.usecase.CreateProductUseCase;
import kz.arabro.planogram.nomenclature.boundary.usecase.DeleteProductUseCase;
import kz.arabro.planogram.nomenclature.boundary.usecase.ReadDataProductUseCase;
import kz.arabro.planogram.nomenclature.boundary.usecase.UpdateProductUseCase;
import kz.arabro.planogram.nomenclature.domain.entity.product.Product;
import kz.arabro.planogram.nomenclature.testdouble.controller.ProductRequestStub;
import kz.arabro.planogram.nomenclature.testdouble.entity.ProductStub;
import kz.arabro.planogram.nomenclature.util.annotation.IntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
class ProductControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private CreateProductUseCase createProductUseCase;

    @MockBean
    private DeleteProductUseCase deleteProductUseCase;

    @MockBean
    private UpdateProductUseCase updateProductUseCase;

    @MockBean
    private ReadDataProductUseCase readDataProductUseCase;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.
                webAppContextSetup(webApplicationContext).
                build();
    }

    @Test
    void createProduct_RequestIsNull_ReturnIsInternalServerError() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/api/v1/create-product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void createBrand_RequestIsValid_ReturnCreateBrandResponse() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        var createProductRequest = ProductRequestStub.getCreateProductRequest();

        var product = ProductStub.getProduct();
        when(createProductUseCase.execute(any())).thenReturn(product);

        var createProductResponse = new CreateProductResponse();
        createProductResponse.setProductID(product.getProductID().getValue().toString());

        mockMvc.perform(post("/api/v1/create-product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createProductRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(createProductResponse)));
    }

    @Test
    void deleteProduct_RequestIsInvalid_ReturnIsInternalServerError() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/api/v1/delete-by-id-product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void deleteProduct_RequestIsValid_ReturnIsStatusOk() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        var deleteProductRequest = ProductRequestStub.getDeleteProductRequest();

        doNothing().when(deleteProductUseCase).deleteProductByID(anyString());

        mockMvc.perform(post("/api/v1/delete-by-id-product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(deleteProductRequest)))
                .andExpect(status().isOk());
    }

    @Test
    void editProduct_RequestIsInvalid_ReturnIsInternalServerError() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/api/v1/edit-product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void editProduct_RequestIsValid_ReturnEditProductResponse() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        var editProductRequest = ProductRequestStub.getEditProductRequest();

        doNothing().when(updateProductUseCase).update(any(ProductEditInfo.class));

        var editProductResponse = new EditProductResponse();
        editProductResponse.setProductID(editProductRequest.getProductID());

        mockMvc.perform(post("/api/v1/edit-product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(editProductRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(editProductResponse)));
    }

    @Test
    void getProductByID_RequestIsInvalid_ReturnIsInternalServerError() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/api/v1/get-product-by-id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void getProductByID_RequestIsValid_ReturnProductResponse() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        var product = ProductStub.getProduct();
        var getProductByIDRequest = ProductRequestStub.getProductByIDRequest();

        when(readDataProductUseCase.findByID(anyString())).thenReturn(product);

        var productResponse = productToResponse(product);

        mockMvc.perform(post("/api/v1/get-product-by-id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getProductByIDRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(productResponse)));
    }

    @Test
    void getProductByCode1C_RequestIsInvalid_ReturnIsInternalServerError() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/api/v1/get-product-by-code-1c")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void getProductByCode1C_RequestIsValid_ReturnProductResponse() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        var product = ProductStub.getProduct();
        var getProductByCode1CRequest = ProductRequestStub.getProductByCode1CRequest();

        when(readDataProductUseCase.findByCode1C(anyString())).thenReturn(product);

        var productResponse = productToResponse(product);

        mockMvc.perform(post("/api/v1/get-product-by-code-1c")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getProductByCode1CRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(productResponse)));
    }

    @Test
    void getAllProducts_RequestIsValid_ReturnProductResponses() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        var count = 5;
        var products = ProductStub.getProducts(count);

        var productResponses = new ArrayList<ProductResponse>(count);

        for (int i = 0; i < count; i++) {
            var productResponse = productToResponse(products.get(i));

            productResponses.add(productResponse);
        }

        when(readDataProductUseCase.findAll()).thenReturn(products);

        mockMvc.perform(post("/api/v1/get-all-products"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(productResponses)));
    }

    @Test
    void getProductsByProducer_RequestIsInvalid_ReturnIsInternalServerError() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/api/v1/get-products-by-producer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void getProductsByProducer_RequestIsValid_ReturnProductResponses() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        var count = 5;
        var products = ProductStub.getProducts(count);

        var getProductsByProducerIDRequest = ProductRequestStub.getProductsByProducerIDRequest();

        var productResponses = new ArrayList<ProductResponse>(count);

        for (int i = 0; i < count; i++) {
            var productResponse = productToResponse(products.get(i));

            productResponses.add(productResponse);
        }

        when(readDataProductUseCase.findByProducer(anyString())).thenReturn(products);

        mockMvc.perform(post("/api/v1/get-products-by-producer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getProductsByProducerIDRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(productResponses)));
    }

    @Test
    void getProductsByCategory_RequestIsInvalid_ReturnIsInternalServerError() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/api/v1/get-products-by-category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void getProductsByCategory_RequestIsValid_ReturnProductResponses() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        var count = 5;
        var products = ProductStub.getProducts(count);

        var getProductsByCategoryIDRequest = ProductRequestStub.getProductsByCategoryIDRequest();

        var productResponses = new ArrayList<ProductResponse>(count);

        for (int i = 0; i < count; i++) {
            var productResponse = productToResponse(products.get(i));

            productResponses.add(productResponse);
        }

        when(readDataProductUseCase.findByCategory(anyString())).thenReturn(products);

        mockMvc.perform(post("/api/v1/get-products-by-category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getProductsByCategoryIDRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(productResponses)));
    }

    @Test
    void getProductsByBrand_RequestIsInvalid_ReturnIsInternalServerError() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/api/v1/get-products-by-brand")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void getProductsByBrand_RequestIsValid_ReturnProductResponses() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        var count = 5;
        var products = ProductStub.getProducts(count);

        var getProductsByBrandIDRequest = ProductRequestStub.getProductsByBrandIDRequest();

        var productResponses = new ArrayList<ProductResponse>(count);

        for (int i = 0; i < count; i++) {
            var productResponse = productToResponse(products.get(i));

            productResponses.add(productResponse);
        }

        when(readDataProductUseCase.findByBrand(anyString())).thenReturn(products);

        mockMvc.perform(post("/api/v1/get-products-by-brand")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getProductsByBrandIDRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(productResponses)));
    }

    private ProductResponse productToResponse(Product product) {
        var productResponse = new ProductResponse();
        productResponse.setProductID(product.getProductID().getValue().toString());
        productResponse.setCode1C(product.getCode1C());
        productResponse.setRusName(product.getRusName().getValue());
        productResponse.setKazName(product.getKazName().getValue());

        var category = product.getCategory();
        var categoryResponse  = new CategoryResponse();
        categoryResponse.setCategoryID(category.getId().getValue().toString());
        categoryResponse.setName(category.getName().getValue());
        categoryResponse.setColor(category.getColor().name());
        var categoryParentIDOpt = category.getParentID();
        assertTrue(categoryParentIDOpt.isPresent());
        categoryResponse.setParentID(categoryParentIDOpt.get().getValue().toString());

        productResponse.setCategory(categoryResponse);

        var brand = product.getBrand();
        var brandResponse = new BrandResponse();
        brandResponse.setBrandID(brand.getId().getValue().toString());
        brandResponse.setName(brand.getName().getValue());

        productResponse.setBrand(brandResponse);

        var producer = product.getProducer();
        var producerResponse = new ProducerResponse();
        producerResponse.setProducerID(producer.getId().getValue().toString());
        producerResponse.setName(producer.getName().getValue());

        productResponse.setProducer(producerResponse);

        productResponse.setBarcode(product.getBarcode().getValue());
        productResponse.setPrice(product.getPrice().getValue());
        productResponse.setHeight(String.valueOf(product.getSize().getHeight()));
        productResponse.setWeight(String.valueOf(product.getSize().getWeight()));
        productResponse.setLength(String.valueOf(product.getSize().getLength()));
        productResponse.setImagePath(product.getImagePath());
        productResponse.setCode1C(product.getCode1C());
        productResponse.setCode1C(product.getCode1C());

        return productResponse;
    }
}