package kz.arabro.planogram.nomenclature.domain.usecase;

import kz.arabro.planogram.nomenclature.boundary.model.ProductCreateInfo;
import kz.arabro.planogram.nomenclature.boundary.repository.BrandRepository;
import kz.arabro.planogram.nomenclature.boundary.repository.CategoryRepository;
import kz.arabro.planogram.nomenclature.boundary.repository.ProducerRepository;
import kz.arabro.planogram.nomenclature.boundary.repository.ProductRepository;
import kz.arabro.planogram.nomenclature.boundary.usecase.ProductCreatorUseCase;
import kz.arabro.planogram.nomenclature.domain.entity.*;
import org.springframework.stereotype.Service;

@Service
public class ProductCreatorUseCaseImpl implements ProductCreatorUseCase {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final ProducerRepository producerRepository;

    public ProductCreatorUseCaseImpl(ProductRepository productRepository, CategoryRepository categoryRepository, BrandRepository brandRepository, ProducerRepository producerRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
        this.producerRepository = producerRepository;
    }

    @Override
    public Product execute(ProductCreateInfo info) {
        if (info == null) {
            throw UseCaseError.errProductCreateInfoIsRequired();
        }

        var code1C = info.getCode1C();
        var rusName = Name.of(info.getRusName());
        var kazName = Name.of(info.getKazName());

        var categoryOpt = categoryRepository.findByID(CategoryID.from(info.getCategory()));
        var brandOpt = brandRepository.findByID(BrandID.from(info.getBrand()));
        var producerOpt = producerRepository.findByID(ProducerID.from(info.getProducer()));

        Category category = null;
        Brand brand = null;
        Producer producer = null;

        if (categoryOpt.isPresent()) {
            category = categoryOpt.get();
        }
        if (brandOpt.isPresent()) {
            brand = brandOpt.get();
        }
        if (producerOpt.isPresent()) {
            producer = producerOpt.get();
        }

        var barcode = Barcode.of(info.getBarcode());
        var price = Price.of(info.getPrice());
        var height = Integer.parseInt(info.getHeight());
        var weight = Integer.parseInt(info.getWeight());
        var length = Integer.parseInt(info.getLength());
        var size = Size.of(height, weight, length);
        var imagePath = info.getImagePath();

        var product = new ProductBuilder().
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
                createProduct();

        productRepository.save(product);
        return product;
    }
}
