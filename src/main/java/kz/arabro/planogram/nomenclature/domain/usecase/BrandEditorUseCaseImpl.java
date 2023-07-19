package kz.arabro.planogram.nomenclature.domain.usecase;

import jakarta.annotation.Nullable;
import kz.arabro.planogram.nomenclature.boundary.model.BrandEditInfo;
import kz.arabro.planogram.nomenclature.boundary.repository.BrandRepository;
import kz.arabro.planogram.nomenclature.boundary.usecase.BrandEditorUseCase;
import kz.arabro.planogram.nomenclature.domain.entity.BrandBuilder;
import kz.arabro.planogram.nomenclature.domain.entity.BrandID;
import kz.arabro.planogram.nomenclature.domain.entity.Name;
import org.springframework.stereotype.Service;

// CR: Данный класс можно назвать глаголом.
// UseCase чем-то напоминает паттерн Команда.
// Поэтому предлагаю переименовать в UpdateBrandUseCase
@Service
public class BrandEditorUseCaseImpl implements BrandEditorUseCase {

    private final BrandRepository brandRepository;

    public BrandEditorUseCaseImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    // CR: Не понял, что делает данный UseCase. Он создает или обновляет?
    // Если обновляет, то почему создается новый Brand?
    // Почему не достается Brand из репозитория для обновления?
    // Пока, я вижу, что у Brand можно заменить ID, а это очень странно
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
