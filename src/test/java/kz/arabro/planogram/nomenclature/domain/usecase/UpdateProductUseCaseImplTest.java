package kz.arabro.planogram.nomenclature.domain.usecase;

import kz.arabro.planogram.nomenclature.boundary.model.ProductEditInfo;
import kz.arabro.planogram.nomenclature.boundary.repository.BrandRepository;
import kz.arabro.planogram.nomenclature.boundary.repository.CategoryRepository;
import kz.arabro.planogram.nomenclature.boundary.repository.ProducerRepository;
import kz.arabro.planogram.nomenclature.boundary.repository.ProductRepository;
import kz.arabro.planogram.nomenclature.boundary.usecase.UpdateProductUseCase;
import kz.arabro.planogram.nomenclature.domain.entity.brand.BrandID;
import kz.arabro.planogram.nomenclature.domain.entity.category.CategoryID;
import kz.arabro.planogram.nomenclature.domain.entity.producer.ProducerID;
import kz.arabro.planogram.nomenclature.domain.entity.product.Product;
import kz.arabro.planogram.nomenclature.domain.exception.CodedException;
import kz.arabro.planogram.nomenclature.testdouble.entity.ProductStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateProductUseCaseImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private BrandRepository brandRepository;

    @Mock
    private ProducerRepository producerRepository;

    private UpdateProductUseCase updateProductUseCase;

    @BeforeEach
    void setUp() {
        this.updateProductUseCase = new UpdateProductUseCaseImpl(
                productRepository,
                categoryRepository,
                brandRepository,
                producerRepository
        );
    }

    @Test
    void update_ProductEditInfoIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> updateProductUseCase.update(null));
        assertEquals(UseCaseError.PRODUCT_EDIT_INFO_IS_REQUIRED, ex.getCode());
    }

    @Test
    void update_ValueIsValid_UpdateBrand() {
        var product = ProductStub.getProduct();

        var productIDStr = product.getProductID().getValue().toString();
        var code1C = product.getCode1C();
        var rusName = product.getRusName().getValue();
        var kazName = product.getKazName().getValue();

        var category = product.getCategory();
        var categoryID = category.getId();
        var categoryIDStr = categoryID.getValue().toString();

        var brand = product.getBrand();
        var brandID = brand.getId();
        var brandIDStr = brandID.getValue().toString();

        var producer = product.getProducer();
        var producerID = producer.getId();
        var producerIDStr = producerID.getValue().toString();

        var barcode = product.getBarcode().getValue();
        var price = product.getPrice().getValue();
        var height = String.valueOf(product.getSize().getHeight());
        var weight = String.valueOf(product.getSize().getWeight());
        var length = String.valueOf(product.getSize().getLength());
        var imagePath = product.getImagePath();

        var productEditInfo = new ProductEditInfo();
        productEditInfo.setId(productIDStr);
        productEditInfo.setCode1C(code1C);
        productEditInfo.setRusName(rusName);
        productEditInfo.setKazName(kazName);
        productEditInfo.setCategory(categoryIDStr);
        productEditInfo.setBrand(brandIDStr);
        productEditInfo.setProducer(producerIDStr);
        productEditInfo.setBarcode(barcode);
        productEditInfo.setPrice(price);
        productEditInfo.setHeight(height);
        productEditInfo.setWeight(weight);
        productEditInfo.setLength(length);
        productEditInfo.setImagePath(imagePath);

        when(categoryRepository.findByID(categoryID)).thenReturn(Optional.of(category));
        when(brandRepository.findByID(brandID)).thenReturn(Optional.of(brand));
        when(producerRepository.findByID(producerID)).thenReturn(Optional.of(producer));

        updateProductUseCase.update(productEditInfo);

        verify(categoryRepository, times(1)).findByID(any(CategoryID.class));
        verify(brandRepository, times(1)).findByID(any(BrandID.class));
        verify(producerRepository, times(1)).findByID(any(ProducerID.class));
        verify(productRepository, times(1)).update(any(Product.class));
    }


}