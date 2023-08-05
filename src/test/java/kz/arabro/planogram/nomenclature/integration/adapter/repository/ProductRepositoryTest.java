package kz.arabro.planogram.nomenclature.integration.adapter.repository;

import kz.arabro.planogram.nomenclature.adapter.repository.jpa.BrandDao;
import kz.arabro.planogram.nomenclature.adapter.repository.jpa.CategoryDao;
import kz.arabro.planogram.nomenclature.adapter.repository.jpa.ProducerDao;
import kz.arabro.planogram.nomenclature.adapter.repository.jpa.ProductDao;
import kz.arabro.planogram.nomenclature.boundary.repository.BrandRepository;
import kz.arabro.planogram.nomenclature.boundary.repository.CategoryRepository;
import kz.arabro.planogram.nomenclature.boundary.repository.ProducerRepository;
import kz.arabro.planogram.nomenclature.boundary.repository.ProductRepository;
import kz.arabro.planogram.nomenclature.domain.entity.brand.Brand;
import kz.arabro.planogram.nomenclature.domain.entity.category.Category;
import kz.arabro.planogram.nomenclature.domain.entity.producer.Producer;
import kz.arabro.planogram.nomenclature.domain.entity.product.Product;
import kz.arabro.planogram.nomenclature.domain.entity.product.ProductID;
import kz.arabro.planogram.nomenclature.testdouble.entity.BrandStub;
import kz.arabro.planogram.nomenclature.testdouble.entity.CategoryStub;
import kz.arabro.planogram.nomenclature.testdouble.entity.ProducerStub;
import kz.arabro.planogram.nomenclature.testdouble.entity.ProductStub;
import kz.arabro.planogram.nomenclature.util.annotation.IntegrationTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@IntegrationTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProducerRepository producerRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProducerDao producerDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private BrandDao brandDao;

    private static final Product PRODUCT = ProductStub.getProduct();
    private static final Producer PRODUCER = PRODUCT.getProducer();
    private static final Category CATEGORY = PRODUCT.getCategory();
    private static final Brand BRAND = PRODUCT.getBrand();

    @BeforeEach
    void setUp() {
        productRepository.save(PRODUCT);
        producerRepository.save(PRODUCER);
        categoryRepository.save(CATEGORY);
        brandRepository.save(BRAND);
    }

    @AfterEach
    void tearDown() {
        brandDao.deleteAll();
        categoryDao.deleteAll();
        producerDao.deleteAll();
        productDao.deleteAll();
    }

    @Test
    void update_ProductUpdated() {
        var productToUpdate = ProductStub.getProduct();
        productRepository.save(productToUpdate);
        producerRepository.save(productToUpdate.getProducer());
        categoryRepository.save(productToUpdate.getCategory());
        brandRepository.save(productToUpdate.getBrand());

        var productFromDBOpt = productRepository.findByID(productToUpdate.getProductID());
        assertTrue(productFromDBOpt.isPresent());
        assertNotEquals(productToUpdate, productFromDBOpt.get());

        productRepository.update(productToUpdate);

        productFromDBOpt = productRepository.findByID(productToUpdate.getProductID());
        assertTrue(productFromDBOpt.isPresent());
        assertEquals(productToUpdate.getProductID().getValue(), productFromDBOpt.get().getProductID().getValue());
    }

    @Test
    void findByID_ProductNotExist_ReturnEmptyOptional() {
        var productOpt = productRepository.findByID(ProductID.newID());
        assertTrue(productOpt.isEmpty());
    }

    @Test
    void findByID_ProductExist_ReturnProduct() {
        var productOpt = productRepository.findByID(PRODUCT.getProductID());
        assertTrue(productOpt.isPresent());

        var productFromDB = productOpt.get();
        assertEquals(PRODUCT.getProductID().getValue(), productFromDB.getProductID().getValue());
    }

    @Test
    void findByCode1c_ProductByCode1cNotExist_ReturnEmptyOptional() {
        var productOpt = productRepository.findByCode1C("123456");
        assertTrue(productOpt.isEmpty());
    }

    @Test
    void findByCode1c_ProductByCode1cExist_ReturnProduct() {
        var productByCode1c = ProductStub.getProductByCode1c();
        productRepository.save(productByCode1c);

        var productOpt = productRepository.findByCode1C(productByCode1c.getCode1C());
        assertTrue(productOpt.isPresent());

        var productFromDB = productOpt.get();
        assertEquals(productByCode1c.getCode1C(), productFromDB.getCode1C());
    }

    @Test
    void findAll_ReturnAllProducts() {
        var count = 10;
        var productsToSave = ProductStub.getProducts(count);
        productsToSave.forEach(productRepository::save);

        var productsFromDB = productRepository.findAll();
        assertNotNull(productsFromDB);
        assertFalse(productsFromDB.isEmpty());

        productsFromDB.forEach(Assertions::assertNotNull);
    }

    @Test
    void findByProducer_ProductByProducerNotExist_ReturnEmptyOptional() {
        var producer = ProducerStub.getProducer();
        var producerID = producer.getId();

        producerRepository.save(producer);

        var products = productRepository.findByProducer(producerID);
        assertTrue(products.isEmpty());
    }

    @Test
    void findByProducer_ProductByProducerExist_ReturnProductByProducer() {
        var producerForProduct = ProducerStub.getProducerForProduct();

        var productsByProducer = ProductStub.getProductsByProducer(10);
        productsByProducer.forEach(productRepository::save);

        var productsByProducerFromDb = productRepository.findByProducer(producerForProduct.getId());

        productsByProducerFromDb.forEach(product -> {
            assertNotNull(product);

            var producer = product.getProducer();

            var producerIDValue = producer.getId().getValue();
            var producerNameValue = producer.getName().getValue();

            var producerForProductIdValue = producerForProduct.getId().getValue();
            var producerForProductNameValue = producerForProduct.getName().getValue();

            assertEquals(producerIDValue, producerForProductIdValue);
            assertEquals(producerNameValue, producerForProductNameValue);
        });
    }

    @Test
    void findByCategory_ProductByCategoryNotExist_ReturnEmptyOptional() {
        var category = CategoryStub.getCategory();
        var categoryID = category.getId();

        categoryRepository.save(category);

        var products = productRepository.findByCategory(categoryID);
        assertTrue(products.isEmpty());
    }

    @Test
    void findByCategory_ProductByCategoryExist_ReturnProductByCategory() {
        var categoryForProduct = CategoryStub.getCategoryForProduct();

        var productsByCategory= ProductStub.getProductsByCategory(10);
        productsByCategory.forEach(productRepository::save);

        var productsByCategoryFromDb = productRepository.findByCategory(categoryForProduct.getId());

        productsByCategoryFromDb.forEach(product -> {
            assertNotNull(product);

            var category = product.getCategory();

            var categoryIDValue = category.getId().getValue();
            var categoryNameValue = category.getName().getValue();
            var categoryColorValue = category.getColor().name();
            var categoryParentIDOpt = category.getParentID();
            assertTrue(categoryParentIDOpt.isPresent());
            var categoryParentIDValue = categoryParentIDOpt.get().getValue();

            var categoryForProductIdValue = categoryForProduct.getId().getValue();
            var categoryForProductNameValue = categoryForProduct.getName().getValue();
            var categoryForProductColorValue = categoryForProduct.getColor().name();
            var categoryForProductParentIDOpt = categoryForProduct.getParentID();
            assertTrue(categoryForProductParentIDOpt.isPresent());
            var categoryForProductParentIDValue = categoryForProductParentIDOpt.get().getValue();

            assertEquals(categoryIDValue, categoryForProductIdValue);
            assertEquals(categoryNameValue, categoryForProductNameValue);
            assertEquals(categoryColorValue, categoryForProductColorValue);
            assertEquals(categoryParentIDValue, categoryForProductParentIDValue);
        });
    }

    @Test
    void findByBrand_ProductByBrandNotExist_ReturnEmptyOptional() {
        var producer = ProducerStub.getProducer();
        var producerID = producer.getId();

        producerRepository.save(producer);

        var products = productRepository.findByProducer(producerID);
        assertTrue(products.isEmpty());
    }

    @Test
    void findByBrand_ProductByBrandExist_ReturnProductByBrand() {
        var brandForProduct = BrandStub.getBrandForProduct();

        var productsByBrand = ProductStub.getProductsByBrand(10);
        productsByBrand.forEach(productRepository::save);

        var productsByBrandFromDb = productRepository.findByBrand(brandForProduct.getId());

        productsByBrandFromDb.forEach(product -> {
            assertNotNull(product);

            var brand = product.getBrand();

            var brandIDValue = brand.getId().getValue();
            var brandNameValue = brand.getName().getValue();

            var brandForProductIdValue = brandForProduct.getId().getValue();
            var brandForProductNameValue = brandForProduct.getName().getValue();

            assertEquals(brandIDValue, brandForProductIdValue);
            assertEquals(brandNameValue, brandForProductNameValue);
        });
    }
}