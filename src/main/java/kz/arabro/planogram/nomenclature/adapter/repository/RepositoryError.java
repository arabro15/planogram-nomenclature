package kz.arabro.planogram.nomenclature.adapter.repository;

import kz.arabro.planogram.nomenclature.domain.exception.CodedException;

public class RepositoryError {

    private static final String BRAND_DB_MODEL_IS_REQUIRED = "3fc0da0a-001";
    private static final String BRAND_DB_MODELS_IS_REQUIRED = "3fc0da0a-002";
    private static final String BRAND_IS_REQUIRED = "3fc0da0a-003";
    private static final String BRANDS_IS_REQUIRED = "3fc0da0a-004";
    private static final String BRAND_ID_IS_REQUIRED = "3fc0da0a-005";
    private static final String PRODUCER_DB_MODEL_IS_REQUIRED = "3fc0da0a-006";
    private static final String PRODUCER_DB_MODELS_IS_REQUIRED = "3fc0da0a-007";
    private static final String PRODUCER_IS_REQUIRED = "3fc0da0a-008";
    private static final String PRODUCERS_IS_REQUIRED = "3fc0da0a-009";
    private static final String PRODUCER_ID_IS_REQUIRED = "3fc0da0a-010";
    private static final String CATEGORY_DB_MODEL_IS_REQUIRED = "3fc0da0a-011";
    private static final String CATEGORY_DB_MODELS_IS_REQUIRED = "3fc0da0a-012";
    private static final String CATEGORY_IS_REQUIRED = "3fc0da0a-013";
    private static final String CATEGORIES_IS_REQUIRED = "3fc0da0a-014";
    private static final String CATEGORY_ID_IS_REQUIRED = "3fc0da0a-015";
    private static final String CATEGORY_PARENT_ID_IS_REQUIRED = "3fc0da0a-016";

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

    public static CodedException errProducerDbModelIsRequired() {
        var errMsg = "Value to create ProducerDbModel is required";
        return new CodedException(PRODUCER_DB_MODEL_IS_REQUIRED, errMsg);
    }

    public static CodedException errProducerDbModelsIsRequired() {
        var errMsg = "Value to create ProducerDbModels is required";
        return new CodedException(PRODUCER_DB_MODELS_IS_REQUIRED, errMsg);
    }

    public static CodedException errProducerIsRequired() {
        var errMsg = "Value to create Producer is required";
        return new CodedException(PRODUCER_IS_REQUIRED, errMsg);
    }

    public static CodedException errProducersIsRequired() {
        var errMsg = "Value to create Producers is required";
        return new CodedException(PRODUCERS_IS_REQUIRED, errMsg);
    }

    public static CodedException errProducerIdIsRequired() {
        var errMsg = "Value to create ProducerId is required";
        return new CodedException(PRODUCER_ID_IS_REQUIRED, errMsg);
    }

    public static CodedException errCategoryDbModelIsRequired() {
        var errMsg = "Value to create CategoryDbModel is required";
        return new CodedException(CATEGORY_DB_MODEL_IS_REQUIRED, errMsg);
    }

    public static CodedException errCategoryDbModelsIsRequired() {
        var errMsg = "Value to create CategoryDbModels is required";
        return new CodedException(CATEGORY_DB_MODELS_IS_REQUIRED, errMsg);
    }

    public static CodedException errCategoryIsRequired() {
        var errMsg = "Value to create Category is required";
        return new CodedException(CATEGORY_IS_REQUIRED, errMsg);
    }

    public static CodedException errCategoriesIsRequired() {
        var errMsg = "Value to create Categories is required";
        return new CodedException(CATEGORIES_IS_REQUIRED, errMsg);
    }

    public static CodedException errCategoryIdIsRequired() {
        var errMsg = "Value to create CategoryID is required";
        return new CodedException(CATEGORY_ID_IS_REQUIRED, errMsg);
    }

    public static CodedException errCategoryParentIdIsRequired() {
        var errMsg = "Value to create Category parentID is required";
        return new CodedException(CATEGORY_PARENT_ID_IS_REQUIRED, errMsg);
    }
}
