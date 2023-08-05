package kz.arabro.planogram.nomenclature.domain.entity.category;

import kz.arabro.planogram.nomenclature.domain.exception.CodedException;

public final class CategoryError {

    public static final String CATEGORY_ID_VALUE_IS_REQUIRED = "f83fcc2e-001";
    public static final String ILLEGAL_CATEGORY_ID_VALUE = "f83fcc2e-002";
    public static final String NULL_ID_CATEGORY_VALUE = "f83fcc2e-003";
    public static final String NULL_NAME_CATEGORY_VALUE = "f83fcc2e-004";
    public static final String NULL_COLOR_CATEGORY_VALUE = "f83fcc2e-005";

    public static CodedException errCategoryIDValueIsRequired() {
        var errMsg = "Value to create CategoryID is required";
        return new CodedException(CATEGORY_ID_VALUE_IS_REQUIRED, errMsg);
    }

    public static CodedException errIllegalCategoryIDValue(String valueStr, Throwable cause) {
        var errMsg = String.format("Illegal value = '%s' format to create CategoryID", valueStr);
        return new CodedException(ILLEGAL_CATEGORY_ID_VALUE, errMsg, cause);
    }

    public static CodedException errNullIdCategoryValue() {
        var errMsg = "Value is null Id in CategoryBuilder";
        return new CodedException(NULL_ID_CATEGORY_VALUE, errMsg);
    }

    public static CodedException errNullNameCategoryValue() {
        var errMsg = "Value is null Name in CategoryBuilder";
        return new CodedException(NULL_NAME_CATEGORY_VALUE, errMsg);
    }

    public static CodedException errNullColorCategoryValue() {
        var errMsg = "Value is null Color in CategoryBuilder";
        return new CodedException(NULL_COLOR_CATEGORY_VALUE, errMsg);
    }

    private CategoryError() {}
}
