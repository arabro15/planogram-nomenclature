package kz.arabro.planogram.nomenclature.domain.usecase;

import kz.arabro.planogram.nomenclature.boundary.repository.CategoryRepository;
import kz.arabro.planogram.nomenclature.boundary.usecase.CategoryDeleteUseCase;
import kz.arabro.planogram.nomenclature.domain.entity.CategoryID;
import org.springframework.stereotype.Service;

// CR: Данный класс можно назвать глаголом.
// UseCase чем-то напоминает паттерн Команда.
// Поэтому предлагаю переименовать в DeleteCategoryUseCase
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

        //CR: на мой взгляд, CategoryID.from(categoryID) лучше вынести в отдельную переменную.
        // Так код будет более читабельный
        categoryRepository.deleteById(CategoryID.from(categoryID));

    }

    //CR: У категории может быть несколько дочерних категорий?
    // Если так, то тут могут быть удалены несколько категорий.
    // Это противоречит названию метода. Возможно стоит назвать deleteCategoriesByParentID
    @Override
    public void deleteCategoryByParentID(String parentID) {
        if (parentID == null) {
            throw UseCaseError.errCategoryParentIdIsRequired();
        }

        categoryRepository.deleteGroupCategoryByParentId(CategoryID.from(parentID));
    }
}
