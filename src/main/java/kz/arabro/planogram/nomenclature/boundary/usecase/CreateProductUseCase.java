package kz.arabro.planogram.nomenclature.boundary.usecase;

import kz.arabro.planogram.nomenclature.boundary.model.ProductCreateInfo;
import kz.arabro.planogram.nomenclature.domain.entity.product.Product;

public interface CreateProductUseCase {
    Product execute(ProductCreateInfo info);
}
