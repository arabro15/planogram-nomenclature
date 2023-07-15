package kz.arabro.planogram.nomenclature.domain.usecase;

import kz.arabro.planogram.nomenclature.boundary.repository.CategoryRepository;
import kz.arabro.planogram.nomenclature.boundary.usecase.CategoryDeleteUseCase;
import kz.arabro.planogram.nomenclature.domain.entity.CategoryID;
import org.springframework.stereotype.Service;

@Service
public class CategoryDeleteUseCaseImpl implements CategoryDeleteUseCase {

    private final CategoryRepository categoryRepository;

    public CategoryDeleteUseCaseImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void deleteCategoryByID(String categoryID) {
        if (categoryID == null) {
            throw UseCaseError.errCategoryIdIsRequired();
        }

        categoryRepository.deleteById(CategoryID.from(categoryID));

    }

    @Override
    public void deleteCategoryByParentID(String parentID) {
        if (parentID == null) {
            throw UseCaseError.errCategoryParentIdIsRequired();
        }

        categoryRepository.deleteGroupCategoryByParentId(CategoryID.from(parentID));
    }
}
