package kz.arabro.planogram.nomenclature.adapter.controller;

import kz.arabro.planogram.nomenclature.adapter.controller.converter.ProductRequestConverter;
import kz.arabro.planogram.nomenclature.adapter.controller.converter.ProductResponseConverter;
import kz.arabro.planogram.nomenclature.adapter.controller.request.*;
import kz.arabro.planogram.nomenclature.adapter.controller.response.CreateProductResponse;
import kz.arabro.planogram.nomenclature.adapter.controller.response.EditProductResponse;
import kz.arabro.planogram.nomenclature.adapter.controller.response.ProductResponse;
import kz.arabro.planogram.nomenclature.boundary.usecase.CreateProductUseCase;
import kz.arabro.planogram.nomenclature.boundary.usecase.DeleteProductUseCase;
import kz.arabro.planogram.nomenclature.boundary.usecase.UpdateProductUseCase;
import kz.arabro.planogram.nomenclature.boundary.usecase.ReadDataProductUseCase;
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

    private final CreateProductUseCase createProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final ReadDataProductUseCase readDataProductUseCase;

    public ProductController(CreateProductUseCase createProductUseCase,
                             DeleteProductUseCase deleteProductUseCase,
                             UpdateProductUseCase updateProductUseCase,
                             ReadDataProductUseCase readDataProductUseCase) {
        this.createProductUseCase = createProductUseCase;
        this.deleteProductUseCase = deleteProductUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.readDataProductUseCase = readDataProductUseCase;
    }

    @PostMapping(path = "/create-product")
    public CreateProductResponse createProduct(@RequestBody CreateProductRequest request) {
        var info = ProductRequestConverter.createProductRequestToModel(request);

        var product = createProductUseCase.execute(info);

        var response = new CreateProductResponse();
        response.setProductID(product.getProductID().getValue().toString());
        return response;
    }

    @PostMapping(path = "/delete-by-id-product")
    public ResponseEntity<Object> deleteProduct(@RequestBody DeleteProductRequest request) {
        deleteProductUseCase.deleteProductByID(request.getProductID());
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping(path = "/edit-product")
    public EditProductResponse editProduct(@RequestBody EditProductRequest request) {
        var product = ProductRequestConverter.editProductRequestToModel(request);

        updateProductUseCase.update(product);

        var response = new EditProductResponse();
        response.setProductID(product.getId());

        return response;
    }

    @PostMapping(path = "/get-product-by-id")
    public ProductResponse getProductByID(@RequestBody GetProductByIDRequest request) {
        var product = readDataProductUseCase.findByID(request.getProductID());
        return ProductResponseConverter.productToResponse(product);
    }

    @PostMapping(path = "/get-product-by-code-1c")
    public ProductResponse getProductByCode1C(@RequestBody GetProductByCode1CRequest request) {
        var product = readDataProductUseCase.findByCode1C(request.getCode1C());
        return ProductResponseConverter.productToResponse(product);
    }

    @PostMapping(path = "/get-all-products")
    public List<ProductResponse> getAllProducts() {
        var products = readDataProductUseCase.findAll();
        return ProductResponseConverter.productsToResponses(products);
    }

    @PostMapping(path = "/get-products-by-producer")
    public List<ProductResponse> getProductsByProducer(@RequestBody GetProductsByProducerIDRequest request) {
        var products = readDataProductUseCase.findAllByProducer(request.getProducerID());
        return ProductResponseConverter.productsToResponses(products);
    }

    @PostMapping(path = "/get-products-by-category")
    public List<ProductResponse> getProductsByCategory(@RequestBody GetProductsByCategoryIDRequest request) {
        var products = readDataProductUseCase.findAllByCategory(request.getCategoryID());
        return ProductResponseConverter.productsToResponses(products);
    }

    @PostMapping(path = "/get-products-by-brand")
    public List<ProductResponse> getProductsByBrand(@RequestBody GetProductsByBrandIDRequest request) {
        var products = readDataProductUseCase.findAllByBrand(request.getBrandID());
        return ProductResponseConverter.productsToResponses(products);
    }

}
