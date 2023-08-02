package kz.arabro.planogram.nomenclature.domain.usecase;

import kz.arabro.planogram.nomenclature.boundary.model.CategoryCreateInfo;
import kz.arabro.planogram.nomenclature.boundary.repository.CategoryRepository;
import kz.arabro.planogram.nomenclature.boundary.usecase.CreateCategoryUseCase;
import kz.arabro.planogram.nomenclature.domain.entity.category.Category;
import kz.arabro.planogram.nomenclature.domain.entity.category.CategoryBuilder;
import kz.arabro.planogram.nomenclature.domain.entity.category.CategoryID;
import kz.arabro.planogram.nomenclature.domain.entity.category.Color;
import kz.arabro.planogram.nomenclature.domain.entity.product.Name;
import org.springframework.stereotype.Service;

@Service
public class CreateCategoryUseCaseImpl implements CreateCategoryUseCase {

    private final CategoryRepository categoryRepository;

    public CreateCategoryUseCaseImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category execute(CategoryCreateInfo info) {
        if (info == null) {
            throw UseCaseError.errCategoryCreateInfoIsRequired();
        }

        var name = Name.of(info.getName());
        var color = Color.NONE;

        if (info.getColor() != null) {
            color = Color.valueOf(info.getColor());
        }

        var categoryBuilder = new CategoryBuilder().
                setID(CategoryID.newID()).
                setName(name).
                setColor(color);
        if (info.getParentID() != null) {
            var parentID = CategoryID.from(info.getParentID());
            categoryBuilder.setParentID(parentID);
        }
        var category = categoryBuilder.build();

        categoryRepository.save(category);
        return category;
    }
}
