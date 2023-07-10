package kz.arabro.planogram.nomenclature.domain.usecase;

import kz.arabro.planogram.nomenclature.boundary.model.BrandCreateInfo;
import kz.arabro.planogram.nomenclature.boundary.repository.BrandRepository;
import kz.arabro.planogram.nomenclature.boundary.usecase.BrandCreatorUseCase;
import kz.arabro.planogram.nomenclature.domain.entity.Brand;
import kz.arabro.planogram.nomenclature.domain.entity.BrandBuilder;
import kz.arabro.planogram.nomenclature.domain.entity.BrandID;
import kz.arabro.planogram.nomenclature.domain.entity.Name;

public class BrandCreatorUseCaseImpl implements BrandCreatorUseCase {

    private final BrandRepository brandRepository;

    public BrandCreatorUseCaseImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public Brand execute(BrandCreateInfo info) {
        if (info == null) {
            throw UseCaseError.errBrandCreateInfoIsRequired();
        }
        var brand = new BrandBuilder().
                setID(BrandID.newID()).
                setName(Name.of(info.getName())).
                build();

        brandRepository.save(brand);
        return brand;
    }
}
