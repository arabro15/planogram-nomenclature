package kz.arabro.planogram.nomenclature.domain.usecase;

import kz.arabro.planogram.nomenclature.domain.entity.BrandID;
import kz.arabro.planogram.nomenclature.domain.entity.CategoryID;
import kz.arabro.planogram.nomenclature.domain.entity.ProducerID;
import kz.arabro.planogram.nomenclature.domain.exception.CodedException;

public class UseCaseError {
    private static final String BRAND_CREATE_INFO_IS_REQUIRED = "d281cc0a-001";
    private static final String BRAND_ID_IS_REQUIRED = "d281cc0a-002";
    private static final String BRAND_IS_NOT_FOUND = "d281cc0a-003";
    private static final String PRODUCER_CREATE_INFO_IS_REQUIRED = "d281cc0a-004";
    private static final String PRODUCER_EDIT_INFO_IS_REQUIRED = "d281cc0a-005";
    private static final String PRODUCER_IS_NOT_FOUND = "d281cc0a-006";
    private static final String CATEGORY_CREATE_INFO_IS_REQUIRED = "d281cc0a-007";
    private static final String CATEGORY_EDIT_INFO_IS_REQUIRED = "d281cc0a-008";
    private static final String CATEGORY_IS_NOT_FOUND = "d281cc0a-009";
    private static final String CATEGORY_ID_IS_REQUIRED = "d281cc0a-010";
    private static final String CATEGORY_PARENT_ID_IS_REQUIRED = "d281cc0a-011";

    public static CodedException errBrandCreateInfoIsRequired() {
        var errMsg = "Value to create BrandCreateInfo is required";
        return new CodedException(BRAND_CREATE_INFO_IS_REQUIRED, errMsg);
    }

    public static CodedException errBrandIdIsRequired() {
        var errMsg = "Brand to delete BrandID is required";
        return new CodedException(BRAND_ID_IS_REQUIRED, errMsg);
    }

    public static CodedException errBrandNotFound(BrandID brandID) {
        var errMsg = String.format("Brand is not found by ID: %s", brandID.getValue().toString());
        return new CodedException(BRAND_IS_NOT_FOUND, errMsg);
    }

    public static CodedException errProducerCreateInfoIsRequired() {
        var errMsg = "Value to create ProducerCreateInfo is required";
        return new CodedException(PRODUCER_CREATE_INFO_IS_REQUIRED, errMsg);
    }

    public static CodedException errProducerEditInfoIsRequired() {
        var errMsg = "Value to create ProducerEditInfo is required";
        return new CodedException(PRODUCER_EDIT_INFO_IS_REQUIRED, errMsg);
    }

    public static CodedException errProducerNotFound(ProducerID producerID) {
        var errMsg = String.format("Producer is not found by ID: %s", producerID.getValue().toString());
        return new CodedException(PRODUCER_IS_NOT_FOUND, errMsg);
    }

    public static CodedException errCategoryCreateInfoIsRequired() {
        var errMsg = "Value to create CategoryCreateInfo is required";
        return new CodedException(CATEGORY_CREATE_INFO_IS_REQUIRED, errMsg);
    }

    public static CodedException errCategoryEditInfoIsRequired() {
        var errMsg = "Value to create CategoryEditInfo is required";
        return new CodedException(CATEGORY_EDIT_INFO_IS_REQUIRED, errMsg);
    }

    public static CodedException errCategoryNotFound(CategoryID categoryID) {
        var errMsg = String.format("Producer is not found by ID: %s", categoryID.getValue().toString());
        return new CodedException(CATEGORY_IS_NOT_FOUND, errMsg);
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
