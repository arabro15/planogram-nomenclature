package kz.arabro.planogram.nomenclature.adapter.repository;

import kz.arabro.planogram.nomenclature.adapter.repository.converter.ProductConverter;
import kz.arabro.planogram.nomenclature.adapter.repository.jpa.BrandDao;
import kz.arabro.planogram.nomenclature.adapter.repository.jpa.CategoryDao;
import kz.arabro.planogram.nomenclature.adapter.repository.jpa.ProducerDao;
import kz.arabro.planogram.nomenclature.adapter.repository.jpa.ProductDao;
import kz.arabro.planogram.nomenclature.adapter.repository.model.ProductDbModel;
import kz.arabro.planogram.nomenclature.boundary.repository.ProductRepository;
import kz.arabro.planogram.nomenclature.domain.entity.brand.BrandID;
import kz.arabro.planogram.nomenclature.domain.entity.category.CategoryID;
import kz.arabro.planogram.nomenclature.domain.entity.producer.ProducerID;
import kz.arabro.planogram.nomenclature.domain.entity.product.Product;
import kz.arabro.planogram.nomenclature.domain.entity.product.ProductID;
import kz.arabro.planogram.nomenclature.domain.exception.CodedException;
import kz.arabro.planogram.nomenclature.testdouble.entity.ProductStub;
import kz.arabro.planogram.nomenclature.testdouble.repository.BrandDbModelStub;
import kz.arabro.planogram.nomenclature.testdouble.repository.CategoryDbModelStub;
import kz.arabro.planogram.nomenclature.testdouble.repository.ProducerDbModelStub;
import kz.arabro.planogram.nomenclature.testdouble.repository.ProductDbModelStub;
import kz.arabro.planogram.nomenclature.util.generator.StringGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryImplTest {

    @Mock
    private ProductDao productDao;

    @Mock
    private ProducerDao producerDao;

    @Mock
    private CategoryDao categoryDao;

    @Mock
    private BrandDao brandDao;

    @Captor
    private ArgumentCaptor<ProductDbModel> productDbModelCaptor;

    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        this.productRepository = new ProductRepositoryImpl(
                productDao,
                producerDao,
                categoryDao,
                brandDao
        );
    }

    @Test
    void save_ProductIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> productRepository.save(null));
        assertEquals(RepositoryError.PRODUCT_IS_REQUIRED, ex.getCode());
    }

    @Test
    void save_ValueIsValid_SaveProduct() {
        var product = ProductStub.getProduct();

        productRepository.save(product);
        verify(productDao).save(productDbModelCaptor.capture());

        var productDbModel = productDbModelCaptor.getValue();
        assertNotNull(productDbModel);
        assertEqualsProductAndProductDbModel(product, productDbModel);
    }

    @Test
    void deleteById_ProductIDIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> productRepository.deleteById(null));
        assertEquals(RepositoryError.PRODUCT_ID_IS_REQUIRED, ex.getCode());
    }

    @Test
    void deleteByID_ValueIsValid_DeleteProduct() {
        var productID = ProductStub.getProduct().getProductID();
        var productIDValue = productID.getValue();

        productRepository.deleteById(productID);
        verify(productDao, times(1)).deleteById(productIDValue);
    }

    @Test
    void update_ProductIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> productRepository.update(null));
        assertEquals(RepositoryError.PRODUCT_IS_REQUIRED, ex.getCode());
    }

    @Test
    void update_ValueIsValid_ProductUpdated() {
        var product = ProductStub.getProduct();
        var expectedDbModel = ProductConverter.toModel(product);
        ArgumentCaptor<ProductDbModel> captor = ArgumentCaptor.forClass(ProductDbModel.class);

        productRepository.update(product);
        verify(productDao, times(1)).updateById(captor.capture());
        var actualDbModel = captor.getValue();
        assertNotNull(actualDbModel);
        assertEquals(expectedDbModel.getId(), actualDbModel.getId());
    }

    @Test
    void findByID_ProductIDIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> productRepository.findByID(null));
        assertEquals(RepositoryError.PRODUCT_ID_IS_REQUIRED, ex.getCode());
    }

    @Test
    void findByID_ValueIsValid_ReturnProduct() {
        var productDbModel = ProductDbModelStub.getProductDbModel();
        when(productDao.findById(any(UUID.class))).thenReturn(Optional.of(productDbModel));

        var productOpt = productRepository.findByID(ProductID.newID());
        assertNotNull(productOpt);
        assertTrue(productOpt.isPresent());

        var product = productOpt.get();

        assertEqualsProductAndProductDbModel(product, productDbModel);
    }

    @Test
    void findByCode1c_Code1cIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> productRepository.findByCode1C(null));
        assertEquals(RepositoryError.PRODUCT_CODE_1C_IS_REQUIRED, ex.getCode());
    }

    @Test
    void findByCode1c_ValueIsValid_ReturnProduct() {
        var productDbModel = ProductDbModelStub.getProductDbModel();
        when(productDao.findByCode1C(any(String.class))).thenReturn(Optional.of(productDbModel));

        var productOpt = productRepository.findByCode1C(StringGenerator.getRandomString());
        assertNotNull(productOpt);
        assertTrue(productOpt.isPresent());

        var product = productOpt.get();

        assertEqualsProductAndProductDbModel(product, productDbModel);
    }

    @Test
    void findAll_NotParams_ReturnProducts() {
        var count = 5;
        var productDbModels = ProductDbModelStub.getProductDbModels(count);
        when(productDao.findAll()).thenReturn(productDbModels);

        var products = productRepository.findAll();
        assertNotNull(products);
        assertEquals(productDbModels.size(), products.size());
        for (int i = 0; i < count; i++) {
            var product = products.get(i);
            var productDbModel = productDbModels.get(i);
            assertEqualsProductAndProductDbModel(product, productDbModel);
        }
    }

    @Test
    void findByProducer_ProducerIDIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> productRepository.findByProducer(null));
        assertEquals(RepositoryError.PRODUCER_ID_IS_REQUIRED, ex.getCode());
    }

    @Test
    void findByProducer_ProducerDbModelIsEmpty_ThrowEx() {
        when(producerDao.findById(any(UUID.class))).thenReturn(Optional.empty());

        var producerID = ProducerID.newID();

        var ex = assertThrows(CodedException.class, () -> productRepository.findByProducer(producerID));
        assertEquals(RepositoryError.PRODUCER_DB_MODEL_IS_REQUIRED, ex.getCode());
    }

    @Test
    void findByProducer_AllValueIsValid_ReturnProductByProducer() {
        var count = 5;

        var producerDbModel = ProducerDbModelStub.getProducerDbModel();
        var producerID = ProducerID.from(producerDbModel.getId().toString());

        var productDbModels = ProductDbModelStub.getProductDbModels(count);

        when(producerDao.findById(any(UUID.class))).thenReturn(Optional.of(producerDbModel));
        when(productDao.findByProducer(producerDbModel)).thenReturn(productDbModels);

        var products = productRepository.findByProducer(producerID);

        assertNotNull(products);

        for (int i = 0; i < count; i++) {
            var product = products.get(i);
            var productDbModel = productDbModels.get(i);
            assertEqualsProductAndProductDbModel(product, productDbModel);
        }
    }

    @Test
    void findByCategory_CategoryIDIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> productRepository.findByCategory(null));
        assertEquals(RepositoryError.CATEGORY_ID_IS_REQUIRED, ex.getCode());
    }

    @Test
    void findByCategory_CategoryDbModelIsEmpty_ThrowEx() {
        when(categoryDao.findById(any(UUID.class))).thenReturn(Optional.empty());

        var categoryID = CategoryID.newID();

        var ex = assertThrows(CodedException.class, () -> productRepository.findByCategory(categoryID));
        assertEquals(RepositoryError.CATEGORY_DB_MODEL_IS_REQUIRED, ex.getCode());
    }

    @Test
    void findByCategory_AllValueIsValid_ReturnProductByCategory() {
        var count = 5;

        var categoryDbModel = CategoryDbModelStub.getCategoryDbModel();
        var categoryID = CategoryID.from(categoryDbModel.getId().toString());

        var productDbModels = ProductDbModelStub.getProductDbModels(count);

        when(categoryDao.findById(any(UUID.class))).thenReturn(Optional.of(categoryDbModel));
        when(productDao.findByCategory(categoryDbModel)).thenReturn(productDbModels);

        var products = productRepository.findByCategory(categoryID);

        assertNotNull(products);

        for (int i = 0; i < count; i++) {
            var product = products.get(i);
            var productDbModel = productDbModels.get(i);
            assertEqualsProductAndProductDbModel(product, productDbModel);
        }
    }

    @Test
    void findByBrand_BrandIDIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> productRepository.findByBrand(null));
        assertEquals(RepositoryError.BRAND_ID_IS_REQUIRED, ex.getCode());
    }

    @Test
    void findByBrand_BrandDbModelIsEmpty_ThrowEx() {
        when(brandDao.findById(any(UUID.class))).thenReturn(Optional.empty());

        var brandID = BrandID.newID();

        var ex = assertThrows(CodedException.class, () -> productRepository.findByBrand(brandID));
        assertEquals(RepositoryError.BRAND_DB_MODEL_IS_REQUIRED, ex.getCode());
    }

    @Test
    void findByBrand_AllValueIsValid_ReturnProductByBrand() {
        var count = 5;

        var brandDbModel = BrandDbModelStub.getBrandDbModel();
        var brandID = BrandID.from(brandDbModel.getId().toString());

        var productDbModels = ProductDbModelStub.getProductDbModels(count);

        when(brandDao.findById(any(UUID.class))).thenReturn(Optional.of(brandDbModel));
        when(productDao.findByBrand(brandDbModel)).thenReturn(productDbModels);

        var products = productRepository.findByBrand(brandID);

        assertNotNull(products);

        for (int i = 0; i < count; i++) {
            var product = products.get(i);
            var productDbModel = productDbModels.get(i);
            assertEqualsProductAndProductDbModel(product, productDbModel);
        }
    }


    private void assertEqualsProductAndProductDbModel(Product product, ProductDbModel productDbModel) {
        var productID = product.getProductID().getValue();
        var productCode1c = product.getCode1C();
        var productRusName = product.getRusName().getValue();
        var productKazName = product.getKazName().getValue();

        var category = product.getCategory();
        var categoryID = category.getId().getValue();
        var categoryName = category.getName().getValue();
        var categoryColor = category.getColor().name();
        var categoryParentIDOpt = category.getParentID();

        var brand = product.getBrand();
        var brandID = brand.getId().getValue();
        var brandName = brand.getName().getValue();

        var producer = product.getProducer();
        var producerID = producer.getId().getValue();
        var producerName = producer.getName().getValue();

        var productBarcode = product.getBarcode().getValue();
        var productPrice = product.getPrice().getValue();

        var productSize = product.getSize();
        var productHeight = String.valueOf(productSize.getHeight());
        var productWeight = String.valueOf(productSize.getWeight());
        var productLength = String.valueOf(productSize.getLength());

        var productImagePath = product.getImagePath();

        var productDbModelID = productDbModel.getId();
        var productDbModelCode1c = productDbModel.getCode1C();
        var productDbModelRusName = productDbModel.getRusName();
        var productDbModelKazName = productDbModel.getKazName();

        var categoryDbModel = productDbModel.getCategory();
        var categoryDbModelID = categoryDbModel.getId();
        var categoryDbModelName = categoryDbModel.getName();
        var categoryDbModelColor = categoryDbModel.getColor();
        var categoryDbModelParentID = categoryDbModel.getParentID();

        var brandDbModel = productDbModel.getBrand();
        var brandDbModelID = brandDbModel.getId();
        var brandDbModelName = brandDbModel.getName();

        var producerDbModel = productDbModel.getProducer();
        var producerDbModelID = producerDbModel.getId();
        var producerDbModelName = producerDbModel.getName();

        var productDbModelBarcode = productDbModel.getBarcode();
        var productDbModelPrice = productDbModel.getPrice();
        var productDbModelHeight = productDbModel.getHeight();
        var productDbModelWeight = productDbModel.getWeight();
        var productDbModelLength = productDbModel.getLength();
        var productDbModelImagePath = productDbModel.getImagePath();

        assertTrue(categoryParentIDOpt.isPresent());
        assertAll(
                () -> assertEquals(productDbModelID, productID),
                () -> assertEquals(productDbModelCode1c, productCode1c),
                () -> assertEquals(productDbModelRusName, productRusName),
                () -> assertEquals(productDbModelKazName, productKazName),
                () -> assertEquals(categoryDbModelID, categoryID),
                () -> assertEquals(categoryDbModelName, categoryName),
                () -> assertEquals(categoryDbModelColor, categoryColor),
                () -> assertEquals(categoryDbModelParentID, categoryParentIDOpt.get().getValue()),
                () -> assertEquals(brandDbModelID, brandID),
                () -> assertEquals(brandDbModelName, brandName),
                () -> assertEquals(producerDbModelID, producerID),
                () -> assertEquals(producerDbModelName, producerName),
                () -> assertEquals(productDbModelBarcode, productBarcode),
                () -> assertEquals(productDbModelPrice, productPrice),
                () -> assertEquals(productDbModelHeight, productHeight),
                () -> assertEquals(productDbModelWeight, productWeight),
                () -> assertEquals(productDbModelLength, productLength),
                () -> assertEquals(productDbModelImagePath, productImagePath)
        );
    }


}