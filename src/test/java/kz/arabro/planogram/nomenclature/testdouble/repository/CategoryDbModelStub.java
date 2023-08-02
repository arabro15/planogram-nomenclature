package kz.arabro.planogram.nomenclature.testdouble.repository;

import kz.arabro.planogram.nomenclature.adapter.repository.model.CategoryDbModel;
import kz.arabro.planogram.nomenclature.testdouble.entity.CategoryStub;

import java.util.ArrayList;
import java.util.List;

public class CategoryDbModelStub {

    public static CategoryDbModel getCategoryDbModel() {
        var category = CategoryStub.getCategory();

        var id = category.getId().getValue();
        var name = category.getName().getValue();
        var color = category.getColor().toString();
        var parentIDOpt = category.getParentID();

        var categoryDbModel = new CategoryDbModel();
        categoryDbModel.setId(id);
        categoryDbModel.setName(name);
        categoryDbModel.setColor(color);

        if (parentIDOpt.isPresent()){
            var parentID = parentIDOpt.get().getValue();
            categoryDbModel.setParentID(parentID);
        }

        return categoryDbModel;
    }

    public static List<CategoryDbModel> getCategoryDbModels(int count) {
        var categoryDbModels = new ArrayList<CategoryDbModel>(count);

        for (int i = 0; i < count; i++) {
            categoryDbModels.add(getCategoryDbModel());
        }

        return categoryDbModels;
    }
}
