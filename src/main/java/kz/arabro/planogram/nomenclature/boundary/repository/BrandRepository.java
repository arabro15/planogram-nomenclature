package kz.arabro.planogram.nomenclature.boundary.repository;

import kz.arabro.planogram.nomenclature.domain.entity.Brand;
import kz.arabro.planogram.nomenclature.domain.entity.BrandID;

import java.util.List;
import java.util.Optional;

public interface BrandRepository {

    void save(Brand brand);
    void deleteById(BrandID brandID);
    void update(Brand brand);
    Optional<Brand> findByID(BrandID brandID);
    List<Brand> findAll();

}
