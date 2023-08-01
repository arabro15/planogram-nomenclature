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
    public void deleteProductByID(String productIDStr) {
        if (productIDStr == null) {
            throw UseCaseError.errProductIDIsRequired();
        }

        var productID = ProductID.from(productIDStr);
        productRepository.deleteById(productID);
    }
}
