package kz.arabro.planogram.nomenclature.boundary.usecase;

import kz.arabro.planogram.nomenclature.domain.entity.brand.Brand;

import java.util.List;

public interface ReadDataBrandUseCase {

    Brand findByID(String brandID);
    List<Brand> findAll();
}
