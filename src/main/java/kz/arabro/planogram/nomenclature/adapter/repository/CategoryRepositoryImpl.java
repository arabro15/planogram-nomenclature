package kz.arabro.planogram.nomenclature.adapter.repository;

import kz.arabro.planogram.nomenclature.adapter.repository.converter.CategoryConverter;
import kz.arabro.planogram.nomenclature.adapter.repository.jpa.CategoryDao;
import kz.arabro.planogram.nomenclature.boundary.repository.CategoryRepository;
import kz.arabro.planogram.nomenclature.domain.entity.Category;
import kz.arabro.planogram.nomenclature.domain.entity.CategoryID;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    private final CategoryDao categoryDao;

    public CategoryRepositoryImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Transactional
    @Override
    public void save(Category category) {
        if (category == null) {
            throw RepositoryError.errCategoryIsRequired();
        }

        var categoryDbModel = CategoryConverter.toModel(category);
        categoryDao.save(categoryDbModel);
    }

    @Transactional
    @Override
    public void deleteById(CategoryID categoryID) {
        if(categoryID == null) {
            throw RepositoryError.errCategoryIdIsRequired();
        }
        categoryDao.deleteById(categoryID.getValue());
    }

    @Transactional
    @Override
    public void deleteGroupCategoryByParentId(CategoryID parentID) {
        if (parentID == null) {
            throw RepositoryError.errCategoryParentIdIsRequired();
        }
        categoryDao.deleteAllByParentID(parentID.getValue());
    }

    @Transactional
    @Override
    public void update(Category category) {
        save(category);
    }

    @Transactional
    @Override
    public Optional<Category> findByID(CategoryID categoryID) {
        if (categoryID == null) {
            throw RepositoryError.errCategoryIdIsRequired();
        }

        return categoryDao.findById(categoryID.getValue()).
                map(CategoryConverter::toEntity);
    }

    @Transactional
    @Override
    public List<Category> findAllByParentID(CategoryID parentID) {
        if (parentID == null) {
            throw RepositoryError.errCategoryParentIdIsRequired();
        }

        var categoryDbModels = categoryDao.findAllByParentID(parentID.getValue());
        return CategoryConverter.toEntities(categoryDbModels);
    }

    @Transactional
    @Override
    public List<Category> findAll() {
        var categoryDbModels = categoryDao.findAll();
        return CategoryConverter.toEntities(categoryDbModels);
    }
}
