package kz.arabro.planogram.nomenclature.domain.usecase;

import kz.arabro.planogram.nomenclature.boundary.repository.BrandRepository;
import kz.arabro.planogram.nomenclature.boundary.usecase.CreateBrandUseCase;
import kz.arabro.planogram.nomenclature.domain.exception.CodedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CreateBrandUseCaseImplTest {

    @Mock
    private BrandRepository brandRepository;

    private CreateBrandUseCase createBrandUseCase;

    @BeforeEach
    void setUp() {
        this.createBrandUseCase = new CreateBrandUseCaseImpl(brandRepository);
    }

    @Test
    void execute_InfoIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> createBrandUseCase.execute(null));
        assertEquals(UseCaseError.BRAND_CREATE_INFO_IS_REQUIRED, ex.getCode());
    }




}