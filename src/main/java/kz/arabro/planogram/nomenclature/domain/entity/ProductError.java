package kz.arabro.planogram.nomenclature.domain.entity;

import kz.arabro.planogram.nomenclature.domain.exception.CodedException;

public final class ProductError {
    private static final String PRODUCT_ID_VALUE_IS_REQUIRED = "b2b54f6d-001";
    private static final String ILLEGAL_PRODUCT_ID_VALUE = "b2b54f6d-002";
    private static final String EMPTY_NAME_VALUE = "b2b54f6d-003";
    private static final String NEGATIVE_BARCODE_VALUE = "b2b54f6d-004";
    private static final String LARGE_BARCODE_VALUE = "b2b54f6d-005";
    private static final String NULL_PRICE_VALUE = "b2b54f6d-006";
    private static final String NEGATIVE_SIZE_HEIGHT_VALUE = "b2b54f6d-007";
    private static final String NEGATIVE_SIZE_WEIGHT_VALUE = "b2b54f6d-008";
    private static final String NEGATIVE_SIZE_LENGTH_VALUE = "b2b54f6d-009";
    private static final String NULL_CODE1C_PRODUCT_VALUE = "b2b54f6d-010";
    private static final String NULL_NAME_PRODUCT_VALUE = "b2b54f6d-011";
    private static final String NULL_CATEGORY_PRODUCT_VALUE = "b2b54f6d-012";
    private static final String NULL_BRAND_PRODUCT_VALUE = "b2b54f6d-013";
    private static final String NULL_PRODUCER_PRODUCER_VALUE = "b2b54f6d-014";
    private static final String NULL_BARCODE_PRODUCT_VALUE = "b2b54f6d-015";
    private static final String NULL_SIZE_PRODUCT_VALUE = "b2b54f6d-016";

    private ProductError() {
    }
    public static CodedException errProductIDValueIsRequired() {
        var errMsg = "Value to create ProductID is required";
        return new CodedException(PRODUCT_ID_VALUE_IS_REQUIRED, errMsg);
    }

    public static CodedException errIllegalProductIDValue(String valueStr, Throwable cause) {
        var errMsg = String.format("Illegal value = '%s' format to create ProductID", valueStr);
        return new CodedException(ILLEGAL_PRODUCT_ID_VALUE, errMsg, cause);
    }

    public static CodedException errEmptyNameValue() {
        var errMsg = "Value to create Name is required";
        return new CodedException(EMPTY_NAME_VALUE, errMsg);
    }

    public static CodedException errNegativeBarcodeValue() {
        var errMsg = "Value is negative Barcode";
        return new CodedException(NEGATIVE_BARCODE_VALUE, errMsg);
    }

    public static CodedException errLargeBarcodeValue() {
        var errMsg = "Value is large Barcode";
        return new CodedException(LARGE_BARCODE_VALUE, errMsg);
    }

    public static CodedException errNullValuePrice() {
        var errMsg = "Value is null Price";
        return new CodedException(NULL_PRICE_VALUE, errMsg);
    }

    public static CodedException errNegativeSizeHeightValue() {
        var errMsg = "Value is negative Size Height";
        return new CodedException(NEGATIVE_SIZE_HEIGHT_VALUE, errMsg);
    }

    public static CodedException errNegativeSizeWeightValue() {
        var errMsg = "Value is negative Size Weight";
        return new CodedException(NEGATIVE_SIZE_WEIGHT_VALUE, errMsg);
    }

    public static CodedException errNegativeSizeLengthValue() {
        var errMsg = "Value is negative Size Length";
        return new CodedException(NEGATIVE_SIZE_LENGTH_VALUE, errMsg);
    }

    public static CodedException errNullCode1CProductValue() {
        var errMsg = "Value is null Code1C in ProductBuilder";
        return new CodedException(NULL_CODE1C_PRODUCT_VALUE, errMsg);
    }

    public static CodedException errNullNameProductValue() {
        var errMsg = "Value is null Name in ProductBuilder";
        return new CodedException(NULL_NAME_PRODUCT_VALUE, errMsg);
    }

    public static CodedException errNullCategoryProductValue() {
        var errMsg = "Value is null Category in ProductBuilder";
        return new CodedException(NULL_CATEGORY_PRODUCT_VALUE, errMsg);
    }

    public static CodedException errNullBrandProductValue() {
        var errMsg = "Value is null Brand in ProductBuilder";
        return new CodedException(NULL_BRAND_PRODUCT_VALUE, errMsg);
    }

    public static CodedException errNullProducerProductValue() {
        var errMsg = "Value is null Producer in ProductBuilder";
        return new CodedException(NULL_PRODUCER_PRODUCER_VALUE, errMsg);
    }

    public static CodedException errNullBarcodeProductValue() {
        var errMsg = "Value is null Barcode in ProductBuilder";
        return new CodedException(NULL_BARCODE_PRODUCT_VALUE, errMsg);
    }

    public static CodedException errNullSizeProductValue() {
        var errMsg = "Value is null Size in ProductBuilder";
        return new CodedException(NULL_SIZE_PRODUCT_VALUE, errMsg);
    }
}
