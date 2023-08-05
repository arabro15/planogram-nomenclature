package kz.arabro.planogram.nomenclature.boundary.usecase;

import kz.arabro.planogram.nomenclature.boundary.model.CategoryCreateInfo;
import kz.arabro.planogram.nomenclature.domain.entity.category.Category;

public interface CreateCategoryUseCase {

    Category execute(CategoryCreateInfo info);

}
