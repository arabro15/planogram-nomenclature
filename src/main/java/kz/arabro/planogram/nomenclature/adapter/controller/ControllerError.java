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
}
