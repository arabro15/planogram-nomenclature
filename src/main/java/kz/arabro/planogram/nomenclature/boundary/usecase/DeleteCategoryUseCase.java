package kz.arabro.planogram.nomenclature.boundary.usecase;

public interface DeleteCategoryUseCase {

    void deleteCategoryByID(String categoryID);
    void deleteCategoriesByParentID(String parentID);

}
