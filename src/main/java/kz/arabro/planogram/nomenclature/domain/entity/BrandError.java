package kz.arabro.planogram.nomenclature.domain.entity;

import kz.arabro.planogram.nomenclature.domain.exception.CodedException;

public final class BrandError {

    private static final String BRAND_ID_VALUE_IS_REQUIRED = "51901dae-001";
    private static final String ILLEGAL_BRAND_ID_VALUE = "51901dae-002";
    private static final String NULL_ID_BRAND_VALUE = "51901dae-003";
    private static final String NULL_NAME_BRAND_VALUE = "51901dae-004";

    private BrandError() {}

    public static CodedException errBrandIDValueIsRequired() {
        var errMsg = "Value to create BrandID is required";
        return new CodedException(BRAND_ID_VALUE_IS_REQUIRED, errMsg);
    }

    public static CodedException errIllegalBrandIDValue(String valueStr, Throwable cause) {
        var errMsg = String.format("Illegal value = '%s' format to create BrandID", valueStr);
        return new CodedException(ILLEGAL_BRAND_ID_VALUE, errMsg, cause);
    }

    public static CodedException errNullIdBrandValue() {
        var errMsg = "Value is null Id in BrandBuilder";
        return new CodedException(NULL_ID_BRAND_VALUE, errMsg);
    }

    public static CodedException errNullNameBrandValue() {
        var errMsg = "Value is null Name in BrandBuilder";
        return new CodedException(NULL_NAME_BRAND_VALUE, errMsg);
    }
}
