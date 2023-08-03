package kz.arabro.planogram.nomenclature.integration.adapter.repository;

import kz.arabro.planogram.nomenclature.adapter.repository.jpa.CategoryDao;
import kz.arabro.planogram.nomenclature.boundary.repository.CategoryRepository;
import kz.arabro.planogram.nomenclature.domain.entity.category.Category;
import kz.arabro.planogram.nomenclature.domain.entity.category.CategoryID;
import kz.arabro.planogram.nomenclature.testdouble.entity.CategoryStub;
import kz.arabro.planogram.nomenclature.util.annotation.IntegrationTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@IntegrationTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryDao categoryDao;

    private static final Category CATEGORY = CategoryStub.getCategory();

    @BeforeEach
    void setUp() {
        categoryRepository.save(CATEGORY);
    }

    @AfterEach
    void tearDown() {
        categoryDao.deleteAll();
    }

    @Test
    void update_CategoryUpdated() {
        var categoryToUpdate = CategoryStub.getCategoryByParentID();
        categoryRepository.save(categoryToUpdate);

        var categoryFromDBOpt = categoryRepository.findByID(categoryToUpdate.getId());
        assertTrue(categoryFromDBOpt.isPresent());
        assertNotEquals(categoryToUpdate, categoryFromDBOpt.get());

        categoryRepository.update(categoryToUpdate);

        categoryFromDBOpt = categoryRepository.findByID(categoryToUpdate.getId());
        assertTrue(categoryFromDBOpt.isPresent());
        assertEquals(categoryToUpdate.getId().getValue(), categoryFromDBOpt.get().getId().getValue());
    }

    @Test
    void findByID_CategoryNotExist_ReturnEmptyOptional() {
        var categoryOpt = categoryRepository.findByID(CategoryID.newID());
        assertTrue(categoryOpt.isEmpty());
    }

    @Test
    void findByID_CategoryExist_ReturnCategory() {
        var categoryOpt = categoryRepository.findByID(CATEGORY.getId());
        assertTrue(categoryOpt.isPresent());

        var categoryFromDB = categoryOpt.get();
        assertEquals(CATEGORY.getId().getValue(), categoryFromDB.getId().getValue());
    }

    @Test
    void findByParentID_CategoriesByParentIDNotExist_ReturnEmptyOptional() {
        var parentID = CategoryID.newID();

        var categoryOpt = categoryRepository.findByParentID(parentID);
        assertTrue(categoryOpt.isEmpty());
    }

    @Test
    void findByParentID_CategoriesByParentIDExist_ReturnCategoriesByParentID() {
        var parentIDOpt = CategoryStub.getCategoryByParentID().getParentID();
        assertTrue(parentIDOpt.isPresent());
        var parentID = parentIDOpt.get();

        var categoriesByParentID = CategoryStub.getCategoriesByParentID(10);
        categoriesByParentID.forEach(categoryRepository::save);

        var categoriesByParentIDFromDb = categoryRepository.findByParentID(parentID);

        categoriesByParentIDFromDb.forEach(category -> {
            assertNotNull(category);

            var parentIDFromDbOpt = category.getParentID();
            assertTrue(parentIDFromDbOpt.isPresent());
            var parentIDFromDb = parentIDFromDbOpt.get();

            assertEquals(parentID.getValue(), parentIDFromDb.getValue());
        });
    }

    @Test
    void findAll_ReturnAllCategories() {
        var count = 10;
        var categoriesToSave = CategoryStub.getCategories(count);
        categoriesToSave.forEach(categoryRepository::save);

        var categoriesFromDB = categoryRepository.findAll();
        assertNotNull(categoriesFromDB);
        assertFalse(categoriesFromDB.isEmpty());

        categoriesFromDB.forEach(Assertions::assertNotNull);
    }
}