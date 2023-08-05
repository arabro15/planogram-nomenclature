package kz.arabro.planogram.nomenclature.domain.usecase;

import kz.arabro.planogram.nomenclature.boundary.model.ProductCreateInfo;
import kz.arabro.planogram.nomenclature.boundary.repository.BrandRepository;
import kz.arabro.planogram.nomenclature.boundary.repository.CategoryRepository;
import kz.arabro.planogram.nomenclature.boundary.repository.ProducerRepository;
import kz.arabro.planogram.nomenclature.boundary.repository.ProductRepository;
import kz.arabro.planogram.nomenclature.boundary.usecase.CreateProductUseCase;
import kz.arabro.planogram.nomenclature.domain.entity.brand.Brand;
import kz.arabro.planogram.nomenclature.domain.entity.brand.BrandID;
import kz.arabro.planogram.nomenclature.domain.entity.category.Category;
import kz.arabro.planogram.nomenclature.domain.entity.category.CategoryID;
import kz.arabro.planogram.nomenclature.domain.entity.producer.Producer;
import kz.arabro.planogram.nomenclature.domain.entity.producer.ProducerID;
import kz.arabro.planogram.nomenclature.domain.entity.product.*;
import org.springframework.stereotype.Service;

@Service
public class CreateProductUseCaseImpl implements CreateProductUseCase {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final ProducerRepository producerRepository;

    public CreateProductUseCaseImpl(ProductRepository productRepository,
                                    CategoryRepository categoryRepository,
                                    BrandRepository brandRepository,
                                    ProducerRepository producerRepository) {
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
        var height = Integer.parseInt(info.getHeight());
        var weight = Integer.parseInt(info.getWeight());
        var length = Integer.parseInt(info.getLength());
        var size = Size.of(height, weight, length);

        var productBuilder = new ProductBuilder().
                setProductID(ProductID.newID()).
                setCode1C(code1C).
                setRusName(rusName).
                setKazName(kazName).
                setCategory(category).
                setBrand(brand).
                setProducer(producer).
                setBarcode(barcode).
                setSize(size);

        if (info.getPrice() != null) {
            var price = Price.of(info.getPrice());
            productBuilder.setPrice(price);
        }
        if (info.getImagePath() != null) {
            var imagePath = info.getImagePath();
            productBuilder.setImagePath(imagePath);
        }

        var product = productBuilder.build();

        productRepository.save(product);

        return product;
    }
}
