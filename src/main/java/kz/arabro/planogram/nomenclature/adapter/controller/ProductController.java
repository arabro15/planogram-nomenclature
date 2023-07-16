package kz.arabro.planogram.nomenclature.adapter.controller;

import kz.arabro.planogram.nomenclature.adapter.controller.converter.ProductRequestConverter;
import kz.arabro.planogram.nomenclature.adapter.controller.converter.ProductResponseConverter;
import kz.arabro.planogram.nomenclature.adapter.controller.request.*;
import kz.arabro.planogram.nomenclature.adapter.controller.response.CreateProductResponse;
import kz.arabro.planogram.nomenclature.adapter.controller.response.EditProductResponse;
import kz.arabro.planogram.nomenclature.adapter.controller.response.ProductResponse;
import kz.arabro.planogram.nomenclature.boundary.usecase.ProductCreatorUseCase;
import kz.arabro.planogram.nomenclature.boundary.usecase.ProductDeleteUseCase;
import kz.arabro.planogram.nomenclature.boundary.usecase.ProductEditorUseCase;
import kz.arabro.planogram.nomenclature.boundary.usecase.ProductReadDataUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
public class ProductController {

    private final ProductCreatorUseCase productCreatorUseCase;
    private final ProductDeleteUseCase productDeleteUseCase;
    private final ProductEditorUseCase productEditorUseCase;
    private final ProductReadDataUseCase productReadDataUseCase;

    public ProductController(ProductCreatorUseCase productCreatorUseCase,
                             ProductDeleteUseCase productDeleteUseCase,
                             ProductEditorUseCase productEditorUseCase,
                             ProductReadDataUseCase productReadDataUseCase) {
        this.productCreatorUseCase = productCreatorUseCase;
        this.productDeleteUseCase = productDeleteUseCase;
        this.productEditorUseCase = productEditorUseCase;
        this.productReadDataUseCase = productReadDataUseCase;
    }

    @PostMapping(path = "/create-product")
    public CreateProductResponse createProduct(@RequestBody CreateProductRequest request) {
        var info = ProductRequestConverter.createProductRequestToModel(request);

        var product = productCreatorUseCase.execute(info);

        var response = new CreateProductResponse();
        response.setProductID(product.getProductID().getValue().toString());
        return response;
    }

    @PostMapping(path = "/delete-by-id-product")
    public ResponseEntity<Object> deleteProduct(@RequestBody DeleteProductRequest request) {
        productDeleteUseCase.deleteProductByID(request.getProductID());
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping(path = "/edit-product")
    public EditProductResponse editProduct(@RequestBody EditProductRequest request) {
        var product = ProductRequestConverter.editProductRequestToModel(request);

        productEditorUseCase.update(product);

        var response = new EditProductResponse();
        response.setProductID(product.getId());

        return response;
    }

    @PostMapping(path = "/get-product-by-id")
    public ProductResponse getProductByID(@RequestBody GetProductByIDRequest request) {
        var product = productReadDataUseCase.findByID(request.getProductID());
        return ProductResponseConverter.productToResponse(product);
    }

    @PostMapping(path = "/get-product-by-code-1c")
    public ProductResponse getProductByCode1C(@RequestBody GetProductByCode1CRequest request) {
        var product = productReadDataUseCase.findByCode1C(request.getCode1C());
        return ProductResponseConverter.productToResponse(product);
    }

    @PostMapping(path = "/get-all-products")
    public List<ProductResponse> getAllProducts() {
        var products = productReadDataUseCase.findAll();
        return ProductResponseConverter.productsToResponses(products);
    }

    @PostMapping(path = "/get-products-by-producer")
    public List<ProductResponse> getProductsByProducer(@RequestBody GetProductsByProducerIDRequest request) {
        var products = productReadDataUseCase.findAllByProducer(request.getProducerID());
        return ProductResponseConverter.productsToResponses(products);
    }

    @PostMapping(path = "/get-products-by-category")
    public List<ProductResponse> getProductsByCategory(@RequestBody GetProductsByCategoryIDRequest request) {
        var products = productReadDataUseCase.findAllByCategory(request.getCategoryID());
        return ProductResponseConverter.productsToResponses(products);
    }

    @PostMapping(path = "/get-products-by-brand")
    public List<ProductResponse> getProductsByBrand(@RequestBody GetProductsByBrandIDRequest request) {
        var products = productReadDataUseCase.findAllByBrand(request.getBrandID());
        return ProductResponseConverter.productsToResponses(products);
    }

}
