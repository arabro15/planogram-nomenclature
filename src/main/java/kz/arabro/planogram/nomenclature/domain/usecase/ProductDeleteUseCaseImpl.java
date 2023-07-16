package kz.arabro.planogram.nomenclature.domain.usecase;

import kz.arabro.planogram.nomenclature.boundary.repository.ProductRepository;
import kz.arabro.planogram.nomenclature.boundary.usecase.ProductDeleteUseCase;
import kz.arabro.planogram.nomenclature.domain.entity.ProductID;
import org.springframework.stereotype.Service;

@Service
public class ProductDeleteUseCaseImpl implements ProductDeleteUseCase {

    private final ProductRepository productRepository;

    public ProductDeleteUseCaseImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void deleteProductByID(String productID) {
        if (productID == null) {
            throw UseCaseError.errProductIDIsRequired();
        }

        productRepository.deleteById(ProductID.from(productID));
    }
}
