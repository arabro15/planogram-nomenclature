package kz.arabro.planogram.nomenclature.domain.usecase;

import kz.arabro.planogram.nomenclature.boundary.repository.BrandRepository;
import kz.arabro.planogram.nomenclature.boundary.usecase.CreateBrandUseCase;
import kz.arabro.planogram.nomenclature.boundary.usecase.DeleteBrandUseCase;
import kz.arabro.planogram.nomenclature.domain.exception.CodedException;
import kz.arabro.planogram.nomenclature.testdouble.entity.BrandStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteBrandUseCaseImplTest {

    @Mock
    private BrandRepository brandRepository;

    private DeleteBrandUseCase deleteBrandUseCase;

    @BeforeEach
    void setUp() {
        this.deleteBrandUseCase = new DeleteBrandUseCaseImpl(brandRepository);
    }

    @Test
    void deleteBrandByID_brandIDIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> deleteBrandUseCase.deleteBrandByID(null));
        assertEquals(UseCaseError.BRAND_ID_IS_REQUIRED, ex.getCode());
    }

    @Test
    void deleteBrandByID_ValueIsValid_DeleteBrand() {
        var brand = BrandStub.getBrand();
        var brandID = brand.getId();
        var brandIDStr = brandID.getValue().toString();

        when(brandRepository.findByID(brandID)).thenReturn(Optional.of(brand));

        var brandOpt = brandRepository.findByID(brandID);
        assertTrue(brandOpt.isPresent());

        deleteBrandUseCase.deleteBrandByID(brandIDStr);
        verify(brandRepository, times(1)).deleteById(brandID);
    }
}
