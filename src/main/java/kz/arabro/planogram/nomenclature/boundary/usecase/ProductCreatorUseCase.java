package kz.arabro.planogram.nomenclature.boundary.usecase;

import kz.arabro.planogram.nomenclature.boundary.model.ProductCreateInfo;
import kz.arabro.planogram.nomenclature.domain.entity.Product;

public interface ProductCreatorUseCase {
    Product execute(ProductCreateInfo info);
}
