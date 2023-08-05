package kz.arabro.planogram.nomenclature.adapter.repository.converter;

import kz.arabro.planogram.nomenclature.adapter.repository.RepositoryError;
import kz.arabro.planogram.nomenclature.domain.exception.CodedException;
import kz.arabro.planogram.nomenclature.testdouble.entity.ProductStub;
import kz.arabro.planogram.nomenclature.testdouble.repository.ProductDbModelStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductConverterTest {

    @Test
    void toEntity_ProductDbModelIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> ProductConverter.toEntity(null));
        assertEquals(RepositoryError.PRODUCT_DB_MODEL_IS_REQUIRED, ex.getCode());
    }

    @Test
    void toEntity_AllParamsIsValid_ReturnProduct() {
        var productDbModel = ProductDbModelStub.getProductDbModel();
        var product = ProductConverter.toEntity(productDbModel);

        //ProductDbModel - fields
        var productDbModelID = productDbModel.getId();
        var productDbModelCode1c = productDbModel.getCode1C();
        var productDbModelRusName = productDbModel.getRusName();
        var productDbModelKazName = productDbModel.getKazName();

        var categoryDbModel = productDbModel.getCategory();
        var categoryDbModelID = categoryDbModel.getId();
        var categoryDbModelName = categoryDbModel.getName();
        var categoryDbModelColor = categoryDbModel.getColor();
        var categoryDbModelParentID = categoryDbModel.getParentID();

        var brandDbModel = productDbModel.getBrand();
        var brandDbModelID = brandDbModel.getId();
        var brandDbModelName = brandDbModel.getName();

        var producerDbModel = productDbModel.getProducer();
        var producerDbModelID = producerDbModel.getId();
        var producerDbModelName = producerDbModel.getName();

        var productDbModelBarcode = productDbModel.getBarcode();
        var productDbModelPrice = productDbModel.getPrice();
        var productDbModelHeight = productDbModel.getHeight();
        var productDbModelWeight = productDbModel.getWeight();
        var productDbModelLength = productDbModel.getLength();

        var productDbModelImagePath = productDbModel.getImagePath();

        //Product - fields
        var productID = product.getProductID().getValue();
        var productCode1c = product.getCode1C();
        var productRusName = product.getRusName().getValue();
        var productKazName = product.getKazName().getValue();

        var category = product.getCategory();
        var categoryID = category.getId().getValue();
        var categoryName = category.getName().getValue();
        var categoryColor = category.getColor().name();
        var categoryParentIDOpt = category.getParentID();

        var brand = product.getBrand();
        var brandID = brand.getId().getValue();
        var brandName = brand.getName().getValue();

        var producer = product.getProducer();
        var producerID = producer.getId().getValue();
        var producerName = producer.getName().getValue();

        var productBarcode = product.getBarcode().getValue();
        var productPrice = product.getPrice().getValue();

        var productSize = product.getSize();
        var productHeight = String.valueOf(productSize.getHeight());
        var productWeight = String.valueOf(productSize.getWeight());
        var productLength = String.valueOf(productSize.getLength());

        var productImagePath = product.getImagePath();

        //Equals fields
        assertNotNull(product);
        assertEquals(productDbModelID, productID);
        assertEquals(productDbModelCode1c, productCode1c);
        assertEquals(productDbModelRusName, productRusName);
        assertEquals(productDbModelKazName, productKazName);

        assertEquals(categoryDbModelID, categoryID);
        assertEquals(categoryDbModelName, categoryName);
        assertEquals(categoryDbModelColor, categoryColor);

        if (categoryDbModelParentID != null) {
            if (categoryParentIDOpt.isPresent()) {
                var categoryParentID = categoryParentIDOpt.get().getValue();
                assertEquals(categoryDbModelParentID, categoryParentID);
            }
        }

        assertEquals(brandDbModelID, brandID);
        assertEquals(brandDbModelName, brandName);

        assertEquals(producerDbModelID, producerID);
        assertEquals(producerDbModelName, producerName);

        assertEquals(productDbModelBarcode, productBarcode);
        assertEquals(productDbModelPrice, productPrice);
        assertEquals(productDbModelHeight, productHeight);
        assertEquals(productDbModelWeight, productWeight);
        assertEquals(productDbModelLength, productLength);
        assertEquals(productDbModelImagePath, productImagePath);
    }

    @Test
    void toEntities_ProductDbModelsIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> ProductConverter.toEntities(null));
        assertEquals(RepositoryError.PRODUCT_DB_MODELS_IS_REQUIRED, ex.getCode());
    }

    @Test
    void toEntities_AllParamsIsValid_ReturnProductDbModels() {
        var count = 5;

        var productDbModels = ProductDbModelStub.getProductDbModels(count);
        var products = ProductConverter.toEntities(productDbModels);

        assertNotNull(products);

        for (int i = 0; i < count; i++) {
            //ProductDbModel - fields
            var productDbModelID = productDbModels.get(i).getId();
            var productDbModelCode1c = productDbModels.get(i).getCode1C();
            var productDbModelRusName = productDbModels.get(i).getRusName();
            var productDbModelKazName = productDbModels.get(i).getKazName();

            var categoryDbModel = productDbModels.get(i).getCategory();
            var categoryDbModelID = categoryDbModel.getId();
            var categoryDbModelName = categoryDbModel.getName();
            var categoryDbModelColor = categoryDbModel.getColor();
            var categoryDbModelParentID = categoryDbModel.getParentID();

            var brandDbModel = productDbModels.get(i).getBrand();
            var brandDbModelID = brandDbModel.getId();
            var brandDbModelName = brandDbModel.getName();

            var producerDbModel = productDbModels.get(i).getProducer();
            var producerDbModelID = producerDbModel.getId();
            var producerDbModelName = producerDbModel.getName();

            var productDbModelBarcode = productDbModels.get(i).getBarcode();
            var productDbModelPrice = productDbModels.get(i).getPrice();
            var productDbModelHeight = productDbModels.get(i).getHeight();
            var productDbModelWeight = productDbModels.get(i).getWeight();
            var productDbModelLength = productDbModels.get(i).getLength();

            var productDbModelImagePath = productDbModels.get(i).getImagePath();

            //Product - fields
            var productID = products.get(i).getProductID().getValue();
            var productCode1c = products.get(i).getCode1C();
            var productRusName = products.get(i).getRusName().getValue();
            var productKazName = products.get(i).getKazName().getValue();

            var category = products.get(i).getCategory();
            var categoryID = category.getId().getValue();
            var categoryName = category.getName().getValue();
            var categoryColor = category.getColor().name();
            var categoryParentIDOpt = category.getParentID();

            var brand = products.get(i).getBrand();
            var brandID = brand.getId().getValue();
            var brandName = brand.getName().getValue();

            var producer = products.get(i).getProducer();
            var producerID = producer.getId().getValue();
            var producerName = producer.getName().getValue();

            var productBarcode = products.get(i).getBarcode().getValue();
            var productPrice = products.get(i).getPrice().getValue();

            var productSize = products.get(i).getSize();
            var productHeight = String.valueOf(productSize.getHeight());
            var productWeight = String.valueOf(productSize.getWeight());
            var productLength = String.valueOf(productSize.getLength());

            var productImagePath = products.get(i).getImagePath();

            //Equals fields
            assertNotNull(products);
            assertEquals(productDbModelID, productID);
            assertEquals(productDbModelCode1c, productCode1c);
            assertEquals(productDbModelRusName, productRusName);
            assertEquals(productDbModelKazName, productKazName);

            assertEquals(categoryDbModelID, categoryID);
            assertEquals(categoryDbModelName, categoryName);
            assertEquals(categoryDbModelColor, categoryColor);

            if (categoryDbModelParentID != null) {
                if (categoryParentIDOpt.isPresent()) {
                    var categoryParentID = categoryParentIDOpt.get().getValue();
                    assertEquals(categoryDbModelParentID, categoryParentID);
                }
            }

            assertEquals(brandDbModelID, brandID);
            assertEquals(brandDbModelName, brandName);

            assertEquals(producerDbModelID, producerID);
            assertEquals(producerDbModelName, producerName);

            assertEquals(productDbModelBarcode, productBarcode);
            assertEquals(productDbModelPrice, productPrice);
            assertEquals(productDbModelHeight, productHeight);
            assertEquals(productDbModelWeight, productWeight);
            assertEquals(productDbModelLength, productLength);
            assertEquals(productDbModelImagePath, productImagePath);
        }
    }

    @Test
    void toModel_ProductIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> ProductConverter.toModel(null));
        assertEquals(RepositoryError.PRODUCT_IS_REQUIRED, ex.getCode());
    }

    @Test
    void toModel_AllParamsIsValid_ReturnProductDbModel() {
        var product = ProductStub.getProduct();
        var productDbModel = ProductConverter.toModel(product);

        //Product - fields
        var productID = product.getProductID().getValue();
        var productCode1c = product.getCode1C();
        var productRusName = product.getRusName().getValue();
        var productKazName = product.getKazName().getValue();

        var category = product.getCategory();
        var categoryID = category.getId().getValue();
        var categoryName = category.getName().getValue();
        var categoryColor = category.getColor().name();
        var categoryParentIDOpt = category.getParentID();

        var brand = product.getBrand();
        var brandID = brand.getId().getValue();
        var brandName = brand.getName().getValue();

        var producer = product.getProducer();
        var producerID = producer.getId().getValue();
        var producerName = producer.getName().getValue();

        var productBarcode = product.getBarcode().getValue();
        var productPrice = product.getPrice().getValue();

        var productSize = product.getSize();
        var productHeight = String.valueOf(productSize.getHeight());
        var productWeight = String.valueOf(productSize.getWeight());
        var productLength = String.valueOf(productSize.getLength());

        var productImagePath = product.getImagePath();

        //ProductDbModel - fields
        var productDbModelID = productDbModel.getId();
        var productDbModelCode1c = productDbModel.getCode1C();
        var productDbModelRusName = productDbModel.getRusName();
        var productDbModelKazName = productDbModel.getKazName();

        var categoryDbModel = productDbModel.getCategory();
        var categoryDbModelID = categoryDbModel.getId();
        var categoryDbModelName = categoryDbModel.getName();
        var categoryDbModelColor = categoryDbModel.getColor();
        var categoryDbModelParentID = categoryDbModel.getParentID();

        var brandDbModel = productDbModel.getBrand();
        var brandDbModelID = brandDbModel.getId();
        var brandDbModelName = brandDbModel.getName();

        var producerDbModel = productDbModel.getProducer();
        var producerDbModelID = producerDbModel.getId();
        var producerDbModelName = producerDbModel.getName();

        var productDbModelBarcode = productDbModel.getBarcode();
        var productDbModelPrice = productDbModel.getPrice();
        var productDbModelHeight = productDbModel.getHeight();
        var productDbModelWeight = productDbModel.getWeight();
        var productDbModelLength = productDbModel.getLength();

        var productDbModelImagePath = productDbModel.getImagePath();

        //Equals fields
        assertNotNull(productDbModel);
        assertEquals(productID, productDbModelID);
        assertEquals(productCode1c, productDbModelCode1c);
        assertEquals(productRusName, productDbModelRusName);
        assertEquals(productKazName, productDbModelKazName);

        assertEquals(categoryID, categoryDbModelID);
        assertEquals(categoryName, categoryDbModelName);
        assertEquals(categoryColor, categoryDbModelColor);


        if (categoryParentIDOpt.isPresent()) {
            if (categoryDbModelParentID != null) {
                var categoryParentID = categoryParentIDOpt.get().getValue();
                assertEquals(categoryParentID, categoryDbModelParentID);
            }
        }

        assertEquals(brandID, brandDbModelID);
        assertEquals(brandName, brandDbModelName);

        assertEquals(producerID, producerDbModelID);
        assertEquals(producerName, producerDbModelName);

        assertEquals(productBarcode, productDbModelBarcode);
        assertEquals(productPrice, productDbModelPrice);
        assertEquals(productHeight, productDbModelHeight);
        assertEquals(productWeight, productDbModelWeight);
        assertEquals(productLength, productDbModelLength);
        assertEquals(productImagePath, productDbModelImagePath);
    }

    @Test
    void toModels_ProductsIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> ProductConverter.toModels(null));
        assertEquals(RepositoryError.PRODUCTS_IS_REQUIRED, ex.getCode());
    }

    @Test
    void toModels_AllParamsIsValid_ReturnProductDbModels() {
        var count = 5;

        var products = ProductStub.getProducts(count);
        var productDbModels = ProductConverter.toModels(products);

        assertNotNull(productDbModels);

        for (int i = 0; i < count; i++) {
            //Product - fields
            var productID = products.get(i).getProductID().getValue();
            var productCode1c = products.get(i).getCode1C();
            var productRusName = products.get(i).getRusName().getValue();
            var productKazName = products.get(i).getKazName().getValue();

            var category = products.get(i).getCategory();
            var categoryID = category.getId().getValue();
            var categoryName = category.getName().getValue();
            var categoryColor = category.getColor().name();
            var categoryParentIDOpt = category.getParentID();

            var brand = products.get(i).getBrand();
            var brandID = brand.getId().getValue();
            var brandName = brand.getName().getValue();

            var producer = products.get(i).getProducer();
            var producerID = producer.getId().getValue();
            var producerName = producer.getName().getValue();

            var productBarcode = products.get(i).getBarcode().getValue();
            var productPrice = products.get(i).getPrice().getValue();

            var productSize = products.get(i).getSize();
            var productHeight = String.valueOf(productSize.getHeight());
            var productWeight = String.valueOf(productSize.getWeight());
            var productLength = String.valueOf(productSize.getLength());

            var productImagePath = products.get(i).getImagePath();

            //ProductDbModel - fields
            var productDbModelID = productDbModels.get(i).getId();
            var productDbModelCode1c = productDbModels.get(i).getCode1C();
            var productDbModelRusName = productDbModels.get(i).getRusName();
            var productDbModelKazName = productDbModels.get(i).getKazName();

            var categoryDbModel = productDbModels.get(i).getCategory();
            var categoryDbModelID = categoryDbModel.getId();
            var categoryDbModelName = categoryDbModel.getName();
            var categoryDbModelColor = categoryDbModel.getColor();
            var categoryDbModelParentID = categoryDbModel.getParentID();

            var brandDbModel = productDbModels.get(i).getBrand();
            var brandDbModelID = brandDbModel.getId();
            var brandDbModelName = brandDbModel.getName();

            var producerDbModel = productDbModels.get(i).getProducer();
            var producerDbModelID = producerDbModel.getId();
            var producerDbModelName = producerDbModel.getName();

            var productDbModelBarcode = productDbModels.get(i).getBarcode();
            var productDbModelPrice = productDbModels.get(i).getPrice();
            var productDbModelHeight = productDbModels.get(i).getHeight();
            var productDbModelWeight = productDbModels.get(i).getWeight();
            var productDbModelLength = productDbModels.get(i).getLength();

            var productDbModelImagePath = productDbModels.get(i).getImagePath();

            //Equals fields
            assertEquals(productID, productDbModelID);
            assertEquals(productCode1c, productDbModelCode1c);
            assertEquals(productRusName, productDbModelRusName);
            assertEquals(productKazName, productDbModelKazName);

            assertEquals(categoryID, categoryDbModelID);
            assertEquals(categoryName, categoryDbModelName);
            assertEquals(categoryColor, categoryDbModelColor);


            if (categoryParentIDOpt.isPresent()) {
                if (categoryDbModelParentID != null) {
                    var categoryParentID = categoryParentIDOpt.get().getValue();
                    assertEquals(categoryParentID, categoryDbModelParentID);
                }
            }

            assertEquals(brandID, brandDbModelID);
            assertEquals(brandName, brandDbModelName);

            assertEquals(producerID, producerDbModelID);
            assertEquals(producerName, producerDbModelName);

            assertEquals(productBarcode, productDbModelBarcode);
            assertEquals(productPrice, productDbModelPrice);
            assertEquals(productHeight, productDbModelHeight);
            assertEquals(productWeight, productDbModelWeight);
            assertEquals(productLength, productDbModelLength);
            assertEquals(productImagePath, productDbModelImagePath);
        }
    }
}