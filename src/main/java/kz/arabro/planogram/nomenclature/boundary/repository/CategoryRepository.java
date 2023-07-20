package kz.arabro.planogram.nomenclature.boundary.repository;

import kz.arabro.planogram.nomenclature.domain.entity.category.Category;
import kz.arabro.planogram.nomenclature.domain.entity.category.CategoryID;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    void save(Category category);
    void deleteById(CategoryID categoryID);
    void deleteGroupCategoryByParentId(CategoryID parentID);
    void update(Category category);
    Optional<Category> findByID(CategoryID categoryID);
    List<Category> findAllByParentID(CategoryID parentID);
    List<Category> findAll();

}
