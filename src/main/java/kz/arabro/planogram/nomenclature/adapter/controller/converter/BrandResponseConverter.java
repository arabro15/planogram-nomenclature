package kz.arabro.planogram.nomenclature.adapter.controller.converter;

import kz.arabro.planogram.nomenclature.adapter.controller.ControllerError;
import kz.arabro.planogram.nomenclature.adapter.controller.response.BrandResponse;
import kz.arabro.planogram.nomenclature.domain.entity.brand.Brand;

import java.util.List;

public class BrandResponseConverter {

    public static BrandResponse brandToResponse(Brand brand) {
        if (brand == null) {
            throw ControllerError.errBrandIsNull();
        }

        var brandID = brand.getId().getValue().toString();
        var name = brand.getName().getValue();

        var brandResponse = new BrandResponse();
        brandResponse.setBrandID(brandID);
        brandResponse.setName(name);

        return brandResponse;
    }

    public static List<BrandResponse> brandsToResponses(List<Brand> brands) {
        if (brands == null) {
            throw ControllerError.errBrandsIsNull();
        }

        return brands.stream().
                map(BrandResponseConverter::brandToResponse).
                toList();
    }

    private BrandResponseConverter() {}
}
