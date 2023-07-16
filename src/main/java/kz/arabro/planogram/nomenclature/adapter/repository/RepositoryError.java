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
    private static final String PRODUCT_IS_REQUIRED = "3fc0da0a-017";
    private static final String PRODUCT_DB_MODEL_IS_REQUIRED = "3fc0da0a-018";
    private static final String PRODUCT_DB_MODELS_IS_REQUIRED = "3fc0da0a-018";
    private static final String PRODUCTS_IS_REQUIRED = "3fc0da0a-019";
    private static final String PRODUCT_ID_IS_REQUIRED = "3fc0da0a-020";
    private static final String PRODUCT_CODE_1C_IS_REQUIRED = "3fc0da0a-021";

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

    public static CodedException errProductIsRequired() {
        var errMsg = "Value to create Product is required";
        return new CodedException(PRODUCT_IS_REQUIRED, errMsg);
    }

    public static CodedException errProductDbModelIsRequired() {
        var errMsg = "Value to create ProductDbModel is required";
        return new CodedException(PRODUCT_DB_MODEL_IS_REQUIRED, errMsg);
    }

    public static CodedException errProductDbModelsIsRequired() {
        var errMsg = "Value to create ProductDbModels is required";
        return new CodedException(PRODUCT_DB_MODELS_IS_REQUIRED, errMsg);
    }

    public static CodedException errProductsIsRequired() {
        var errMsg = "Value to create Products is required";
        return new CodedException(PRODUCTS_IS_REQUIRED, errMsg);
    }

    public static CodedException errProductIdIsRequired() {
        var errMsg = "Value to create ProductID is required";
        return new CodedException(PRODUCT_ID_IS_REQUIRED, errMsg);
    }

    public static CodedException errProductCode1CIsRequired() {
        var errMsg = "Value to create Product Code1C is required";
        return new CodedException(PRODUCT_CODE_1C_IS_REQUIRED, errMsg);
    }
}
