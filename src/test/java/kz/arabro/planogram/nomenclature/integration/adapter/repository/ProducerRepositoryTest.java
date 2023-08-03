package kz.arabro.planogram.nomenclature.integration.adapter.repository;

import kz.arabro.planogram.nomenclature.adapter.repository.jpa.ProducerDao;
import kz.arabro.planogram.nomenclature.boundary.repository.ProducerRepository;
import kz.arabro.planogram.nomenclature.domain.entity.producer.Producer;
import kz.arabro.planogram.nomenclature.domain.entity.producer.ProducerID;
import kz.arabro.planogram.nomenclature.testdouble.entity.ProducerStub;
import kz.arabro.planogram.nomenclature.util.annotation.IntegrationTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@IntegrationTest
class ProducerRepositoryTest {

    @Autowired
    private ProducerRepository producerRepository;

    @Autowired
    private ProducerDao producerDao;

    private static final Producer PRODUCER = ProducerStub.getProducer();

    @BeforeEach
    void setUp() {
        producerRepository.save(PRODUCER);
    }

    @AfterEach
    void tearDown() {
        producerDao.deleteAll();
    }

    @Test
    void update_ProducerUpdated() {
        var producerToUpdate = ProducerStub.getProducer();
        producerRepository.save(producerToUpdate);

        var producerFromDBOpt = producerRepository.findByID(producerToUpdate.getId());
        assertTrue(producerFromDBOpt.isPresent());
        assertNotEquals(producerToUpdate, producerFromDBOpt.get());

        producerRepository.update(producerToUpdate);

        producerFromDBOpt = producerRepository.findByID(producerToUpdate.getId());
        assertTrue(producerFromDBOpt.isPresent());
        assertEquals(producerToUpdate.getId().getValue(), producerFromDBOpt.get().getId().getValue());
    }

    @Test
    void findByID_ProducerNotExist_ReturnEmptyOptional() {
        var producerOpt = producerRepository.findByID(ProducerID.newID());
        assertTrue(producerOpt.isEmpty());
    }

    @Test
    void findByID_ProducerExist_ReturnProducer() {
        var producerOpt = producerRepository.findByID(PRODUCER.getId());
        assertTrue(producerOpt.isPresent());

        var producerFromDB = producerOpt.get();
        assertEquals(PRODUCER.getId().getValue(), producerFromDB.getId().getValue());
    }

    @Test
    void findAll_ReturnAllBrands() {
        var count = 10;
        var producersToSave = ProducerStub.getProducers(count);
        producersToSave.forEach(producerRepository::save);

        var producersFromDB = producerRepository.findAll();
        assertNotNull(producersFromDB);
        assertFalse(producersFromDB.isEmpty());

        producersFromDB.forEach(Assertions::assertNotNull);
    }

}