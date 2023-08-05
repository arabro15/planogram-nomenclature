package kz.arabro.planogram.nomenclature.domain.usecase;

import kz.arabro.planogram.nomenclature.boundary.repository.BrandRepository;
import kz.arabro.planogram.nomenclature.boundary.usecase.ReadDataBrandUseCase;
import kz.arabro.planogram.nomenclature.domain.entity.brand.Brand;
import kz.arabro.planogram.nomenclature.domain.entity.brand.BrandID;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadDataBrandUseCaseImpl implements ReadDataBrandUseCase {

    private final BrandRepository brandRepository;

    public ReadDataBrandUseCaseImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public Brand findByID(String brandIDStr) {
        var brandID = BrandID.from(brandIDStr);
        var brandOpt = brandRepository.findByID(brandID);

        return brandOpt.orElseThrow(()
                -> UseCaseError.errBrandNotFound(brandID));
    }

    @Override
    public List<Brand> findAll() {
        return brandRepository.findAll();
    }
}
