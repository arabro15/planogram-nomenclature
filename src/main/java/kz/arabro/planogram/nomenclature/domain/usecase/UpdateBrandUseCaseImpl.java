package kz.arabro.planogram.nomenclature.domain.usecase;

import jakarta.annotation.Nullable;
import kz.arabro.planogram.nomenclature.boundary.model.BrandEditInfo;
import kz.arabro.planogram.nomenclature.boundary.repository.BrandRepository;
import kz.arabro.planogram.nomenclature.boundary.usecase.UpdateBrandUseCase;
import kz.arabro.planogram.nomenclature.domain.entity.brand.BrandBuilder;
import kz.arabro.planogram.nomenclature.domain.entity.brand.BrandID;
import kz.arabro.planogram.nomenclature.domain.entity.product.Name;
import org.springframework.stereotype.Service;

@Service
public class UpdateBrandUseCaseImpl implements UpdateBrandUseCase {

    private final BrandRepository brandRepository;

    public UpdateBrandUseCaseImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public void update(@Nullable BrandEditInfo brandEditInfo) {
        if (brandEditInfo == null) {
            throw UseCaseError.errBrandEditInfoIsRequired();
        }

        var brandID = BrandID.from(brandEditInfo.getBrandID());
        var name = Name.of(brandEditInfo.getName());

        var brand = new BrandBuilder().
                setID(brandID).
                setName(name).
                build();

        brandRepository.update(brand);
    }
}
