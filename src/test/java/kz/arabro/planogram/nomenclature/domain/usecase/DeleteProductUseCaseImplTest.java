package kz.arabro.planogram.nomenclature.domain.usecase;

import kz.arabro.planogram.nomenclature.boundary.repository.ProductRepository;
import kz.arabro.planogram.nomenclature.boundary.usecase.DeleteProductUseCase;
import kz.arabro.planogram.nomenclature.domain.entity.product.ProductID;
import kz.arabro.planogram.nomenclature.domain.exception.CodedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteProductUseCaseImplTest {

    @Mock
    private ProductRepository productRepository;

    private DeleteProductUseCase deleteProductUseCase;

    @BeforeEach
    void setUp() {
        this.deleteProductUseCase = new DeleteProductUseCaseImpl(productRepository);
    }

    @Test
    void deleteBrandByID_BrandIDIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> deleteProductUseCase.deleteProductByID(null));
        assertEquals(UseCaseError.PRODUCT_ID_IS_REQUIRED, ex.getCode());
    }

    @Test
    void deleteProductByID_ValueIsValid_DeleteProduct() {
        var productID = ProductID.newID();
        var productIDStr = productID.getValue().toString();

        deleteProductUseCase.deleteProductByID(productIDStr);
        verify(productRepository, times(1)).deleteById(productID);
    }

}