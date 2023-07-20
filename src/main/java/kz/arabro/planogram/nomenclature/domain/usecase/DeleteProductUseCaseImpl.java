package kz.arabro.planogram.nomenclature.domain.usecase;

import kz.arabro.planogram.nomenclature.boundary.repository.ProductRepository;
import kz.arabro.planogram.nomenclature.boundary.usecase.DeleteProductUseCase;
import kz.arabro.planogram.nomenclature.domain.entity.product.ProductID;
import org.springframework.stereotype.Service;

@Service
public class DeleteProductUseCaseImpl implements DeleteProductUseCase {

    private final ProductRepository productRepository;

    public DeleteProductUseCaseImpl(ProductRepository productRepository) {
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
