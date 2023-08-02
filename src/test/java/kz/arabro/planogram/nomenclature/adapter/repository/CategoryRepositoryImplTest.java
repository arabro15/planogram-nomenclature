package kz.arabro.planogram.nomenclature.adapter.repository;

import kz.arabro.planogram.nomenclature.adapter.repository.jpa.CategoryDao;
import kz.arabro.planogram.nomenclature.adapter.repository.model.CategoryDbModel;
import kz.arabro.planogram.nomenclature.boundary.repository.CategoryRepository;
import kz.arabro.planogram.nomenclature.domain.entity.category.CategoryID;
import kz.arabro.planogram.nomenclature.domain.exception.CodedException;
import kz.arabro.planogram.nomenclature.testdouble.entity.CategoryStub;
import kz.arabro.planogram.nomenclature.testdouble.repository.CategoryDbModelStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryRepositoryImplTest {

    @Mock
    private CategoryDao categoryDao;

    @Captor
    private ArgumentCaptor<CategoryDbModel> categoryDbModelCaptor;

    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        this.categoryRepository = new CategoryRepositoryImpl(categoryDao);
    }

    @Test
    void save_CategoryIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> categoryRepository.save(null));
        assertEquals(RepositoryError.CATEGORY_IS_REQUIRED, ex.getCode());
    }

    @Test
    void save_ValueIsValid_SaveCategory() {
        var category = CategoryStub.getCategory();

        categoryRepository.save(category);
        verify(categoryDao).save(categoryDbModelCaptor.capture());

        var categoryDbModel = categoryDbModelCaptor.getValue();

        var categoryID = category.getId().getValue();
        var categoryName = category.getName().getValue();
        var categoryColor = category.getColor().name();
        var categoryParentIDOpt = category.getParentID();

        var categoryDbModelID = categoryDbModel.getId();
        var categoryDbModelName = categoryDbModel.getName();
        var categoryDbModelColor = categoryDbModel.getColor();
        var categoryDbModelParentID = categoryDbModel.getParentID();

        assertNotNull(categoryDbModel);
        assertEquals(categoryID, categoryDbModelID);
        assertEquals(categoryName, categoryDbModelName);
        assertEquals(categoryColor, categoryDbModelColor);

        if (categoryParentIDOpt.isPresent()) {
            if (categoryDbModelParentID != null) {
                var categoryParentID = categoryParentIDOpt.get().getValue();
                assertEquals(categoryParentID, categoryDbModelParentID);
            }
        }
    }

    @Test
    void deleteById_CategoryIDIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> categoryRepository.deleteById(null));
        assertEquals(RepositoryError.CATEGORY_ID_IS_REQUIRED, ex.getCode());
    }

    @Test
    void deleteByID_ValueIsValid_DeleteCategory() {
        var categoryID = CategoryStub.getCategory().getId();
        var categoryIDValue = categoryID.getValue();

        categoryRepository.deleteById(categoryID);
        verify(categoryDao, times(1)).deleteById(categoryIDValue);
    }

    @Test
    void deleteCategoriesByParentId_ParentIDIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> categoryRepository.deleteCategoriesByParentId(null));
        assertEquals(RepositoryError.CATEGORY_PARENT_ID_IS_REQUIRED, ex.getCode());
    }

    @Test
    void deleteCategoriesByParentId_ValueIsValid_DeleteCategoriesByParentID() {
        var parentID = CategoryID.newID();
        var parentIDValue = parentID.getValue();

        categoryRepository.deleteCategoriesByParentId(parentID);
        verify(categoryDao, times(1)).deleteByParentID(parentIDValue);
    }

    @Test
    void update_CategoryIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> categoryRepository.update(null));
        assertEquals(RepositoryError.CATEGORY_IS_REQUIRED, ex.getCode());
    }

    @Test
    void update_ValueIsValid_UpdateCategory() {
        var category = CategoryStub.getCategory();

        var categoryIDValue = category.getId().getValue();
        var categoryNameStr = category.getName().getValue();
        var categoryColorStr = category.getColor().name();
        var categoryParentIDOpt = category.getParentID();

        categoryRepository.update(category);
        if (categoryParentIDOpt.isPresent()) {
            var categoryParentID = categoryParentIDOpt.get().getValue();
            verify(categoryDao).updateById(categoryIDValue, categoryNameStr,categoryColorStr, categoryParentID);
        }
        if (categoryParentIDOpt.isEmpty()) {
            verify(categoryDao).updateById(categoryIDValue, categoryNameStr,categoryColorStr, null);
        }
    }

    @Test
    void findByID_CategoryIDIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> categoryRepository.findByID(null));
        assertEquals(RepositoryError.CATEGORY_ID_IS_REQUIRED, ex.getCode());
    }

    @Test
    void findByID_ValueIsValid_ReturnCategory() {
        var categoryDbModel = CategoryDbModelStub.getCategoryDbModel();
        when(categoryDao.findById(any(UUID.class))).thenReturn(Optional.of(categoryDbModel));

        var categoryOpt = categoryRepository.findByID(CategoryID.newID());
        assertNotNull(categoryOpt);
        assertTrue(categoryOpt.isPresent());

        assertEquals(categoryDbModel.getId(), categoryOpt.get().getId().getValue());
    }

    @Test
    void findByParentID_ParentIDIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> categoryRepository.findByParentID(null));
        assertEquals(RepositoryError.CATEGORY_PARENT_ID_IS_REQUIRED, ex.getCode());
    }

    @Test
    void findByParentID_ValueIsValid_ReturnCategory() {
        var count = 5;

        var categoryDbModels = CategoryDbModelStub.getCategoryDbModels(count);
        when(categoryDao.findByParentID(any(UUID.class))).thenReturn(categoryDbModels);

        var parentID = CategoryID.newID();

        var categories = categoryRepository.findByParentID(parentID);

        assertNotNull(categories);

        for (int i = 0; i < count; i++) {
            var categoryDbModelID = categoryDbModels.get(i).getId();
            var categoryDbModelName = categoryDbModels.get(i).getName();
            var categoryDbModelColor = categoryDbModels.get(i).getColor();
            var categoryDbModelParentID = categoryDbModels.get(i).getParentID();

            var categoryID = categories.get(i).getId().getValue();
            var categoryName = categories.get(i).getName().getValue();
            var categoryColor = categories.get(i).getColor().name();
            var categoryParentOpt = categories.get(i).getParentID();

            assertEquals(categoryDbModelID, categoryID);
            assertEquals(categoryDbModelName, categoryName);
            assertEquals(categoryDbModelColor, categoryColor);

            assertTrue(categoryParentOpt.isPresent());
            assertEquals(categoryDbModelParentID, categoryParentOpt.get().getValue());
        }
    }

    @Test
    void findAll_NotParams_ReturnCategories() {
        var count = 5;

        var categoryDbModels = CategoryDbModelStub.getCategoryDbModels(count);
        when(categoryDao.findAll()).thenReturn(categoryDbModels);

        var categories = categoryRepository.findAll();
        assertNotNull(categories);
        assertEquals(categoryDbModels.size(), categories.size());

        for (int i = 0; i < count; i++) {
            var categoryDbModelID = categoryDbModels.get(i).getId();
            var categoryDbModelName = categoryDbModels.get(i).getName();
            var categoryDbModelColor = categoryDbModels.get(i).getColor();
            var categoryDbModelParentID = categoryDbModels.get(i).getParentID();

            var categoryID = categories.get(i).getId().getValue();
            var categoryName = categories.get(i).getName().getValue();
            var categoryColor = categories.get(i).getColor().name();
            var categoryParentOpt = categories.get(i).getParentID();

            assertEquals(categoryDbModelID, categoryID);
            assertEquals(categoryDbModelName, categoryName);
            assertEquals(categoryDbModelColor, categoryColor);

            assertTrue(categoryParentOpt.isPresent());
            assertEquals(categoryDbModelParentID, categoryParentOpt.get().getValue());
        }
    }




}