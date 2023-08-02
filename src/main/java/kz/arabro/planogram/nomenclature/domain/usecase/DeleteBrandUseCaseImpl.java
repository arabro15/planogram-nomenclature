package kz.arabro.planogram.nomenclature.domain.usecase;

import jakarta.annotation.Nullable;
import kz.arabro.planogram.nomenclature.boundary.repository.BrandRepository;
import kz.arabro.planogram.nomenclature.boundary.usecase.DeleteBrandUseCase;
import kz.arabro.planogram.nomenclature.domain.entity.brand.BrandID;
import org.springframework.stereotype.Service;

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

        brandRepository.deleteById(BrandID.from(brandIDStr));
    }
}
