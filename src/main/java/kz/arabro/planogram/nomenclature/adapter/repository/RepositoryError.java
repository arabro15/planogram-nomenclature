package kz.arabro.planogram.nomenclature.adapter.repository;

import kz.arabro.planogram.nomenclature.domain.exception.CodedException;

import javax.swing.undo.CompoundEdit;

public class RepositoryError {

    private static final String BRAND_DB_MODEL_IS_REQUIRED = "3fc0da0a-001";
    private static final String BRAND_DB_MODELS_IS_REQUIRED = "3fc0da0a-002";
    private static final String BRAND_IS_REQUIRED = "3fc0da0a-003";
    private static final String BRANDS_IS_REQUIRED = "3fc0da0a-004";
    private static final String BRAND_ID_IS_REQUIRED = "3fc0da0a-005";

    private RepositoryError() {}

    public static CodedException errBrandDbModelIsRequired() {
        var errMsg = "Value to create BrandDbModel is required";
        return new CodedException(BRAND_DB_MODEL_IS_REQUIRED, errMsg);
    }

    public static CodedException errBrandDbModelsIsRequired() {
        var errMsg = "Value to create BrandDbModels is required";
        return new CodedException(BRAND_DB_MODELS_IS_REQUIRED, errMsg);
    }

    public static CodedException errBrandIsRequired() {
        var errMsg = "Value to create Brand is required";
        return new CodedException(BRAND_IS_REQUIRED, errMsg);
    }

    public static CodedException errBrandsIsRequired() {
        var errMsg = "Value to create Brands is required";
        return new CodedException(BRANDS_IS_REQUIRED, errMsg);
    }

    public static CodedException errBrandIdIsRequired() {
        var errMsg = "Value to create BrandId is required";
        return new CodedException(BRAND_ID_IS_REQUIRED, errMsg);
    }
}
