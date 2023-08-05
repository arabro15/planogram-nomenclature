package kz.arabro.planogram.nomenclature.domain.usecase;

import kz.arabro.planogram.nomenclature.boundary.model.ProductEditInfo;
import kz.arabro.planogram.nomenclature.boundary.repository.BrandRepository;
import kz.arabro.planogram.nomenclature.boundary.repository.CategoryRepository;
import kz.arabro.planogram.nomenclature.boundary.repository.ProducerRepository;
import kz.arabro.planogram.nomenclature.boundary.repository.ProductRepository;
import kz.arabro.planogram.nomenclature.boundary.usecase.UpdateProductUseCase;
import kz.arabro.planogram.nomenclature.domain.entity.brand.BrandID;
import kz.arabro.planogram.nomenclature.domain.entity.category.CategoryID;
import kz.arabro.planogram.nomenclature.domain.entity.producer.ProducerID;
import kz.arabro.planogram.nomenclature.domain.entity.product.*;
import org.springframework.stereotype.Service;

@Service
public class UpdateProductUseCaseImpl implements UpdateProductUseCase {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final ProducerRepository producerRepository;

    public UpdateProductUseCaseImpl(ProductRepository productRepository,
                                    CategoryRepository categoryRepository,
                                    BrandRepository brandRepository,
                                    ProducerRepository producerRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
        this.producerRepository = producerRepository;
    }

    @Override
    public void update(ProductEditInfo info) {
        if (info == null) {
            throw UseCaseError.errProductEditInfoIsRequired();
        }

        var categoryOpt = categoryRepository.findByID(CategoryID.from(info.getCategory()));
        var brandOpt = brandRepository.findByID(BrandID.from(info.getBrand()));
        var producerOpt = producerRepository.findByID(ProducerID.from(info.getProducer()));

        if (categoryOpt.isEmpty()) {
            throw UseCaseError.errCategoryNotFound(CategoryID.from(info.getCategory()));
        }
        if (brandOpt.isEmpty()) {
            throw UseCaseError.errBrandNotFound(BrandID.from(info.getBrand()));
        }
        if (producerOpt.isEmpty()) {
            throw UseCaseError.errProducerNotFound(ProducerID.from(info.getProducer()));
        }

        var productID = ProductID.from(info.getId());
        var code1C = info.getCode1C();
        var rusName = Name.of(info.getRusName());
        var kazName = Name.of(info.getKazName());
        var category = categoryOpt.get();
        var brand = brandOpt.get();
        var producer = producerOpt.get();
        var barcode = Barcode.of(info.getBarcode());
        var price = Price.of(info.getPrice());
        var height = Integer.parseInt(info.getHeight());
        var weight = Integer.parseInt(info.getWeight());
        var length = Integer.parseInt(info.getLength());
        var size = Size.of(height, weight, length);
        var imagePath = info.getImagePath();

        var product = new ProductBuilder().
                setProductID(productID).
                setProductID(ProductID.newID()).
                setCode1C(code1C).
                setRusName(rusName).
                setKazName(kazName).
                setCategory(category).
                setBrand(brand).
                setProducer(producer).
                setBarcode(barcode).
                setPrice(price).
                setSize(size).
                setImagePath(imagePath).
                build();

        productRepository.update(product);
    }
}
