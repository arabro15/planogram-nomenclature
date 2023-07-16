package kz.arabro.planogram.nomenclature.adapter.controller;

import kz.arabro.planogram.nomenclature.domain.exception.CodedException;

public class ControllerError {

    public static final String BRAND_REQUEST_IS_REQUIRED = "05e416d3-001";
    public static final String BRAND_NAME_IS_REQUIRED = "05e416d3-002";
    private static final String BRAND_IS_NULL = "05e416d3-003";
    private static final String BRANDS_IS_NULL = "05e416d3-004";
    private static final String PRODUCER_REQUEST_IS_REQUIRED = "05e416d3-005";
    private static final String PRODUCER_NAME_IS_REQUIRED = "05e416d3-006";
    private static final String PRODUCER_IS_NULL = "05e416d3-007";
    private static final String PRODUCERS_IS_NULL = "05e416d3-008";
    private static final String CATEGORY_REQUEST_IS_REQUIRED = "05e416d3-009";
    private static final String CATEGORY_REQUEST_NULL_IS_REQUIRED = "05e416d3-010";
    private static final String CATEGORY_IS_NULL = "05e416d3-011";
    private static final String CATEGORIES_IS_NULL = "05e416d3-012";
    private static final String CREATE_PRODUCT_REQUEST_IS_REQUIRED = "05e416d3-013";
    private static final String EDIT_PRODUCT_REQUEST_IS_REQUIRED = "05e416d3-014";
    private static final String PRODUCT_IS_REQUIRED = "05e416d3-015";
    private static final String PRODUCTS_IS_REQUIRED = "05e416d3-016";

    private ControllerError() {}

    public static CodedException errBrandRequestIsRequired() {
        var errMsg = "request is required";
        return new CodedException(BRAND_REQUEST_IS_REQUIRED , errMsg);
    }

    public static CodedException errBrandRequestNullIsRequired() {
        var errMsg = "request null is required";
        return new CodedException(BRAND_NAME_IS_REQUIRED, errMsg);
    }

    public static CodedException errBrandIsNull() {
        var errMsg = "Brand is null";
        return new CodedException(BRAND_IS_NULL, errMsg);
    }

    public static CodedException errBrandsIsNull() {
        var errMsg = "Brands is null";
        return new CodedException(BRANDS_IS_NULL, errMsg);
    }

    public static CodedException errProducerRequestIsRequired() {
        var errMsg = "request is required";
        return new CodedException(PRODUCER_REQUEST_IS_REQUIRED , errMsg);
    }

    public static CodedException errProducerRequestNullIsRequired() {
        var errMsg = "request null is required";
        return new CodedException(PRODUCER_NAME_IS_REQUIRED, errMsg);
    }

    public static CodedException errProducerIsNull() {
        var errMsg = "Brand is null";
        return new CodedException(PRODUCER_IS_NULL, errMsg);
    }

    public static CodedException errProducersIsNull() {
        var errMsg = "Brands is null";
        return new CodedException(PRODUCERS_IS_NULL, errMsg);
    }

    public static CodedException errCategoryRequestIsRequired() {
        var errMsg = "request is required";
        return new CodedException(CATEGORY_REQUEST_IS_REQUIRED , errMsg);
    }

    public static CodedException errCategoryRequestNullIsRequired() {
        var errMsg = "request null is required";
        return new CodedException(CATEGORY_REQUEST_NULL_IS_REQUIRED, errMsg);
    }

    public static CodedException errCategoryIsNull() {
        var errMsg = "Category is null";
        return new CodedException(CATEGORY_IS_NULL, errMsg);
    }

    public static CodedException errCategoriesIsNull() {
        var errMsg = "Categories is null";
        return new CodedException(CATEGORIES_IS_NULL, errMsg);
    }

    public static CodedException errCreateProductRequestIsRequired() {
        var errMsg = "request null is required";
        return new CodedException(CREATE_PRODUCT_REQUEST_IS_REQUIRED, errMsg);
    }

    public static CodedException errEditProductRequestIsRequired() {
        var errMsg = "request null is required";
        return new CodedException(EDIT_PRODUCT_REQUEST_IS_REQUIRED, errMsg);
    }

    public static CodedException errProductIsRequired() {
        var errMsg = "Product is null";
        return new CodedException(PRODUCT_IS_REQUIRED, errMsg);
    }

    public static CodedException errProductsIsRequired() {
        var errMsg = "Products is null";
        return new CodedException(PRODUCTS_IS_REQUIRED, errMsg);
    }
}
