package kz.arabro.planogram.nomenclature.adapter.controller;

import kz.arabro.planogram.nomenclature.boundary.model.BrandCreateInfo;
import kz.arabro.planogram.nomenclature.boundary.model.BrandEditInfo;
import kz.arabro.planogram.nomenclature.boundary.usecase.CreateBrandUseCase;
import kz.arabro.planogram.nomenclature.boundary.usecase.DeleteBrandUseCase;
import kz.arabro.planogram.nomenclature.boundary.usecase.ReadDataBrandUseCase;
import kz.arabro.planogram.nomenclature.boundary.usecase.UpdateBrandUseCase;
import kz.arabro.planogram.nomenclature.domain.exception.CodedException;
import kz.arabro.planogram.nomenclature.testdouble.controller.BrandRequestStub;
import kz.arabro.planogram.nomenclature.testdouble.entity.BrandStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BrandControllerTest {

    @Mock
    private CreateBrandUseCase createBrandUseCase;

    @Mock
    private DeleteBrandUseCase deleteBrandUseCase;

    @Mock
    private UpdateBrandUseCase updateBrandUseCase;

    @Mock
    private ReadDataBrandUseCase readDataBrandUseCase;

    private BrandController brandController;

    @BeforeEach
    void setUp() {
        this.brandController = new BrandController(
                createBrandUseCase,
                deleteBrandUseCase,
                updateBrandUseCase,
                readDataBrandUseCase
        );
    }

    @Test
    void createBrand_CreateBrandRequestIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> brandController.createBrand(null));
        assertEquals(ControllerError.BRAND_REQUEST_IS_REQUIRED, ex.getCode());
    }

    @Test
    void createBrand_UseCaseIsThrowEx_ThrowEx() {
        var createBrandRequest = BrandRequestStub.getCreateBrandRequest();

        when(createBrandUseCase.execute(any(BrandCreateInfo.class))).thenThrow(RuntimeException.class);
        assertThrows(RuntimeException.class, () -> brandController.createBrand(createBrandRequest));
    }

    @Test
    void createBrand_NoException_ReturnCreateBrandResponse() {
        var brand = BrandStub.getBrand();
        when(createBrandUseCase.execute(any(BrandCreateInfo.class))).thenReturn(brand);

        var createBrandRequest = BrandRequestStub.getCreateBrandRequest();
        var brandResponse = brandController.createBrand(createBrandRequest);
        assertEquals(brand.getId().getValue().toString(), brandResponse.getBrandID());
    }

    @Test
    void deleteBrand_DeleteBrandRequestIsThrowEx_ThrowEx() {
        doThrow(RuntimeException.class).when(deleteBrandUseCase).deleteBrandByID(anyString());

        var deleteBrandRequest = BrandRequestStub.getDeleteBrandRequest();

        assertThrows(RuntimeException.class, () -> brandController.deleteBrand(deleteBrandRequest));
    }

    @Test
    void deleteBrand_NoException_ReturnHttpStatusOk() {
        doNothing().when(deleteBrandUseCase).deleteBrandByID(anyString());

        var deleteBrandRequest = BrandRequestStub.getDeleteBrandRequest();

        var brandResponse = brandController.deleteBrand(deleteBrandRequest);

        assertEquals(HttpStatus.OK, brandResponse.getStatusCode());
        assertNull(brandResponse.getBody());
    }

    @Test
    void editBrand_EditBrandRequestIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> brandController.editBrand(null));
        assertEquals(ControllerError.BRAND_REQUEST_IS_REQUIRED, ex.getCode());
    }

    @Test
    void editBrand_UseCaseIsThrowEx_ThrowEx() {
        var editBrandRequest = BrandRequestStub.getEditBrandRequest();

        doThrow(RuntimeException.class).when(updateBrandUseCase).update(any(BrandEditInfo.class));
        assertThrows(RuntimeException.class, () -> brandController.editBrand(editBrandRequest));
    }

    @Test
    void editBrand_NoException_ReturnEditBrandResponse() {
        doNothing().when(updateBrandUseCase).update(any(BrandEditInfo.class));

        var editBrandRequest = BrandRequestStub.getEditBrandRequest();

        var editBrandResponse = brandController.editBrand(editBrandRequest);

        assertNotNull(editBrandResponse);

        var editBrandRequestID = editBrandRequest.getBrandID();
        var editBrandRequestName = editBrandRequest.getName();

        var editBrandResponseID = editBrandResponse.getBrandID();
        var editBrandResponseName = editBrandResponse.getName();

        assertEquals(editBrandRequestID, editBrandResponseID);
        assertEquals(editBrandRequestName, editBrandResponseName);
    }

    @Test
    void getBrandByID_GetBrandByIDRequestIsNull_RuntimeEx() {
        assertThrows(RuntimeException.class, () -> brandController.getBrandByID(null));
    }

    @Test
    void getBrandByID_NoException_ReturnBrandResponse() {
        var brand = BrandStub.getBrand();

        when(readDataBrandUseCase.findByID(anyString())).thenReturn(brand);

        var getBrandByIDRequest = BrandRequestStub.getBrandByIDRequest();

        var brandResponse = brandController.getBrandByID(getBrandByIDRequest);

        assertNotNull(brandResponse);
        assertEquals(brand.getId().getValue().toString(), brandResponse.getBrandID());
    }

    @Test
    void getAllBrands_NotValues_ReturnBrandResponses() {
        var count = 5;

        var brands = BrandStub.getBrands(count);
        when(readDataBrandUseCase.findAll()).thenReturn(brands);

        var brandResponses = brandController.getAllBrands();

        assertNotNull(brandResponses);
        for (int i = 0; i < count; i++) {
            var brandID = brands.get(i).getId().getValue().toString();
            var brandName = brands.get(i).getName().getValue();

            var brandResponseID = brandResponses.get(i).getBrandID();
            var brandResponseName = brandResponses.get(i).getName();

            assertEquals(brandID, brandResponseID);
            assertEquals(brandName, brandResponseName);
        }
    }
}