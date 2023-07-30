package kz.arabro.planogram.nomenclature.domain.usecase;

import kz.arabro.planogram.nomenclature.boundary.model.ProductCreateInfo;
import kz.arabro.planogram.nomenclature.boundary.repository.BrandRepository;
import kz.arabro.planogram.nomenclature.boundary.repository.CategoryRepository;
import kz.arabro.planogram.nomenclature.boundary.repository.ProducerRepository;
import kz.arabro.planogram.nomenclature.boundary.repository.ProductRepository;
import kz.arabro.planogram.nomenclature.boundary.usecase.CreateProductUseCase;
import kz.arabro.planogram.nomenclature.domain.exception.CodedException;
import kz.arabro.planogram.nomenclature.testdouble.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateProductUseCaseImplTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private BrandRepository brandRepository;
    @Mock
    private ProducerRepository producerRepository;

    private CreateProductUseCase createProductUseCase;

    @BeforeEach
    void setUp() {
        this.createProductUseCase = new CreateProductUseCaseImpl(
                productRepository,
                categoryRepository,
                brandRepository,
                producerRepository
        );
    }

    @Test
    void execute_InfoIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> createProductUseCase.execute(null));
        assertEquals(UseCaseError.PRODUCT_CREATE_INFO_IS_REQUIRED, ex.getCode());
    }

    @Test
    void execute_InfoPriceIsNull_ReturnProductWithoutPrice() {
        var code1c = "123456789";
        var rusName = NameStub.getName().getValue();
        var kazName = NameStub.getName().getValue();

        var category = CategoryStub.getCategory();
        var categoryID = category.getId();
        var categoryIDStr = categoryID.getValue().toString();

        var brand = BrandStub.getBrand();
        var brandID = brand.getId();
        var brandIDStr = brandID.getValue().toString();

        var producer = ProducerStub.getProducer();
        var producerID = producer.getId();
        var producerIDStr = producerID.getValue().toString();

        var barcode = BarcodeStub.getBarcode().getValue();
        var height = String.valueOf(SizeStub.getSize().getHeight());
        var weight = String.valueOf(SizeStub.getSize().getWeight());
        var length = String.valueOf(SizeStub.getSize().getLength());
        var imagePath = "path";

        var info = new ProductCreateInfo();
        info.setCode1C(code1c);
        info.setRusName(rusName);
        info.setKazName(kazName);
        info.setCategory(categoryIDStr);
        info.setBrand(brandIDStr);
        info.setProducer(producerIDStr);
        info.setBarcode(barcode);
        info.setPrice(null);
        info.setHeight(height);
        info.setWeight(weight);
        info.setLength(length);
        info.setImagePath(imagePath);

        when(categoryRepository.findByID(categoryID)).thenReturn(Optional.of(category));
        when(brandRepository.findByID(brandID)).thenReturn(Optional.of(brand));
        when(producerRepository.findByID(producerID)).thenReturn(Optional.of(producer));

        var product = createProductUseCase.execute(info);

        assertNotNull(product);
        assertEquals(code1c, product.getCode1C());
        assertEquals(rusName, product.getRusName().getValue());
        assertEquals(kazName, product.getKazName().getValue());
        assertEquals(category, product.getCategory());
        assertEquals(brand, product.getBrand());
        assertEquals(producer, product.getProducer());
        assertEquals(barcode, product.getBarcode().getValue());
        assertNull(product.getPrice());
        assertEquals(height, String.valueOf(product.getSize().getHeight()));
        assertEquals(weight, String.valueOf(product.getSize().getWeight()));
        assertEquals(length, String.valueOf(product.getSize().getLength()));
        assertEquals(imagePath, product.getImagePath());

        verify(categoryRepository, times(1)).findByID(categoryID);
        verify(brandRepository, times(1)).findByID(brandID);
        verify(producerRepository, times(1)).findByID(producerID);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void execute_InfoImagePathIsNull_ReturnProductWithoutImagePath() {
        var code1c = "123456789";
        var rusName = NameStub.getName().getValue();
        var kazName = NameStub.getName().getValue();

        var category = CategoryStub.getCategory();
        var categoryID = category.getId();
        var categoryIDStr = categoryID.getValue().toString();

        var brand = BrandStub.getBrand();
        var brandID = brand.getId();
        var brandIDStr = brandID.getValue().toString();

        var producer = ProducerStub.getProducer();
        var producerID = producer.getId();
        var producerIDStr = producerID.getValue().toString();

        var barcode = BarcodeStub.getBarcode().getValue();
        var price = PriceStub.getPrice().getPrice();
        var height = String.valueOf(SizeStub.getSize().getHeight());
        var weight = String.valueOf(SizeStub.getSize().getWeight());
        var length = String.valueOf(SizeStub.getSize().getLength());

        var info = new ProductCreateInfo();
        info.setCode1C(code1c);
        info.setRusName(rusName);
        info.setKazName(kazName);
        info.setCategory(categoryIDStr);
        info.setBrand(brandIDStr);
        info.setProducer(producerIDStr);
        info.setBarcode(barcode);
        info.setPrice(price);
        info.setHeight(height);
        info.setWeight(weight);
        info.setLength(length);
        info.setImagePath(null);

        when(categoryRepository.findByID(categoryID)).thenReturn(Optional.of(category));
        when(brandRepository.findByID(brandID)).thenReturn(Optional.of(brand));
        when(producerRepository.findByID(producerID)).thenReturn(Optional.of(producer));

        var product = createProductUseCase.execute(info);

        assertNotNull(product);
        assertEquals(code1c, product.getCode1C());
        assertEquals(rusName, product.getRusName().getValue());
        assertEquals(kazName, product.getKazName().getValue());
        assertEquals(category, product.getCategory());
        assertEquals(brand, product.getBrand());
        assertEquals(producer, product.getProducer());
        assertEquals(barcode, product.getBarcode().getValue());
        assertEquals(price, product.getPrice().getPrice());
        assertEquals(height, String.valueOf(product.getSize().getHeight()));
        assertEquals(weight, String.valueOf(product.getSize().getWeight()));
        assertEquals(length, String.valueOf(product.getSize().getLength()));
        assertNull(product.getImagePath());

        verify(categoryRepository, times(1)).findByID(categoryID);
        verify(brandRepository, times(1)).findByID(brandID);
        verify(producerRepository, times(1)).findByID(producerID);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void execute_InfoPriceAndImagePathIsNull_ReturnProductWithoutPriceAndImagePath() {
        var code1c = "123456789";
        var rusName = NameStub.getName().getValue();
        var kazName = NameStub.getName().getValue();

        var category = CategoryStub.getCategory();
        var categoryID = category.getId();
        var categoryIDStr = categoryID.getValue().toString();

        var brand = BrandStub.getBrand();
        var brandID = brand.getId();
        var brandIDStr = brandID.getValue().toString();

        var producer = ProducerStub.getProducer();
        var producerID = producer.getId();
        var producerIDStr = producerID.getValue().toString();

        var barcode = BarcodeStub.getBarcode().getValue();
        var height = String.valueOf(SizeStub.getSize().getHeight());
        var weight = String.valueOf(SizeStub.getSize().getWeight());
        var length = String.valueOf(SizeStub.getSize().getLength());

        var info = new ProductCreateInfo();
        info.setCode1C(code1c);
        info.setRusName(rusName);
        info.setKazName(kazName);
        info.setCategory(categoryIDStr);
        info.setBrand(brandIDStr);
        info.setProducer(producerIDStr);
        info.setBarcode(barcode);
        info.setPrice(null);
        info.setHeight(height);
        info.setWeight(weight);
        info.setLength(length);
        info.setImagePath(null);

        when(categoryRepository.findByID(categoryID)).thenReturn(Optional.of(category));
        when(brandRepository.findByID(brandID)).thenReturn(Optional.of(brand));
        when(producerRepository.findByID(producerID)).thenReturn(Optional.of(producer));

        var product = createProductUseCase.execute(info);

        assertNotNull(product);
        assertEquals(code1c, product.getCode1C());
        assertEquals(rusName, product.getRusName().getValue());
        assertEquals(kazName, product.getKazName().getValue());
        assertEquals(category, product.getCategory());
        assertEquals(brand, product.getBrand());
        assertEquals(producer, product.getProducer());
        assertEquals(barcode, product.getBarcode().getValue());
        assertNull(product.getPrice());
        assertEquals(height, String.valueOf(product.getSize().getHeight()));
        assertEquals(weight, String.valueOf(product.getSize().getWeight()));
        assertEquals(length, String.valueOf(product.getSize().getLength()));
        assertNull(product.getImagePath());

        verify(categoryRepository, times(1)).findByID(categoryID);
        verify(brandRepository, times(1)).findByID(brandID);
        verify(producerRepository, times(1)).findByID(producerID);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void execute_AllParamsIsValid_ReturnFullProduct() {
        var code1c = "123456789";
        var rusName = NameStub.getName().getValue();
        var kazName = NameStub.getName().getValue();

        var category = CategoryStub.getCategory();
        var categoryID = category.getId();
        var categoryIDStr = categoryID.getValue().toString();

        var brand = BrandStub.getBrand();
        var brandID = brand.getId();
        var brandIDStr = brandID.getValue().toString();

        var producer = ProducerStub.getProducer();
        var producerID = producer.getId();
        var producerIDStr = producerID.getValue().toString();

        var barcode = BarcodeStub.getBarcode().getValue();
        var price = PriceStub.getPrice().getPrice();
        var height = String.valueOf(SizeStub.getSize().getHeight());
        var weight = String.valueOf(SizeStub.getSize().getWeight());
        var length = String.valueOf(SizeStub.getSize().getLength());
        var imagePath = "path";

        var info = new ProductCreateInfo();
        info.setCode1C(code1c);
        info.setRusName(rusName);
        info.setKazName(kazName);
        info.setCategory(categoryIDStr);
        info.setBrand(brandIDStr);
        info.setProducer(producerIDStr);
        info.setBarcode(barcode);
        info.setPrice(price);
        info.setHeight(height);
        info.setWeight(weight);
        info.setLength(length);
        info.setImagePath(imagePath);

        when(categoryRepository.findByID(categoryID)).thenReturn(Optional.of(category));
        when(brandRepository.findByID(brandID)).thenReturn(Optional.of(brand));
        when(producerRepository.findByID(producerID)).thenReturn(Optional.of(producer));

        var product = createProductUseCase.execute(info);

        assertNotNull(product);
        assertEquals(code1c, product.getCode1C());
        assertEquals(rusName, product.getRusName().getValue());
        assertEquals(kazName, product.getKazName().getValue());
        assertEquals(category, product.getCategory());
        assertEquals(brand, product.getBrand());
        assertEquals(producer, product.getProducer());
        assertEquals(barcode, product.getBarcode().getValue());
        assertEquals(price, product.getPrice().getPrice());
        assertEquals(height, String.valueOf(product.getSize().getHeight()));
        assertEquals(weight, String.valueOf(product.getSize().getWeight()));
        assertEquals(length, String.valueOf(product.getSize().getLength()));
        assertEquals(imagePath, product.getImagePath());

        verify(categoryRepository, times(1)).findByID(categoryID);
        verify(brandRepository, times(1)).findByID(brandID);
        verify(producerRepository, times(1)).findByID(producerID);
        verify(productRepository, times(1)).save(product);
    }
}
