package kz.arabro.planogram.nomenclature.boundary.usecase;

import kz.arabro.planogram.nomenclature.boundary.model.BrandCreateInfo;
import kz.arabro.planogram.nomenclature.domain.entity.Brand;

public interface BrandCreatorUseCase {

    Brand execute(BrandCreateInfo info);

}
