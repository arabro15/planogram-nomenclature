package kz.arabro.planogram.nomenclature.boundary.usecase;

import kz.arabro.planogram.nomenclature.domain.entity.Brand;

import java.util.List;

public interface BrandReadDataUseCase {

    Brand findByID(String brandID);
    List<Brand> findAll();
}
