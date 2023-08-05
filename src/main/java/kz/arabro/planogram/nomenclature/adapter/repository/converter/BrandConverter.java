package kz.arabro.planogram.nomenclature.adapter.repository.converter;

import kz.arabro.planogram.nomenclature.adapter.repository.RepositoryError;
import kz.arabro.planogram.nomenclature.adapter.repository.model.BrandDbModel;
import kz.arabro.planogram.nomenclature.domain.entity.brand.Brand;
import kz.arabro.planogram.nomenclature.domain.entity.brand.BrandBuilder;
import kz.arabro.planogram.nomenclature.domain.entity.brand.BrandID;
import kz.arabro.planogram.nomenclature.domain.entity.product.Name;

import java.util.List;

public final class BrandConverter {

    public static Brand toEntity(BrandDbModel brandDbModel) {
        if(brandDbModel == null) {
            throw RepositoryError.errBrandDbModelIsRequired();
        }

        var id = BrandID.from(brandDbModel.getId().toString());
        var name = Name.of(brandDbModel.getName());

        return new BrandBuilder().
                setID(id).
                setName(name).
                build();
    }

    public static List<Brand> toEntities(List<BrandDbModel> brandDbModels) {
        if(brandDbModels == null) {
            throw RepositoryError.errBrandDbModelsIsRequired();
        }

        return brandDbModels.stream().
                map(BrandConverter::toEntity).
                toList();
    }

    public static BrandDbModel toModel(Brand brand) {
        if(brand == null) {
            throw RepositoryError.errBrandIsRequired();
        }

        var id = brand.getId().getValue();
        var name = brand.getName().getValue();

        var brandDbModel = new BrandDbModel();
        brandDbModel.setId(id);
        brandDbModel.setName(name);

        return brandDbModel;
    }

    public static List<BrandDbModel> toModels(List<Brand> brands) {
        if(brands == null) {
            throw RepositoryError.errBrandsIsRequired();
        }

        return brands.stream().
                map(BrandConverter::toModel).
                toList();
    }

    private BrandConverter() {}
}
