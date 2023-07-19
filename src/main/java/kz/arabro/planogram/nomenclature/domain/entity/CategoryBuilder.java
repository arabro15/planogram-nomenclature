package kz.arabro.planogram.nomenclature.domain.entity;

public class CategoryBuilder {
    private CategoryID id;
    private Name name;
    private Color color;
    private CategoryID parentID;

    // CR: пустой конструктор также используется как конструктор по умолчанию.
    // Его нет необходимости указывать в классе, он неявно присутствует.
    // Его есть необходимость указать, только если присутствует какой-либо другой конструктор.
    // Предлагаю его убрать
    public CategoryBuilder() {}

    public CategoryBuilder setID(CategoryID id) {
        this.id = id;
        return this;
    }

    public CategoryBuilder setName(Name name) {
        this.name = name;
        return this;
    }

    public CategoryBuilder setColor(Color color) {
        this.color = color;
        return this;
    }

    public CategoryBuilder setParentID(CategoryID parentID) {
        this.parentID = parentID;
        return this;
    }

    public Category build() {
        checkRequiredFields();
        return new Category(id, name, color, parentID);
    }

    public void checkRequiredFields() {
        if (id == null) {
            throw CategoryError.errNullIdCategoryValue();
        }
        if(name == null) {
            throw CategoryError.errNullNameCategoryValue();
        }
        if(color == null) {
            throw CategoryError.errNullColorCategoryValue();
        }
    }
}

