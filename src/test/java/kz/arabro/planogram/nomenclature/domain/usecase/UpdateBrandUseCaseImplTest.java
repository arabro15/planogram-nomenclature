package kz.arabro.planogram.nomenclature.domain.usecase;

import kz.arabro.planogram.nomenclature.boundary.model.BrandEditInfo;
import kz.arabro.planogram.nomenclature.boundary.repository.BrandRepository;
import kz.arabro.planogram.nomenclature.boundary.usecase.UpdateBrandUseCase;
import kz.arabro.planogram.nomenclature.domain.entity.brand.Brand;
import kz.arabro.planogram.nomenclature.domain.exception.CodedException;
import kz.arabro.planogram.nomenclature.testdouble.entity.BrandStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateBrandUseCaseImplTest {

    @Mock
    private BrandRepository brandRepository;

    private UpdateBrandUseCase updateBrandUseCase;

    @BeforeEach
    void setUp() {
        this.updateBrandUseCase = new UpdateBrandUseCaseImpl(brandRepository);
    }

    @Test
    void update_BrandEditInfoIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> updateBrandUseCase.update(null));
        assertEquals(UseCaseError.BRAND_EDIT_INFO_IS_REQUIRED, ex.getCode());
    }

    @Test
    void update_ValueIsValid_UpdateBrand() {
        var brand = BrandStub.getBrand();

        var brandIDStr = brand.getId().getValue().toString();
        var name = brand.getName().getValue();

        var brandEditInfo = new BrandEditInfo();
        brandEditInfo.setBrandID(brandIDStr);
        brandEditInfo.setName(name);

        updateBrandUseCase.update(brandEditInfo);
        verify(brandRepository, times(1)).update(any(Brand.class));
    }

}