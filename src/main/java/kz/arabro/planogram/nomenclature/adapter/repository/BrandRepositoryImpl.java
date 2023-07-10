package kz.arabro.planogram.nomenclature.adapter.repository;

import kz.arabro.planogram.nomenclature.adapter.repository.converter.BrandConverter;
import kz.arabro.planogram.nomenclature.adapter.repository.jpa.BrandDao;
import kz.arabro.planogram.nomenclature.boundary.repository.BrandRepository;
import kz.arabro.planogram.nomenclature.domain.entity.Brand;
import kz.arabro.planogram.nomenclature.domain.entity.BrandID;

import java.util.List;
import java.util.Optional;

public class BrandRepositoryImpl implements BrandRepository {

    private final BrandDao brandDao;

    public BrandRepositoryImpl(BrandDao brandDao) {
        this.brandDao = brandDao;
    }

    @Override
    public void save(Brand brand) {
        if (brand == null) {
            throw RepositoryError.errBrandIsRequired();
        }

        var brandDbModel = BrandConverter.toModel(brand);
        brandDao.save(brandDbModel);
    }

    @Override
    public void deleteById(BrandID brandID) {
        if (brandID == null) {
            throw RepositoryError.errBrandIdIsRequired();
        }
        brandDao.deleteById(brandID.getValue());
    }

    @Override
    public void update(Brand brand) {
        save(brand);
    }

    @Override
    public Optional<Brand> findByID(BrandID brandID) {
        if (brandID == null) {
            throw RepositoryError.errBrandIdIsRequired();
        }

        return brandDao.findById(brandID.getValue()).
                map(BrandConverter::toEntity);
    }

    @Override
    public List<Brand> findAll() {
        var brandDbModels = brandDao.findAll();
        return BrandConverter.toEntities(brandDbModels);
    }
}
