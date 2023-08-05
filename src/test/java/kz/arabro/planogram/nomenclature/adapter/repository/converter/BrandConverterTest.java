package kz.arabro.planogram.nomenclature.adapter.repository.converter;

import kz.arabro.planogram.nomenclature.adapter.repository.RepositoryError;
import kz.arabro.planogram.nomenclature.domain.exception.CodedException;
import kz.arabro.planogram.nomenclature.testdouble.entity.BrandStub;
import kz.arabro.planogram.nomenclature.testdouble.repository.BrandDbModelStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrandConverterTest {

    @Test
    void toEntity_BrandDbModelIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> BrandConverter.toEntity(null));
        assertEquals(RepositoryError.BRAND_DB_MODEL_IS_REQUIRED, ex.getCode());
    }

    @Test
    void toEntity_AllParamsIsValid_ReturnBrand() {
        var brandDbModel = BrandDbModelStub.getBrandDbModel();
        var brand = BrandConverter.toEntity(brandDbModel);

        var brandDbModelID = brandDbModel.getId();
        var brandDbModelName = brandDbModel.getName();

        var brandID = brand.getId().getValue();
        var brandName = brand.getName().getValue();

        assertNotNull(brand);
        assertEquals(brandDbModelID, brandID);
        assertEquals(brandDbModelName, brandName);
    }

    @Test
    void toEntities_BrandDbModelsIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> BrandConverter.toEntities(null));
        assertEquals(RepositoryError.BRAND_DB_MODELS_IS_REQUIRED, ex.getCode());
    }

    @Test
    void toEntities_AllParamsIsValid_ReturnBrandDbModels() {
        var count = 5;

        var brandDbModels = BrandDbModelStub.getBrandDbModels(count);
        var brands = BrandConverter.toEntities(brandDbModels);

        assertNotNull(brands);

        for (int i = 0; i < count; i++) {
            var brandDbModelID = brandDbModels.get(i).getId();
            var brandDbModelName = brandDbModels.get(i).getName();

            var brandID = brands.get(i).getId().getValue();
            var brandName = brands.get(i).getName().getValue();

            assertEquals(brandDbModelID, brandID);
            assertEquals(brandDbModelName, brandName);
        }
    }

    @Test
    void toModel_BrandIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> BrandConverter.toModel(null));
        assertEquals(RepositoryError.BRAND_IS_REQUIRED, ex.getCode());
    }

    @Test
    void toModel_AllParamsIsValid_ReturnBrandDbModel() {
        var brand = BrandStub.getBrand();
        var brandDbModel = BrandConverter.toModel(brand);

        var brandID = brand.getId().getValue();
        var brandName = brand.getName().getValue();

        var brandDbModelID = brandDbModel.getId();
        var brandDbModelName = brandDbModel.getName();

        assertNotNull(brandDbModel);
        assertEquals(brandID, brandDbModelID);
        assertEquals(brandName, brandDbModelName);
    }

    @Test
    void toModels_BrandsIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> BrandConverter.toModels(null));
        assertEquals(RepositoryError.BRANDS_IS_REQUIRED, ex.getCode());
    }

    @Test
    void toModels_AllParamsIsValid_ReturnBrandDbModels() {
        var count = 5;

        var brands = BrandStub.getBrands(count);
        var brandDbModels = BrandConverter.toModels(brands);

        assertNotNull(brandDbModels);

        for (int i = 0; i < count; i++) {
            var brandID = brands.get(i).getId().getValue();
            var brandName = brands.get(i).getName().getValue();

            var brandDbModelID = brandDbModels.get(i).getId();
            var brandDbModelName = brandDbModels.get(i).getName();

            assertEquals(brandID, brandDbModelID);
            assertEquals(brandName, brandDbModelName);
        }
    }
}