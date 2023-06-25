package kz.arabro.planogram.nomenclature.domain.entity;

import kz.arabro.planogram.nomenclature.domain.exception.CodedException;

public final class ProducerError {
    private static final String PRODUCER_ID_VALUE_IS_REQUIRED = "14fe66f6-001";
    private static final String ILLEGAL_PRODUCER_ID_VALUE = "14fe66f6-002";
    private static final String NULL_ID_PRODUCER_VALUE = "14fe66f6-003";
    private static final String NULL_NAME_PRODUCER_VALUE = "14fe66f6-004";

    private ProducerError() {}

    public static CodedException errProducerIDValueIsRequired() {
        var errMsg = "Value to create ProducerID is required";
        return new CodedException(PRODUCER_ID_VALUE_IS_REQUIRED, errMsg);
    }

    public static CodedException errIllegalProducerIDValue(String valueStr, Throwable cause) {
        var errMsg = String.format("Illegal value = '%s' format to create ProducerID", valueStr);
        return new CodedException(ILLEGAL_PRODUCER_ID_VALUE, errMsg, cause);
    }

    public static CodedException errNullIdProducerValue() {
        var errMsg = "Value is null Id in ProducerBuilder";
        return new CodedException(NULL_ID_PRODUCER_VALUE, errMsg);
    }

    public static CodedException errNullNameProducerValue() {
        var errMsg = "Value is null Name in ProducerBuilder";
        return new CodedException(NULL_NAME_PRODUCER_VALUE, errMsg);
    }
}
