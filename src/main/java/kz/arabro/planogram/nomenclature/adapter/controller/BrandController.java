package kz.arabro.planogram.nomenclature.adapter.controller;

import kz.arabro.planogram.nomenclature.adapter.controller.converter.BrandRequestConverter;
import kz.arabro.planogram.nomenclature.adapter.controller.converter.BrandResponseConverter;
import kz.arabro.planogram.nomenclature.adapter.controller.request.CreateBrandRequest;
import kz.arabro.planogram.nomenclature.adapter.controller.request.DeleteBrandRequest;
import kz.arabro.planogram.nomenclature.adapter.controller.request.EditBrandRequest;
import kz.arabro.planogram.nomenclature.adapter.controller.request.GetBrandByIDRequest;
import kz.arabro.planogram.nomenclature.adapter.controller.response.BrandResponse;
import kz.arabro.planogram.nomenclature.adapter.controller.response.CreateBrandResponse;
import kz.arabro.planogram.nomenclature.adapter.controller.response.EditBrandResponse;
import kz.arabro.planogram.nomenclature.boundary.usecase.BrandCreatorUseCase;
import kz.arabro.planogram.nomenclature.boundary.usecase.BrandDeleteUseCase;
import kz.arabro.planogram.nomenclature.boundary.usecase.BrandEditorUseCase;
import kz.arabro.planogram.nomenclature.boundary.usecase.BrandReadDataUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
public class BrandController {

    private final BrandCreatorUseCase brandCreatorUseCase;
    private final BrandDeleteUseCase brandDeleteUseCase;
    private final BrandEditorUseCase brandEditorUseCase;
    private final BrandReadDataUseCase brandReadDataUseCase;

    public BrandController(BrandCreatorUseCase brandCreatorUseCase,
                           BrandDeleteUseCase brandDeleteUseCase,
                           BrandEditorUseCase brandEditorUseCase,
                           BrandReadDataUseCase brandReadDataUseCase) {
        this.brandCreatorUseCase = brandCreatorUseCase;
        this.brandDeleteUseCase = brandDeleteUseCase;
        this.brandEditorUseCase = brandEditorUseCase;
        this.brandReadDataUseCase = brandReadDataUseCase;
    }

    @PostMapping(path = "/create-brand")
    public CreateBrandResponse createBrand(@RequestBody CreateBrandRequest request) {
        var info = BrandRequestConverter.createBrandRequestToModel(request);

        var brand = brandCreatorUseCase.execute(info);

        var response = new CreateBrandResponse();
        response.setBrandID(brand.getId().toString());
        return response;
    }

    @PostMapping(path = "/delete-by-id-brand")
    public ResponseEntity<Object> deleteBrand(@RequestBody DeleteBrandRequest request) {
        brandDeleteUseCase.deleteBrandByID(request.getBrandID());
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping(path = "/edit-brand")
    public EditBrandResponse editBrand(@RequestBody EditBrandRequest request) {
        var brand = BrandRequestConverter.editBrandRequestToModel(request);

        brandEditorUseCase.update(brand);

        var response = new EditBrandResponse();
        response.setBrandID(brand.getBrandID());
        response.setName(brand.getName());

        return response;
    }

    @PostMapping(path = "/get-brand-by-id")
    public BrandResponse getBrandByID(@RequestBody GetBrandByIDRequest request) {
        var brand = brandReadDataUseCase.findByID(request.getBrandID());
        return BrandResponseConverter.brandToResponse(brand);
    }

    @PostMapping(path = "/get-all-brands")
    public List<BrandResponse> getAllBrands() {
        var brands = brandReadDataUseCase.findAll();
        return BrandResponseConverter.brandsToResponses(brands);
    }


}
