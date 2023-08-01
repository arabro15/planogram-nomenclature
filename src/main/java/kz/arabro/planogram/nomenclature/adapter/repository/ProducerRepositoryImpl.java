package kz.arabro.planogram.nomenclature.adapter.repository;

import kz.arabro.planogram.nomenclature.adapter.repository.converter.ProducerConverter;
import kz.arabro.planogram.nomenclature.adapter.repository.jpa.ProducerDao;
import kz.arabro.planogram.nomenclature.boundary.repository.ProducerRepository;
import kz.arabro.planogram.nomenclature.domain.entity.producer.Producer;
import kz.arabro.planogram.nomenclature.domain.entity.producer.ProducerID;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class ProducerRepositoryImpl implements ProducerRepository {

    private final ProducerDao producerDao;

    public ProducerRepositoryImpl(ProducerDao producerDao) {
        this.producerDao = producerDao;
    }

    @Transactional
    @Override
    public void save(Producer producer) {
        if (producer == null) {
            throw RepositoryError.errProducerIsRequired();
        }

        var producerDbModel = ProducerConverter.toModel(producer);
        producerDao.save(producerDbModel);
    }

    @Transactional
    @Override
    public void deleteById(ProducerID producerID) {
        if (producerID == null) {
            throw RepositoryError.errProducerIdIsRequired();
        }
        producerDao.deleteById(producerID.getValue());
    }

    @Transactional
    @Override
    public void update(Producer producer) {
        if (producer == null) {
            throw RepositoryError.errProducerIsRequired();
        }

        var id = producer.getId().getValue();
        var name = producer.getName().getValue();

        producerDao.updateById(id, name);
    }

    @Transactional
    @Override
    public Optional<Producer> findByID(ProducerID producerID) {
        if (producerID == null) {
            throw RepositoryError.errProducerIdIsRequired();
        }
        return producerDao.findById(producerID.getValue()).
                map(ProducerConverter::toEntity);
    }

    @Transactional
    @Override
    public List<Producer> findAll() {
        var producerDbModels = producerDao.findAll();
        return ProducerConverter.toEntities(producerDbModels);
    }
}
