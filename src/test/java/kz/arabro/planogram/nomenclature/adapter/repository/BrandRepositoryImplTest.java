package kz.arabro.planogram.nomenclature.adapter.repository;

import kz.arabro.planogram.nomenclature.adapter.repository.jpa.BrandDao;
import kz.arabro.planogram.nomenclature.adapter.repository.model.BrandDbModel;
import kz.arabro.planogram.nomenclature.boundary.repository.BrandRepository;
import kz.arabro.planogram.nomenclature.domain.entity.brand.BrandID;
import kz.arabro.planogram.nomenclature.domain.exception.CodedException;
import kz.arabro.planogram.nomenclature.testdouble.entity.BrandStub;
import kz.arabro.planogram.nomenclature.testdouble.repository.BrandDbModelStub;
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
class BrandRepositoryImplTest {

    @Mock
    private BrandDao brandDao;

    @Captor
    private ArgumentCaptor<BrandDbModel> brandDbModelCaptor;

    @Captor
    private ArgumentCaptor<UUID> uuidCaptor;

    @Captor
    private ArgumentCaptor<String> stringCaptor;

    private BrandRepository brandRepository;

    @BeforeEach
    void setUp() {
        this.brandRepository = new BrandRepositoryImpl(brandDao);
    }

    @Test
    void save_BrandIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> brandRepository.save(null));
        assertEquals(RepositoryError.BRAND_IS_REQUIRED, ex.getCode());
    }

    @Test
    void save_ValueIsValid_SaveBrand() {
        var brand = BrandStub.getBrand();

        brandRepository.save(brand);
        verify(brandDao).save(brandDbModelCaptor.capture());

        var brandDbModel = brandDbModelCaptor.getValue();

        var brandID = brand.getId().getValue();
        var brandName = brand.getName().getValue();

        var brandDbModelID = brandDbModel.getId();
        var brandDbModelName = brandDbModel.getName();

        assertNotNull(brandDbModel);
        assertEquals(brandID, brandDbModelID);
        assertEquals(brandName, brandDbModelName);
    }

    @Test
    void deleteById_BrandIDIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> brandRepository.deleteById(null));
        assertEquals(RepositoryError.BRAND_ID_IS_REQUIRED, ex.getCode());
    }

    @Test
    void deleteByID_ValueIsValid_DeleteBrand() {
        var brandID = BrandStub.getBrand().getId();
        var brandIDValue = brandID.getValue();

        brandRepository.deleteById(brandID);
        verify(brandDao, times(1)).deleteById(brandIDValue);
    }

    @Test
    void update_BrandIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> brandRepository.update(null));
        assertEquals(RepositoryError.BRAND_IS_REQUIRED, ex.getCode());
    }

    @Test
    void update_ValueIsValid_UpdateBrand() {
        var brand = BrandStub.getBrand();

        var brandIDValue = brand.getId().getValue();
        var brandNameStr = brand.getName().getValue();

        brandRepository.update(brand);
        verify(brandDao).updateById(uuidCaptor.capture(), stringCaptor.capture());

        var brandIDValueCaptor = uuidCaptor.getValue();
        var brandNameStrCaptor = stringCaptor.getValue();

        assertEquals(brandIDValue, brandIDValueCaptor);
        assertEquals(brandNameStr, brandNameStrCaptor);
    }

    @Test
    void findByID_BrandIDIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> brandRepository.findByID(null));
        assertEquals(RepositoryError.BRAND_ID_IS_REQUIRED, ex.getCode());
    }

    @Test
    void findByID_ValueIsValid_ReturnBrand() {
        var brandDbModel = BrandDbModelStub.getBrandDbModel();
        when(brandDao.findById(any(UUID.class))).thenReturn(Optional.of(brandDbModel));

        var brandOpt = brandRepository.findByID(BrandID.newID());
        assertNotNull(brandOpt);
        assertTrue(brandOpt.isPresent());

        assertEquals(brandDbModel.getId(), brandOpt.get().getId().getValue());
    }

    @Test
    void findAll_NotParams_ReturnBrands() {
        var brandDbModels = BrandDbModelStub.getBrandDbModels(5);
        when(brandDao.findAll()).thenReturn(brandDbModels);

        var brands = brandRepository.findAll();
        assertNotNull(brands);
        assertEquals(brandDbModels.size(), brands.size());
        assertEquals(brandDbModels.get(4).getId(), brands.get(4).getId().getValue());
    }

    @Test
    void existsByID_BrandIDIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> brandRepository.existsById(null));
        assertEquals(RepositoryError.BRAND_ID_IS_REQUIRED, ex.getCode());
    }

    @Test
    void existsByID_ValueIsValid_ReturnBoolean() {
        var brandID = BrandStub.getBrand().getId();
        when(brandDao.existsById(brandID.getValue())).thenReturn(Boolean.TRUE);

        var brandIDIsTrue = brandRepository.existsById(brandID);
        assertTrue(brandIDIsTrue);
    }

}