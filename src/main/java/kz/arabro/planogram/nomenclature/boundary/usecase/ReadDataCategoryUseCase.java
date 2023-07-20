package kz.arabro.planogram.nomenclature.boundary.usecase;

import kz.arabro.planogram.nomenclature.domain.entity.category.Category;

import java.util.List;

public interface ReadDataCategoryUseCase {

    Category findByID(String categoryID);
    List<Category> findAllByParentID(String parentID);
    List<Category> findAll();
}
