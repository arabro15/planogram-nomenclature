package kz.arabro.planogram.nomenclature.domain.usecase;

import kz.arabro.planogram.nomenclature.boundary.repository.ProductRepository;
import kz.arabro.planogram.nomenclature.boundary.usecase.ReadDataProductUseCase;
import kz.arabro.planogram.nomenclature.domain.entity.brand.BrandID;
import kz.arabro.planogram.nomenclature.domain.entity.category.CategoryID;
import kz.arabro.planogram.nomenclature.domain.entity.producer.ProducerID;
import kz.arabro.planogram.nomenclature.domain.entity.product.Product;
import kz.arabro.planogram.nomenclature.domain.entity.product.ProductError;
import kz.arabro.planogram.nomenclature.domain.entity.product.ProductID;
import kz.arabro.planogram.nomenclature.domain.exception.CodedException;
import kz.arabro.planogram.nomenclature.testdouble.entity.ProductStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReadDataProductUseCaseImplTest {

    @Mock
    private ProductRepository productRepository;

    private ReadDataProductUseCase readDataProductUseCase;

    @BeforeEach
    void setUp() {
        this.readDataProductUseCase = new ReadDataProductUseCaseImpl(productRepository);
    }

    @Test
    void findByID_ProductIDStrIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> readDataProductUseCase.findByID(null));
        assertEquals(ProductError.PRODUCT_ID_VALUE_IS_REQUIRED, ex.getCode());
    }

    @Test
    void findByID_ProductIsNotFoundByID_ThrowEx() {
        var productIDStr = ProductID.newID().getValue().toString();

        when(productRepository.findByID(any(ProductID.class))).thenReturn(Optional.empty());

        var ex = assertThrows(CodedException.class, () -> readDataProductUseCase.findByID(productIDStr));
        assertEquals(UseCaseError.PRODUCT_IS_NOT_FOUND, ex.getCode());
    }

    @Test
    void findByID_ProductExists_ReturnProduct() {
        var product = ProductStub.getProduct();
        var productIDStr = product.getProductID().getValue().toString();

        when(productRepository.findByID(any(ProductID.class))).thenReturn(Optional.of(product));

        var actualProduct = readDataProductUseCase.findByID(productIDStr);
        assertNotNull(actualProduct);
        assertEquals(product, actualProduct);
    }

    @Test
    void findByCode1C_Code1CIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> readDataProductUseCase.findByCode1C(null));
        assertEquals(UseCaseError.PRODUCT_CODE_1C_IS_REQUIRED, ex.getCode());
    }

    @Test
    void findByCode1C_ProductByCode1CIsNotFoundByID_ThrowEx() {
        var code1c = "123456789";

        when(productRepository.findByCode1C(any(String.class))).thenReturn(Optional.empty());

        var ex = assertThrows(CodedException.class, () -> readDataProductUseCase.findByCode1C(code1c));
        assertEquals(UseCaseError.PRODUCT_BY_CODE_1C_IS_NOT_FOUND, ex.getCode());
    }

    @Test
    void findByCode1C_ProductExists_ReturnProduct() {
        var product = ProductStub.getProduct();
        var code1c = product.getCode1C();

        when(productRepository.findByCode1C(any(String.class))).thenReturn(Optional.of(product));

        var actualProduct = readDataProductUseCase.findByCode1C(code1c);

        assertNotNull(actualProduct);
        assertEquals(product, actualProduct);
    }

    @Test
    void findAll_ProductRepositoryIsRuntimeEx_ThrowEx() {
        when(productRepository.findAll()).thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class, () -> readDataProductUseCase.findAll());
    }

    @Test
    void findAll_ProductsIsEmpty_ReturnNull() {
        List<Product> products = emptyList();
        when(productRepository.findAll()).thenReturn(products);

        var actualProducts = readDataProductUseCase.findAll();

        assertNotNull(actualProducts);
        assertTrue(actualProducts.isEmpty());
    }

    @Test
    void findAll_ProductsExists_ReturnProducts() {
        var products = ProductStub.getProducts(5);
        when(productRepository.findAll()).thenReturn(products);

        var actualProducts = readDataProductUseCase.findAll();

        assertEquals(products, actualProducts);
    }

    @Test
    void findByProducer_ProducerIDStrIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> readDataProductUseCase.findByProducer(null));
        assertEquals(UseCaseError.PRODUCER_ID_IS_REQUIRED, ex.getCode());
    }

    @Test
    void findByProducer_ProductsByProducerIsEmpty_ReturnNull() {
        List<Product> products = emptyList();
        var producerID = ProducerID.newID();
        var producerIDStr = producerID.getValue().toString();

        when(productRepository.findByProducer(producerID)).thenReturn(products);

        var actualProducts = readDataProductUseCase.findByProducer(producerIDStr);

        assertNotNull(actualProducts);
        assertTrue(actualProducts.isEmpty());
    }

    @Test
    void findByProducer_ProductsByProducerExists_ReturnProducts() {
        var products = ProductStub.getProducts(5);
        var producerID = ProducerID.newID();
        var producerIDStr = producerID.getValue().toString();

        when(productRepository.findByProducer(producerID)).thenReturn(products);

        var actualProducts = readDataProductUseCase.findByProducer(producerIDStr);

        assertEquals(products, actualProducts);
    }

    @Test
    void findByCategory_CategoryIDStrIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> readDataProductUseCase.findByCategory(null));
        assertEquals(UseCaseError.CATEGORY_ID_IS_REQUIRED, ex.getCode());
    }

    @Test
    void findByCategory_ProductsByCategoryIsEmpty_ReturnNull() {
        List<Product> products = emptyList();
        var categoryID = CategoryID.newID();
        var categoryIDStr = categoryID.getValue().toString();

        when(productRepository.findByCategory(categoryID)).thenReturn(products);

        var actualProducts = readDataProductUseCase.findByCategory(categoryIDStr);

        assertNotNull(actualProducts);
        assertTrue(actualProducts.isEmpty());
    }

    @Test
    void findByCategory_ProductsByCategoryExists_ReturnProducts() {
        var products = ProductStub.getProducts(5);
        var categoryID = CategoryID.newID();
        var categoryIDStr = categoryID.getValue().toString();

        when(productRepository.findByCategory(categoryID)).thenReturn(products);

        var actualProducts = readDataProductUseCase.findByCategory(categoryIDStr);

        assertEquals(products, actualProducts);
    }

    @Test
    void findByBrand_BrandIDStrIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> readDataProductUseCase.findByBrand(null));
        assertEquals(UseCaseError.BRAND_ID_IS_REQUIRED, ex.getCode());
    }

    @Test
    void findByBrand_ProductsByBrandIsEmpty_ReturnNull() {
        List<Product> products = emptyList();
        var brandID = BrandID.newID();
        var brandIDStr = brandID.getValue().toString();

        when(productRepository.findByBrand(brandID)).thenReturn(products);

        var actualProducts = readDataProductUseCase.findByBrand(brandIDStr);

        assertNotNull(actualProducts);
        assertTrue(actualProducts.isEmpty());
    }

    @Test
    void findByBrand_ProductsByBrandExists_ReturnProducts() {
        var products = ProductStub.getProducts(5);
        var brandID = BrandID.newID();
        var brandIDStr = brandID.getValue().toString();

        when(productRepository.findByBrand(brandID)).thenReturn(products);

        var actualProducts = readDataProductUseCase.findByBrand(brandIDStr);

        assertEquals(products, actualProducts);
    }

}