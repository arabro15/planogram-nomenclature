package kz.arabro.planogram.nomenclature.adapter.repository;

import kz.arabro.planogram.nomenclature.adapter.repository.converter.ProductConverter;
import kz.arabro.planogram.nomenclature.adapter.repository.jpa.BrandDao;
import kz.arabro.planogram.nomenclature.adapter.repository.jpa.CategoryDao;
import kz.arabro.planogram.nomenclature.adapter.repository.jpa.ProducerDao;
import kz.arabro.planogram.nomenclature.adapter.repository.jpa.ProductDao;
import kz.arabro.planogram.nomenclature.boundary.repository.ProductRepository;
import kz.arabro.planogram.nomenclature.domain.entity.*;
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
        save(product);
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
    public List<Product> findAllByProducer(ProducerID producerID) {
        if (producerID == null) {
            throw RepositoryError.errProducerIdIsRequired();
        }

        var producerDbModel = producerDao.findById(producerID.getValue());

        if (producerDbModel.isEmpty()) {
            throw RepositoryError.errProducerDbModelIsRequired();
        }

        var productDbModels = productDao.findAllByProducer(producerDbModel.get());
        return ProductConverter.toEntities(productDbModels);
    }

    @Transactional
    @Override
    public List<Product> findAllByCategory(CategoryID categoryID) {
        if (categoryID == null) {
            throw RepositoryError.errCategoryIdIsRequired();
        }

        var categoryDbModel = categoryDao.findById(categoryID.getValue());

        if (categoryDbModel.isEmpty()) {
            throw RepositoryError.errCategoryDbModelIsRequired();
        }

        var productDbModels = productDao.findAllByCategory(categoryDbModel.get());
        return ProductConverter.toEntities(productDbModels);
    }

    @Transactional
    @Override
    public List<Product> findAllByBrand(BrandID brandID) {
        if (brandID == null) {
            throw RepositoryError.errBrandIdIsRequired();
        }

        var brandDbModel = brandDao.findById(brandID.getValue());

        if (brandDbModel.isEmpty()) {
            throw RepositoryError.errBrandDbModelIsRequired();
        }

        var productDbModels = productDao.findAllByBrand(brandDbModel.get());
        return ProductConverter.toEntities(productDbModels);
    }

}
