package kz.arabro.planogram.nomenclature.domain.usecase;

import kz.arabro.planogram.nomenclature.boundary.repository.BrandRepository;
import kz.arabro.planogram.nomenclature.boundary.usecase.BrandDeleteUseCase;
import kz.arabro.planogram.nomenclature.domain.entity.BrandID;

public class BrandDeleteUseCaseImpl implements BrandDeleteUseCase {

    private final BrandRepository brandRepository;

    public BrandDeleteUseCaseImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public void deleteBrandByID(BrandID brandID) {
        if (brandID == null){
            throw UseCaseError.errBrandIdIsRequired();
        }

        brandRepository.deleteById(brandID);
    }
}
