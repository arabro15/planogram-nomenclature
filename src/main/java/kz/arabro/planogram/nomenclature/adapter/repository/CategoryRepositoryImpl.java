package kz.arabro.planogram.nomenclature.adapter.repository;

import kz.arabro.planogram.nomenclature.adapter.repository.converter.CategoryConverter;
import kz.arabro.planogram.nomenclature.adapter.repository.jpa.CategoryDao;
import kz.arabro.planogram.nomenclature.boundary.repository.CategoryRepository;
import kz.arabro.planogram.nomenclature.domain.entity.category.Category;
import kz.arabro.planogram.nomenclature.domain.entity.category.CategoryID;
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
        if (category == null) {
            throw RepositoryError.errCategoryIsRequired();
        }

        var id = category.getId().getValue();
        var name = category.getName().getValue();
        var color = category.getColor().name();
        var parentIDOpt = category.getParentID();

        if (parentIDOpt.isPresent()) {
            var parentID = parentIDOpt.get().getValue();
            categoryDao.updateById(id, name, color, parentID);
        }

        if (parentIDOpt.isEmpty()) {
            categoryDao.updateById(id, name, color, null);
        }

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
    public List<Category> findByParentID(CategoryID parentID) {
        if (parentID == null) {
            throw RepositoryError.errCategoryParentIdIsRequired();
        }

        var categoryDbModels = categoryDao.findByParentID(parentID.getValue());
        return CategoryConverter.toEntities(categoryDbModels);
    }

    @Transactional
    @Override
    public List<Category> findAll() {
        var categoryDbModels = categoryDao.findAll();
        return CategoryConverter.toEntities(categoryDbModels);
    }
}
