package kz.arabro.planogram.nomenclature.domain.usecase;

import kz.arabro.planogram.nomenclature.boundary.repository.BrandRepository;
import kz.arabro.planogram.nomenclature.boundary.usecase.DeleteBrandUseCase;
import kz.arabro.planogram.nomenclature.domain.exception.CodedException;
import kz.arabro.planogram.nomenclature.testdouble.entity.BrandStub;
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
class DeleteBrandUseCaseImplTest {

    @Mock
    private BrandRepository brandRepository;

    private DeleteBrandUseCase deleteBrandUseCase;

    @BeforeEach
    void setUp() {
        this.deleteBrandUseCase = new DeleteBrandUseCaseImpl(brandRepository);
    }

    @Test
    void deleteBrandByID_BrandIDIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> deleteBrandUseCase.deleteBrandByID(null));
        assertEquals(UseCaseError.BRAND_ID_IS_REQUIRED, ex.getCode());
    }

    @Test
    void deleteBrandByID_ValueIsValid_DeleteBrand() {
        var brandID = BrandStub.getBrand().getId();
        var brandIDStr = brandID.getValue().toString();

        deleteBrandUseCase.deleteBrandByID(brandIDStr);
        verify(brandRepository, times(1)).deleteById(brandID);
    }
}
