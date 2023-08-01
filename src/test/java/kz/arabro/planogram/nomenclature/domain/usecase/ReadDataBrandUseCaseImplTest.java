package kz.arabro.planogram.nomenclature.domain.usecase;

import kz.arabro.planogram.nomenclature.boundary.repository.BrandRepository;
import kz.arabro.planogram.nomenclature.boundary.usecase.ReadDataBrandUseCase;
import kz.arabro.planogram.nomenclature.domain.entity.brand.Brand;
import kz.arabro.planogram.nomenclature.domain.entity.brand.BrandError;
import kz.arabro.planogram.nomenclature.domain.entity.brand.BrandID;
import kz.arabro.planogram.nomenclature.domain.exception.CodedException;
import kz.arabro.planogram.nomenclature.testdouble.entity.BrandStub;
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
class ReadDataBrandUseCaseImplTest {

    @Mock
    private BrandRepository brandRepository;

    private ReadDataBrandUseCase readDataBrandUseCase;

    @BeforeEach
    void setUp() {
        this.readDataBrandUseCase = new ReadDataBrandUseCaseImpl(brandRepository);
    }

    @Test
    void findByID_BrandIDStrIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> readDataBrandUseCase.findByID(null));
        assertEquals(BrandError.BRAND_ID_VALUE_IS_REQUIRED, ex.getCode());
    }

    @Test
    void findByID_BrandIsNotFoundByID_ThrowEx() {
        var brandID = BrandID.newID().getValue().toString();

        when(brandRepository.findByID(any(BrandID.class))).thenReturn(Optional.empty());

        var ex = assertThrows(CodedException.class, () -> readDataBrandUseCase.findByID(brandID));
        assertEquals(UseCaseError.BRAND_IS_NOT_FOUND, ex.getCode());
    }

    @Test
    void findByID_BrandExists_ReturnBrand() {
        var brand = BrandStub.getBrand();
        var brandID = BrandID.newID().getValue().toString();

        when(brandRepository.findByID(any(BrandID.class))).thenReturn(Optional.of(brand));

        var actualBrand = readDataBrandUseCase.findByID(brandID);
        assertNotNull(actualBrand);
        assertEquals(brand, actualBrand);
    }

    @Test
    void findAll_BrandRepositoryIsRuntimeEx_ThrowEx() {
        when(brandRepository.findAll()).thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class, () -> readDataBrandUseCase.findAll());
    }

    @Test
    void findAll_BrandsIsEmpty_ReturnNull() {
        List<Brand> brands = emptyList();
        when(brandRepository.findAll()).thenReturn(brands);

        var actualBrands = readDataBrandUseCase.findAll();

        assertNotNull(actualBrands);
        assertTrue(actualBrands.isEmpty());
    }

    @Test
    void findAll_BrandsExists_ReturnBrands() {
        var brands = BrandStub.getBrands(5);
        when(brandRepository.findAll()).thenReturn(brands);

        var actualBrands = readDataBrandUseCase.findAll();

        assertEquals(brands, actualBrands);
    }


}