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

// CR: Данный класс можно назвать глаголом.
// UseCase чем-то напоминает паттерн Команда.
// Поэтому предлагаю переименовать в CreateCategoryUseCase
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
