package kz.arabro.planogram.nomenclature.domain.usecase;

import jakarta.annotation.Nullable;
import kz.arabro.planogram.nomenclature.boundary.repository.BrandRepository;
import kz.arabro.planogram.nomenclature.boundary.usecase.BrandDeleteUseCase;
import kz.arabro.planogram.nomenclature.domain.entity.BrandID;
import org.springframework.stereotype.Service;

@Service
public class BrandDeleteUseCaseImpl implements BrandDeleteUseCase {

    private final BrandRepository brandRepository;

    public BrandDeleteUseCaseImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public void deleteBrandByID(@Nullable String brandID) {
        if (brandID == null){
            throw UseCaseError.errBrandIdIsRequired();
        }

        brandRepository.deleteById(BrandID.from(brandID));
    }
}
