package kz.arabro.planogram.nomenclature.boundary.usecase;

import kz.arabro.planogram.nomenclature.domain.entity.Brand;
import kz.arabro.planogram.nomenclature.domain.entity.BrandID;

import java.util.List;

public interface BrandReadDataUseCase {

    Brand findByID(BrandID brandID);
    List<Brand> findAll();
}
