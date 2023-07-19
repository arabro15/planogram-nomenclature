package kz.arabro.planogram.nomenclature.domain.entity;

public class BrandBuilder {
    private BrandID id;
    private Name name;

    // CR: пустой конструктор также используется как конструктор по умолчанию.
    // Его нет необходимости указывать в классе, он неявно присутствует.
    // Его есть необходимость указать, только если присутствует какой-либо другой конструктор.
    // Предлагаю его убрать
    public BrandBuilder() {
    }

    public BrandBuilder setID(BrandID id) {
        this.id = id;
        return this;
    }

    public BrandBuilder setName(Name name) {
        this.name = name;
        return this;
    }

    public Brand build() {
        checkRequiredFields();

        return new Brand(id, name);
    }

    private void checkRequiredFields() {
        if (id == null) {
            throw BrandError.errNullIdBrandValue();
        }
        if(name == null) {
            throw BrandError.errNullNameBrandValue();
        }
    }
}
