package kz.arabro.planogram.nomenclature.domain.usecase;

import kz.arabro.planogram.nomenclature.boundary.repository.BrandRepository;
import kz.arabro.planogram.nomenclature.boundary.usecase.BrandEditorUseCase;
import kz.arabro.planogram.nomenclature.domain.entity.Brand;

public class BrandEditorUseCaseImpl implements BrandEditorUseCase {

    private final BrandRepository brandRepository;

    public BrandEditorUseCaseImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public void update(Brand brand) {
        if (brand == null){
            throw UseCaseError.errBrandCreateInfoIsRequired();
        }

        brandRepository.update(brand);
    }
}
