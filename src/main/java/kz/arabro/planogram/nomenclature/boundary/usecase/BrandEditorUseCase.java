package kz.arabro.planogram.nomenclature.boundary.usecase;

import kz.arabro.planogram.nomenclature.domain.entity.Brand;

public interface BrandEditorUseCase {

    Brand execute(Brand currentBrand, Brand targetBrand);
}
