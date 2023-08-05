package kz.arabro.planogram.nomenclature.adapter.repository;

import kz.arabro.planogram.nomenclature.adapter.repository.converter.BrandConverter;
import kz.arabro.planogram.nomenclature.adapter.repository.jpa.BrandDao;
import kz.arabro.planogram.nomenclature.boundary.repository.BrandRepository;
import kz.arabro.planogram.nomenclature.domain.entity.brand.Brand;
import kz.arabro.planogram.nomenclature.domain.entity.brand.BrandID;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class BrandRepositoryImpl implements BrandRepository {

    private final BrandDao brandDao;

    public BrandRepositoryImpl(BrandDao brandDao) {
        this.brandDao = brandDao;
    }

    @Transactional
    @Override
    public void save(Brand brand) {
        if (brand == null) {
            throw RepositoryError.errBrandIsRequired();
        }

        var brandDbModel = BrandConverter.toModel(brand);
        brandDao.save(brandDbModel);
    }

    @Transactional
    @Override
    public void deleteById(BrandID brandID) {
        if (brandID == null) {
            throw RepositoryError.errBrandIdIsRequired();
        }

        brandDao.deleteById(brandID.getValue());
    }

    @Transactional
    @Override
    public void update(Brand brand) {
        if (brand == null) {
            throw RepositoryError.errBrandIsRequired();
        }

        var id = brand.getId().getValue();
        var name = brand.getName().getValue();

        brandDao.updateById(id, name);
    }

    @Transactional
    @Override
    public Optional<Brand> findByID(BrandID brandID) {
        if (brandID == null) {
            throw RepositoryError.errBrandIdIsRequired();
        }

        return brandDao.findById(brandID.getValue()).
                map(BrandConverter::toEntity);
    }

    @Transactional
    @Override
    public List<Brand> findAll() {
        var brandDbModels = brandDao.findAll();
        return BrandConverter.toEntities(brandDbModels);
    }

    @Transactional
    @Override
    public boolean existsById(BrandID brandID) {
        if (brandID == null) {
            throw RepositoryError.errBrandIdIsRequired();
        }

        return brandDao.existsById(brandID.getValue());
    }
}
