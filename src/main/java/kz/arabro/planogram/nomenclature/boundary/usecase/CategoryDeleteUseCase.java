package kz.arabro.planogram.nomenclature.boundary.usecase;

public interface CategoryDeleteUseCase {

    void deleteCategoryByID(String categoryID);
    void deleteCategoryByParentID(String parentID);

}
