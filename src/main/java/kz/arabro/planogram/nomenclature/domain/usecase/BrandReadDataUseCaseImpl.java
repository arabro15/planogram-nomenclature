package kz.arabro.planogram.nomenclature.domain.usecase;

import kz.arabro.planogram.nomenclature.boundary.repository.BrandRepository;
import kz.arabro.planogram.nomenclature.boundary.usecase.BrandReadDataUseCase;
import kz.arabro.planogram.nomenclature.domain.entity.Brand;
import kz.arabro.planogram.nomenclature.domain.entity.BrandID;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandReadDataUseCaseImpl implements BrandReadDataUseCase {

    private final BrandRepository brandRepository;

    public BrandReadDataUseCaseImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public Brand findByID(String brandIDStr) {
        var brandID = BrandID.from(brandIDStr);
        var brandOpt = brandRepository.findByID(brandID);

        return brandOpt.orElseThrow(() -> UseCaseError.errBrandNotFound(brandID));
    }

    @Override
    public List<Brand> findAll() {
        return brandRepository.findAll();
    }
}
