package kz.arabro.planogram.nomenclature.boundary.usecase;

import kz.arabro.planogram.nomenclature.boundary.model.BrandCreateInfo;
import kz.arabro.planogram.nomenclature.domain.entity.brand.Brand;

public interface CreateBrandUseCase {

    Brand execute(BrandCreateInfo info);

}
