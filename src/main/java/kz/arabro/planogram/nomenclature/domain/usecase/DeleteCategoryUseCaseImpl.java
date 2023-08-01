package kz.arabro.planogram.nomenclature.domain.usecase;

import kz.arabro.planogram.nomenclature.boundary.repository.CategoryRepository;
import kz.arabro.planogram.nomenclature.boundary.usecase.DeleteCategoryUseCase;
import kz.arabro.planogram.nomenclature.domain.entity.category.CategoryID;
import org.springframework.stereotype.Service;

// CR: Данный класс можно назвать глаголом.
// UseCase чем-то напоминает паттерн Команда.
// Поэтому предлагаю переименовать в DeleteCategoryUseCase
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

        //CR: на мой взгляд, CategoryID.from(categoryID) лучше вынести в отдельную переменную.
        // Так код будет более читабельный
        var categoryID = CategoryID.from(categoryIDStr);
        categoryRepository.deleteById(categoryID);

    }

    //CR: У категории может быть несколько дочерних категорий?
    // Если так, то тут могут быть удалены несколько категорий.
    // Это противоречит названию метода. Возможно стоит назвать deleteCategoriesByParentID
    @Override
    public void deleteCategoriesByParentID(String parentIDStr) {
        if (parentIDStr == null) {
            throw UseCaseError.errCategoryParentIdIsRequired();
        }

        var parentID = CategoryID.from(parentIDStr);
        categoryRepository.deleteGroupCategoryByParentId(parentID);
    }
}
