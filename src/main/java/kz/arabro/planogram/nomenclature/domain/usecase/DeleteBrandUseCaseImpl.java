package kz.arabro.planogram.nomenclature.domain.usecase;

import jakarta.annotation.Nullable;
import kz.arabro.planogram.nomenclature.boundary.repository.BrandRepository;
import kz.arabro.planogram.nomenclature.boundary.usecase.DeleteBrandUseCase;
import kz.arabro.planogram.nomenclature.domain.entity.brand.BrandID;
import org.springframework.stereotype.Service;

// CR: Правильно будет DeleteBrandUseCase.
// Сначала идет глагол, а потом объект действия
@Service
public class DeleteBrandUseCaseImpl implements DeleteBrandUseCase {

    private final BrandRepository brandRepository;

    public DeleteBrandUseCaseImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public void deleteBrandByID(@Nullable String brandIDStr) {
        if (brandIDStr == null){
            throw UseCaseError.errBrandIdIsRequired();
        }

        var brandOpt = brandRepository.findByID(BrandID.from(brandIDStr));
        if (brandOpt.isPresent()) {
            var brandID = brandOpt.get().getId();
            brandRepository.deleteById(brandID);
        }
    }
}
