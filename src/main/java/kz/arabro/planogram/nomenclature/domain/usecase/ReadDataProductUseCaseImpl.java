package kz.arabro.planogram.nomenclature.domain.usecase;

import kz.arabro.planogram.nomenclature.boundary.repository.ProductRepository;
import kz.arabro.planogram.nomenclature.boundary.usecase.ReadDataProductUseCase;
import kz.arabro.planogram.nomenclature.domain.entity.brand.BrandID;
import kz.arabro.planogram.nomenclature.domain.entity.category.CategoryID;
import kz.arabro.planogram.nomenclature.domain.entity.producer.ProducerID;
import kz.arabro.planogram.nomenclature.domain.entity.product.Product;
import kz.arabro.planogram.nomenclature.domain.entity.product.ProductID;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadDataProductUseCaseImpl implements ReadDataProductUseCase {

    private final ProductRepository productRepository;

    public ReadDataProductUseCaseImpl(ProductRepository productRepository) {
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
        if (code1C == null) {
            throw UseCaseError.errProductCode1cIsRequired();
        }

        var productOpt = productRepository.findByCode1C(code1C);

        return productOpt.
                orElseThrow(() -> UseCaseError.errProductByCode1CNotFound(code1C));
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findByProducer(String producerIDStr) {
        if (producerIDStr == null) {
            throw UseCaseError.errProducerIDIsRequired();
        }

        var producerID = ProducerID.from(producerIDStr);

        return productRepository.findByProducer(producerID);
    }

    @Override
    public List<Product> findByCategory(String categoryIDStr) {
        if (categoryIDStr == null) {
            throw UseCaseError.errCategoryIdIsRequired();
        }

        var categoryID = CategoryID.from(categoryIDStr);

        return productRepository.findByCategory(categoryID);
    }

    @Override
    public List<Product> findByBrand(String brandIDStr) {
        if (brandIDStr == null) {
            throw UseCaseError.errBrandIdIsRequired();
        }

        var brandID = BrandID.from(brandIDStr);

        return productRepository.findByBrand(brandID);
    }
}
