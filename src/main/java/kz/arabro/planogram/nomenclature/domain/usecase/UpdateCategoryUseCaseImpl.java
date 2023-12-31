package kz.arabro.planogram.nomenclature.domain.usecase;

import jakarta.annotation.Nullable;
import kz.arabro.planogram.nomenclature.boundary.model.CategoryEditInfo;
import kz.arabro.planogram.nomenclature.boundary.repository.CategoryRepository;
import kz.arabro.planogram.nomenclature.boundary.usecase.UpdateCategoryUseCase;
import kz.arabro.planogram.nomenclature.domain.entity.category.CategoryBuilder;
import kz.arabro.planogram.nomenclature.domain.entity.category.CategoryID;
import kz.arabro.planogram.nomenclature.domain.entity.category.Color;
import kz.arabro.planogram.nomenclature.domain.entity.product.Name;
import org.springframework.stereotype.Service;

@Service
public class UpdateCategoryUseCaseImpl implements UpdateCategoryUseCase {

    private final CategoryRepository categoryRepository;

    public UpdateCategoryUseCaseImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void update(@Nullable CategoryEditInfo categoryEditInfo) {
        if (categoryEditInfo == null) {
            throw UseCaseError.errCategoryEditInfoIsRequired();
        }

        var id = CategoryID.from(categoryEditInfo.getCategoryID());
        var name = Name.of(categoryEditInfo.getName());
        var color = Color.valueOf(categoryEditInfo.getColor());
        var parentID = CategoryID.from(categoryEditInfo.getParentID());

        var category = new CategoryBuilder().
                setID(id).
                setName(name).
                setColor(color).
                setParentID(parentID).
                build();

        categoryRepository.update(category);
    }
}
