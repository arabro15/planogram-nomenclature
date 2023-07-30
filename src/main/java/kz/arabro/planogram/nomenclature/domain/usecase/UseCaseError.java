package kz.arabro.planogram.nomenclature.domain.usecase;

import kz.arabro.planogram.nomenclature.domain.entity.brand.BrandID;
import kz.arabro.planogram.nomenclature.domain.entity.category.CategoryID;
import kz.arabro.planogram.nomenclature.domain.entity.producer.ProducerID;
import kz.arabro.planogram.nomenclature.domain.entity.product.ProductID;
import kz.arabro.planogram.nomenclature.domain.exception.CodedException;

public class UseCaseError {
    public static final String BRAND_CREATE_INFO_IS_REQUIRED = "d281cc0a-001";
    public static final String BRAND_ID_IS_REQUIRED = "d281cc0a-002";
    public static final String BRAND_IS_NOT_FOUND = "d281cc0a-003";
    public static final String PRODUCER_CREATE_INFO_IS_REQUIRED = "d281cc0a-004";
    public static final String PRODUCER_EDIT_INFO_IS_REQUIRED = "d281cc0a-005";
    public static final String PRODUCER_IS_NOT_FOUND = "d281cc0a-006";
    public static final String CATEGORY_CREATE_INFO_IS_REQUIRED = "d281cc0a-007";
    public static final String CATEGORY_EDIT_INFO_IS_REQUIRED = "d281cc0a-008";
    public static final String CATEGORY_IS_NOT_FOUND = "d281cc0a-009";
    public static final String CATEGORY_ID_IS_REQUIRED = "d281cc0a-010";
    public static final String CATEGORY_PARENT_ID_IS_REQUIRED = "d281cc0a-011";
    public static final String PRODUCT_CREATE_INFO_IS_REQUIRED = "d281cc0a-012";
    public static final String PRODUCT_ID_IS_REQUIRED = "d281cc0a-013";
    public static final String PRODUCT_EDIT_INFO_IS_REQUIRED = "d281cc0a-014";
    public static final String PRODUCT_IS_NOT_FOUND = "d281cc0a-015";
    public static final String PRODUCT_BY_CODE_1C_IS_NOT_FOUND = "d281cc0a-016";
    public static final String PRODUCER_ID_IS_REQUIRED = "d281cc0a-017";
    private static final String BRAND_EDIT_INFO_IS_REQUIRED = "d281cc0a-018";

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
        var errMsg = String.format("Category is not found by ID: %s", categoryID.getValue().toString());
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

    public static CodedException errProductCreateInfoIsRequired() {
        var errMsg = "Value to create ProductCreateInfo is required";
        return new CodedException(PRODUCT_CREATE_INFO_IS_REQUIRED, errMsg);
    }

    public static CodedException errProductIDIsRequired() {
        var errMsg = "Value to create ProductID is required";
        return new CodedException(PRODUCT_ID_IS_REQUIRED, errMsg);
    }

    public static CodedException errProductEditInfoIsRequired() {
        var errMsg = "Value to create ProductEditInfo is required";
        return new CodedException(PRODUCT_EDIT_INFO_IS_REQUIRED, errMsg);
    }

    public static CodedException errProductNotFound(ProductID productID) {
        var errMsg = String.format("Product is not found by ID: %s", productID.getValue().toString());
        return new CodedException(PRODUCT_IS_NOT_FOUND, errMsg);
    }

    public static CodedException errProductByCode1CNotFound(String code1C) {
        var errMsg = String.format("Product is not found by Code1C: %s", code1C);
        return new CodedException(PRODUCT_BY_CODE_1C_IS_NOT_FOUND, errMsg);
    }

    public static CodedException errProducerIDIsRequired() {
        var errMsg = "Value to create ProducerID is required";
        return new CodedException(PRODUCER_ID_IS_REQUIRED, errMsg);
    }

    public static CodedException errBrandEditInfoIsRequired() {
        var errMsg = "Value to create BrandEditInfo is required";
        return new CodedException(BRAND_EDIT_INFO_IS_REQUIRED, errMsg);
    }
}
