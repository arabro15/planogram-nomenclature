package kz.arabro.planogram.nomenclature.boundary.usecase;

import kz.arabro.planogram.nomenclature.boundary.model.CategoryCreateInfo;
import kz.arabro.planogram.nomenclature.domain.entity.Category;

public interface CategoryCreatorUseCase {

    Category execute(CategoryCreateInfo info);

}
