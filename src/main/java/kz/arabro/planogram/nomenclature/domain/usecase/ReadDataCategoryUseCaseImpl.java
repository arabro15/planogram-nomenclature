package kz.arabro.planogram.nomenclature.domain.usecase;

import kz.arabro.planogram.nomenclature.boundary.repository.CategoryRepository;
import kz.arabro.planogram.nomenclature.boundary.usecase.ReadDataCategoryUseCase;
import kz.arabro.planogram.nomenclature.domain.entity.category.Category;
import kz.arabro.planogram.nomenclature.domain.entity.category.CategoryID;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadDataCategoryUseCaseImpl implements ReadDataCategoryUseCase {

    private final CategoryRepository categoryRepository;

    public ReadDataCategoryUseCaseImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category findByID(String categoryIDStr) {
        if (categoryIDStr == null) {
            throw UseCaseError.errCategoryIdIsRequired();
        }
        var categoryID = CategoryID.from(categoryIDStr);
        var categoryOpt = categoryRepository.findByID(categoryID);

        return categoryOpt.
                orElseThrow(() -> UseCaseError.errCategoryNotFound(categoryID));
    }

    //CR: All можно убрать из названия метода
    // Тут же не все категории возвращаются
    @Override
    public List<Category> findAllByParentID(String parentIDStr) {
        if (parentIDStr == null) {
            throw UseCaseError.errCategoryParentIdIsRequired();
        }

        var parentID = CategoryID.from(parentIDStr);

        return categoryRepository.findAllByParentID(parentID);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
