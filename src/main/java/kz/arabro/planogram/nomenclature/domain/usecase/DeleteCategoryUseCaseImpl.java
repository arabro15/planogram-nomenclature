package kz.arabro.planogram.nomenclature.domain.usecase;

import kz.arabro.planogram.nomenclature.boundary.repository.CategoryRepository;
import kz.arabro.planogram.nomenclature.boundary.usecase.DeleteCategoryUseCase;
import kz.arabro.planogram.nomenclature.domain.entity.category.CategoryID;
import org.springframework.stereotype.Service;

@Service
public class DeleteCategoryUseCaseImpl implements DeleteCategoryUseCase {

    private final CategoryRepository categoryRepository;

    public DeleteCategoryUseCaseImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void deleteCategoryByID(String categoryIDStr) {
        if (categoryIDStr == null) {
            throw UseCaseError.errCategoryIdIsRequired();
        }

        var categoryID = CategoryID.from(categoryIDStr);
        categoryRepository.deleteById(categoryID);

    }

    @Override
    public void deleteCategoriesByParentID(String parentIDStr) {
        if (parentIDStr == null) {
            throw UseCaseError.errCategoryParentIdIsRequired();
        }

        var parentID = CategoryID.from(parentIDStr);
        categoryRepository.deleteCategoriesByParentId(parentID);
    }
}
