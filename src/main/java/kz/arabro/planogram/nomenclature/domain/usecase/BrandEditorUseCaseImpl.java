package kz.arabro.planogram.nomenclature.domain.usecase;

import jakarta.annotation.Nullable;
import kz.arabro.planogram.nomenclature.boundary.model.BrandEditInfo;
import kz.arabro.planogram.nomenclature.boundary.repository.BrandRepository;
import kz.arabro.planogram.nomenclature.boundary.usecase.BrandEditorUseCase;
import kz.arabro.planogram.nomenclature.domain.entity.BrandBuilder;
import kz.arabro.planogram.nomenclature.domain.entity.BrandID;
import kz.arabro.planogram.nomenclature.domain.entity.Name;
import org.springframework.stereotype.Service;

@Service
public class BrandEditorUseCaseImpl implements BrandEditorUseCase {

    private final BrandRepository brandRepository;

    public BrandEditorUseCaseImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public void update(@Nullable BrandEditInfo brandEditInfo) {
        if (brandEditInfo == null){
            throw UseCaseError.errBrandCreateInfoIsRequired();
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
