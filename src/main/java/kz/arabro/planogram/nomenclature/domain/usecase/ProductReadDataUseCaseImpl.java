package kz.arabro.planogram.nomenclature.domain.usecase;

import kz.arabro.planogram.nomenclature.boundary.repository.ProductRepository;
import kz.arabro.planogram.nomenclature.boundary.usecase.ProductReadDataUseCase;
import kz.arabro.planogram.nomenclature.domain.entity.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductReadDataUseCaseImpl implements ProductReadDataUseCase {

    private final ProductRepository productRepository;

    public ProductReadDataUseCaseImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product findByID(String productIDStr) {
        var productID = ProductID.from(productIDStr);
        var productOpt = productRepository.findByID(productID);
        return productOpt.
                orElseThrow(() -> UseCaseError.errProductNotFound(productID));
    }

    @Override
    public Product findByCode1C(String code1C) {
        var productOpt = productRepository.findByCode1C(code1C);
        return productOpt.
                orElseThrow(() -> UseCaseError.errProductByCode1CNotFound(code1C));
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findAllByProducer(String producerIDStr) {
        if (producerIDStr == null) {
            throw UseCaseError.errProducerIDIsRequired();
        }
        var producerID = ProducerID.from(producerIDStr);

        return productRepository.findAllByProducer(producerID);
    }

    @Override
    public List<Product> findAllByCategory(String categoryIDStr) {
        if (categoryIDStr == null) {
            throw UseCaseError.errCategoryIdIsRequired();
        }
        var categoryID = CategoryID.from(categoryIDStr);

        return productRepository.findAllByCategory(categoryID);
    }

    @Override
    public List<Product> findAllByBrand(String brandIDStr) {
        if (brandIDStr == null) {
            throw UseCaseError.errBrandIdIsRequired();
        }
        var brandID = BrandID.from(brandIDStr);

        return productRepository.findAllByBrand(brandID);
    }
}
