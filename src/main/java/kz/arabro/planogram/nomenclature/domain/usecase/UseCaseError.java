package kz.arabro.planogram.nomenclature.domain.usecase;

import kz.arabro.planogram.nomenclature.domain.exception.CodedException;

public class UseCaseError {
    private static final String BRAND_CREATE_INFO_IS_REQUIRED = "d281cc0a-001";
    private static final String BRAND_ID_IS_REQUIRED = "d281cc0a-002";

    public static CodedException errBrandCreateInfoIsRequired() {
        var errMsg = "Value to create BrandCreateInfo is required";
        return new CodedException(BRAND_CREATE_INFO_IS_REQUIRED, errMsg);
    }

    public static CodedException errBrandIdIsRequired() {
        var errMsg = "Brand to delete BrandID is required";
        return new CodedException(BRAND_ID_IS_REQUIRED, errMsg);
    }
}
