package kz.arabro.planogram.nomenclature.domain.usecase;

import jakarta.annotation.Nullable;
import kz.arabro.planogram.nomenclature.boundary.model.BrandCreateInfo;
import kz.arabro.planogram.nomenclature.boundary.repository.BrandRepository;
import kz.arabro.planogram.nomenclature.boundary.usecase.CreateBrandUseCase;
import kz.arabro.planogram.nomenclature.domain.entity.brand.Brand;
import kz.arabro.planogram.nomenclature.domain.entity.brand.BrandBuilder;
import kz.arabro.planogram.nomenclature.domain.entity.brand.BrandID;
import kz.arabro.planogram.nomenclature.domain.entity.product.Name;
import org.springframework.stereotype.Service;

@Service
public class CreateBrandUseCaseImpl implements CreateBrandUseCase {

    private final BrandRepository brandRepository;

    public CreateBrandUseCaseImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public Brand execute(@Nullable BrandCreateInfo info) {
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
