package kz.arabro.planogram.nomenclature.integration.adapter.repository;

import kz.arabro.planogram.nomenclature.adapter.repository.jpa.BrandDao;
import kz.arabro.planogram.nomenclature.boundary.repository.BrandRepository;
import kz.arabro.planogram.nomenclature.domain.entity.brand.Brand;
import kz.arabro.planogram.nomenclature.domain.entity.brand.BrandID;
import kz.arabro.planogram.nomenclature.testdouble.entity.BrandStub;
import kz.arabro.planogram.nomenclature.util.annotation.IntegrationTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@IntegrationTest
class BrandRepositoryTest {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private BrandDao brandDao;

    private static final Brand BRAND = BrandStub.getBrand();

    @BeforeEach
    void setUp() {
        brandRepository.save(BRAND);
    }

    @AfterEach
    void tearDown() {
        brandDao.deleteAll();
    }

    @Test
    void update_BrandUpdated() {
        var brandToUpdate = BrandStub.getBrand();
        brandRepository.save(brandToUpdate);

        var brandFromDBOpt = brandRepository.findByID(brandToUpdate.getId());
        assertTrue(brandFromDBOpt.isPresent());
        assertNotEquals(brandToUpdate, brandFromDBOpt.get());

        brandRepository.update(brandToUpdate);

        brandFromDBOpt = brandRepository.findByID(brandToUpdate.getId());
        assertTrue(brandFromDBOpt.isPresent());
        assertEquals(brandToUpdate.getId().getValue(), brandFromDBOpt.get().getId().getValue());
    }

    @Test
    void findByID_BrandNotExist_ReturnEmptyOptional() {
        var brandOpt = brandRepository.findByID(BrandID.newID());
        assertTrue(brandOpt.isEmpty());
    }

    @Test
    void findByID_BrandExist_ReturnBrand() {
        var brandOpt = brandRepository.findByID(BRAND.getId());
        assertTrue(brandOpt.isPresent());

        var brandFromDB = brandOpt.get();
        assertEquals(BRAND.getId().getValue(), brandFromDB.getId().getValue());
    }

    @Test
    void findAll_ReturnAllBrands() {
        var count = 10;
        var brandsToSave = BrandStub.getBrands(count);
        brandsToSave.forEach(brandRepository::save);

        var brandsFromDB = brandRepository.findAll();
        assertNotNull(brandsFromDB);
        assertFalse(brandsFromDB.isEmpty());

        brandsFromDB.forEach(Assertions::assertNotNull);
    }

    @Test
    void existsByID_BrandID_ReturnTrue() {
        var isExists = brandRepository.existsById(BRAND.getId());
        assertTrue(isExists);
    }

    @Test
    void existByID_UnknownBrand_ReturnFalse() {
        var isExists = brandRepository.existsById(BrandID.newID());
        assertFalse(isExists);
    }

}