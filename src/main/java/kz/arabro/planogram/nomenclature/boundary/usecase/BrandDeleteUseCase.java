package kz.arabro.planogram.nomenclature.boundary.usecase;

import kz.arabro.planogram.nomenclature.domain.entity.BrandID;

public interface BrandDeleteUseCase {

    void deleteBrandByID(BrandID brandID);
}
