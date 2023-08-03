package kz.arabro.planogram.nomenclature.testdouble.controller;

import kz.arabro.planogram.nomenclature.adapter.controller.request.*;
import kz.arabro.planogram.nomenclature.domain.entity.brand.BrandID;
import kz.arabro.planogram.nomenclature.domain.entity.category.CategoryID;
import kz.arabro.planogram.nomenclature.domain.entity.producer.ProducerID;
import kz.arabro.planogram.nomenclature.domain.entity.product.ProductID;
import kz.arabro.planogram.nomenclature.testdouble.entity.BarcodeStub;
import kz.arabro.planogram.nomenclature.testdouble.entity.NameStub;
import kz.arabro.planogram.nomenclature.testdouble.entity.PriceStub;
import kz.arabro.planogram.nomenclature.testdouble.entity.SizeStub;

public class ProductRequestStub {

    public static CreateProductRequest getCreateProductRequest() {
        var createProductRequest = new CreateProductRequest();
        createProductRequest.setCode1C("123456789");
        createProductRequest.setKazName(NameStub.getName().getValue());
        createProductRequest.setRusName(NameStub.getName().getValue());
        createProductRequest.setCategoryID(CategoryID.newID().getValue().toString());
        createProductRequest.setBrandID(BrandID.newID().getValue().toString());
        createProductRequest.setProducerID(ProducerID.newID().getValue().toString());
        createProductRequest.setBarcode(BarcodeStub.getBarcode().getValue());
        createProductRequest.setPrice(PriceStub.getPrice().getValue());
        createProductRequest.setHeight(String.valueOf(SizeStub.getSize().getHeight()));
        createProductRequest.setWeight(String.valueOf(SizeStub.getSize().getWeight()));
        createProductRequest.setLength(String.valueOf(SizeStub.getSize().getLength()));
        createProductRequest.setImagePath("path");

        return createProductRequest;
    }

    public static DeleteProductRequest getDeleteProductRequest() {
        var deleteProductRequest = new DeleteProductRequest();
        deleteProductRequest.setProductID(ProductID.newID().getValue().toString());

        return deleteProductRequest;
    }

    public static EditProductRequest getEditProductRequest() {
        var editProductRequest = new EditProductRequest();
        editProductRequest.setProducerID(ProductID.newID().getValue().toString());
        editProductRequest.setCode1C("123456789");
        editProductRequest.setKazName(NameStub.getName().getValue());
        editProductRequest.setRusName(NameStub.getName().getValue());
        editProductRequest.setCategoryID(CategoryID.newID().getValue().toString());
        editProductRequest.setBrandID(BrandID.newID().getValue().toString());
        editProductRequest.setProducerID(ProducerID.newID().getValue().toString());
        editProductRequest.setBarcode(BarcodeStub.getBarcode().getValue());
        editProductRequest.setPrice(PriceStub.getPrice().getValue());
        editProductRequest.setHeight(String.valueOf(SizeStub.getSize().getHeight()));
        editProductRequest.setWeight(String.valueOf(SizeStub.getSize().getWeight()));
        editProductRequest.setLength(String.valueOf(SizeStub.getSize().getLength()));
        editProductRequest.setImagePath("path");

        return editProductRequest;
    }

    public static GetProductByIDRequest getProductByIDRequest() {
        var getProductByIDRequest = new GetProductByIDRequest();
        getProductByIDRequest.setProductID(ProductID.newID().getValue().toString());

        return getProductByIDRequest;
    }

    public static GetProductByCode1CRequest getProductByCode1CRequest() {
        var getProductByCode1CRequest = new GetProductByCode1CRequest();
        getProductByCode1CRequest.setCode1C("123456789");

        return getProductByCode1CRequest;
    }

    public static GetProductsByProducerIDRequest getProductsByProducerIDRequest() {
        var getProductsByProducerIDRequest = new GetProductsByProducerIDRequest();
        getProductsByProducerIDRequest.setProducerID(ProducerID.newID().getValue().toString());

        return getProductsByProducerIDRequest;
    }

    public static GetProductsByCategoryIDRequest getProductsByCategoryIDRequest() {
        var getProductsByCategoryIDRequest = new GetProductsByCategoryIDRequest();
        getProductsByCategoryIDRequest.setCategoryID(CategoryID.newID().getValue().toString());

        return getProductsByCategoryIDRequest;
    }

    public static GetProductsByBrandIDRequest getProductsByBrandIDRequest() {
        var getProductsByBrandIDRequest = new GetProductsByBrandIDRequest();
        getProductsByBrandIDRequest.setBrandID(BrandID.newID().getValue().toString());

        return getProductsByBrandIDRequest;
    }


}
