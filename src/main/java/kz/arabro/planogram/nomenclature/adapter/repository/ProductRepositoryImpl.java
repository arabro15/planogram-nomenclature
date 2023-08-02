package kz.arabro.planogram.nomenclature.adapter.repository;

import kz.arabro.planogram.nomenclature.adapter.repository.converter.BrandConverter;
import kz.arabro.planogram.nomenclature.adapter.repository.converter.CategoryConverter;
import kz.arabro.planogram.nomenclature.adapter.repository.converter.ProducerConverter;
import kz.arabro.planogram.nomenclature.adapter.repository.converter.ProductConverter;
import kz.arabro.planogram.nomenclature.adapter.repository.jpa.BrandDao;
import kz.arabro.planogram.nomenclature.adapter.repository.jpa.CategoryDao;
import kz.arabro.planogram.nomenclature.adapter.repository.jpa.ProducerDao;
import kz.arabro.planogram.nomenclature.adapter.repository.jpa.ProductDao;
import kz.arabro.planogram.nomenclature.boundary.repository.ProductRepository;
import kz.arabro.planogram.nomenclature.domain.entity.brand.BrandID;
import kz.arabro.planogram.nomenclature.domain.entity.category.CategoryID;
import kz.arabro.planogram.nomenclature.domain.entity.producer.ProducerID;
import kz.arabro.planogram.nomenclature.domain.entity.product.Product;
import kz.arabro.planogram.nomenclature.domain.entity.product.ProductID;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductDao productDao;
    private final ProducerDao producerDao;
    private final CategoryDao categoryDao;
    private final BrandDao brandDao;

    public ProductRepositoryImpl(ProductDao productDao,
                                 ProducerDao producerDao,
                                 CategoryDao categoryDao,
                                 BrandDao brandDao) {
        this.productDao = productDao;
        this.producerDao = producerDao;
        this.categoryDao = categoryDao;
        this.brandDao = brandDao;
    }

    @Transactional
    @Override
    public void save(Product product) {
        if (product == null) {
            throw RepositoryError.errProductIsRequired();
        }

        var productDbModel = ProductConverter.toModel(product);
        productDao.save(productDbModel);
    }

    @Transactional
    @Override
    public void deleteById(ProductID productID) {
        if (productID == null) {
            throw RepositoryError.errProductIdIsRequired();
        }
        productDao.deleteById(productID.getValue());
    }

    @Transactional
    @Override
    public void update(Product product) {
        if (product == null) {
            throw RepositoryError.errProductIsRequired();
        }

        var id = product.getProductID().getValue();
        var code1c = product.getCode1C();
        var rusName = product.getRusName().getValue();
        var kazName = product.getKazName().getValue();
        var category = CategoryConverter.toModel(product.getCategory());
        var brand = BrandConverter.toModel(product.getBrand());
        var producer = ProducerConverter.toModel(product.getProducer());
        var barcode = product.getBarcode().getValue();
        var price = product.getPrice().getValue();
        var height = String.valueOf(product.getSize().getHeight());
        var weight = String.valueOf(product.getSize().getWeight());
        var length = String.valueOf(product.getSize().getLength());
        var imagePath = product.getImagePath();

        productDao.updateById(
                id,
                code1c,
                rusName,
                kazName,
                category,
                brand,
                producer,
                barcode,
                price,
                height,
                weight,
                length,
                imagePath
        );
    }

    @Transactional
    @Override
    public Optional<Product> findByID(ProductID productID) {
        if (productID == null) {
            throw RepositoryError.errProductIdIsRequired();
        }
        return productDao.findById(productID.getValue()).
                map(ProductConverter::toEntity);
    }

    @Transactional
    @Override
    public Optional<Product> findByCode1C(String code1C) {
        if (code1C == null) {
            throw RepositoryError.errProductCode1CIsRequired();
        }

        return productDao.findByCode1C(code1C).
                map(ProductConverter::toEntity);
    }

    @Transactional
    @Override
    public List<Product> findAll() {
        var productDbModels = productDao.findAll();
        return ProductConverter.toEntities(productDbModels);
    }

    @Transactional
    @Override
    public List<Product> findByProducer(ProducerID producerID) {
        if (producerID == null) {
            throw RepositoryError.errProducerIdIsRequired();
        }

        var producerDbModel = producerDao.findById(producerID.getValue());

        if (producerDbModel.isEmpty()) {
            throw RepositoryError.errProducerDbModelIsRequired();
        }

        var productDbModels = productDao.findByProducer(producerDbModel.get());
        return ProductConverter.toEntities(productDbModels);
    }

    @Transactional
    @Override
    public List<Product> findByCategory(CategoryID categoryID) {
        if (categoryID == null) {
            throw RepositoryError.errCategoryIdIsRequired();
        }

        var categoryDbModel = categoryDao.findById(categoryID.getValue());

        if (categoryDbModel.isEmpty()) {
            throw RepositoryError.errCategoryDbModelIsRequired();
        }

        var productDbModels = productDao.findByCategory(categoryDbModel.get());
        return ProductConverter.toEntities(productDbModels);
    }

    @Transactional
    @Override
    public List<Product> findByBrand(BrandID brandID) {
        if (brandID == null) {
            throw RepositoryError.errBrandIdIsRequired();
        }

        var brandDbModel = brandDao.findById(brandID.getValue());

        if (brandDbModel.isEmpty()) {
            throw RepositoryError.errBrandDbModelIsRequired();
        }

        var productDbModels = productDao.findByBrand(brandDbModel.get());
        return ProductConverter.toEntities(productDbModels);
    }

}
