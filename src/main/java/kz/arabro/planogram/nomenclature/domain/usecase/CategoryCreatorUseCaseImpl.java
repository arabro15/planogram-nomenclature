package kz.arabro.planogram.nomenclature.domain.usecase;

import kz.arabro.planogram.nomenclature.boundary.model.CategoryCreateInfo;
import kz.arabro.planogram.nomenclature.boundary.repository.CategoryRepository;
import kz.arabro.planogram.nomenclature.boundary.usecase.CategoryCreatorUseCase;
import kz.arabro.planogram.nomenclature.domain.entity.*;
import org.springframework.stereotype.Service;

@Service
public class CategoryCreatorUseCaseImpl implements CategoryCreatorUseCase {

    private final CategoryRepository categoryRepository;

    public CategoryCreatorUseCaseImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category execute(CategoryCreateInfo info) {
        if (info == null) {
            throw UseCaseError.errCategoryCreateInfoIsRequired();
        }
        var name = Name.of(info.getName());
        var color = Color.valueOf(info.getColor());
        var parentID = CategoryID.from(info.getParentID());

        var category = new CategoryBuilder().
                setID(CategoryID.newID()).
                setName(name).
                setColor(color).
                setParentID(parentID).
                build();

        categoryRepository.save(category);
        return category;
    }
}
