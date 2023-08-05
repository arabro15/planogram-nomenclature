package kz.arabro.planogram.nomenclature.testdouble.repository;

import kz.arabro.planogram.nomenclature.adapter.repository.model.BrandDbModel;
import kz.arabro.planogram.nomenclature.testdouble.entity.BrandStub;

import java.util.ArrayList;
import java.util.List;

public class BrandDbModelStub {

    public static BrandDbModel getBrandDbModel() {
        var brand = BrandStub.getBrand();

        var id = brand.getId().getValue();
        var name = brand.getName().getValue();

        var brandDbModel = new BrandDbModel();
        brandDbModel.setId(id);
        brandDbModel.setName(name);

        return brandDbModel;
    }

    public static List<BrandDbModel> getBrandDbModels(int count) {
        var brandDbModels = new ArrayList<BrandDbModel>(count);

        for (int i = 0; i < count; i++) {
            brandDbModels.add(getBrandDbModel());
        }

        return brandDbModels;
    }
}
