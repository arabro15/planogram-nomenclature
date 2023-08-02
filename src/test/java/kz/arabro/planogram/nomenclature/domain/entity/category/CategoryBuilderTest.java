package kz.arabro.planogram.nomenclature.domain.entity.category;

import kz.arabro.planogram.nomenclature.domain.exception.CodedException;
import kz.arabro.planogram.nomenclature.testdouble.entity.NameStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryBuilderTest {

    @Test
    void build_CategoryIDIsNull_ThrowEx() {
        var name = NameStub.getName();
        var color = Color.RED;
        var parentID = CategoryID.newID();
        var builder = new CategoryBuilder().
                setName(name).
                setColor(color).
                setParentID(parentID);

        var ex = assertThrows(CodedException.class, builder::build);
        assertEquals(CategoryError.NULL_ID_CATEGORY_VALUE, ex.getCode());
    }

    @Test
    void build_NameIsNull_ThrowEx() {
        var categoryID = CategoryID.newID();
        var color = Color.RED;
        var parentID = CategoryID.newID();
        var builder = new CategoryBuilder().
                setID(categoryID).
                setColor(color).
                setParentID(parentID);

        var ex = assertThrows(CodedException.class, builder::build);
        assertEquals(CategoryError.NULL_NAME_CATEGORY_VALUE, ex.getCode());
    }

    @Test
    void build_ColorIsNull_ThrowEx() {
        var categoryID = CategoryID.newID();
        var name = NameStub.getName();
        var parentID = CategoryID.newID();
        var builder = new CategoryBuilder().
                setID(categoryID).
                setName(name).
                setParentID(parentID);

        var ex = assertThrows(CodedException.class, builder::build);
        assertEquals(CategoryError.NULL_COLOR_CATEGORY_VALUE, ex.getCode());
    }

    @Test
    void build_ParentIDIsNull_ReturnCategoryWithEmptyParentID() {
        var categoryID = CategoryID.newID();
        var name = NameStub.getName();
        var color = Color.BLACK;

        var category = new CategoryBuilder().
                setID(categoryID).
                setName(name).
                setColor(color).
                setParentID(null).
                build();

        assertNotNull(category);
        assertEquals(categoryID, category.getId());
        assertEquals(name, category.getName());
        assertEquals(color, category.getColor());
        assertFalse(category.getParentID().isPresent());
    }

    @Test
    void build_AllParamsIsValid_ReturnCategoryWithParentID() {
        var categoryID = CategoryID.newID();
        var name = NameStub.getName();
        var color = Color.BLACK;
        var parentID = CategoryID.newID();

        var category = new CategoryBuilder().
                setID(categoryID).
                setName(name).
                setColor(color).
                setParentID(parentID).
                build();

        assertNotNull(category);
        assertEquals(categoryID, category.getId());
        assertEquals(name, category.getName());
        assertEquals(color, category.getColor());
        assertTrue(category.getParentID().isPresent());
        assertEquals(parentID, category.getParentID().get());
    }






}
