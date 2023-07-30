package kz.arabro.planogram.nomenclature.domain.entity.product;

import kz.arabro.planogram.nomenclature.domain.exception.CodedException;
import kz.arabro.planogram.nomenclature.testdouble.entity.*;
import kz.arabro.planogram.nomenclature.util.generator.StringGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductBuilderTest {

    @Test
    void build_ProductIDIsNull_ThrowEx() {
        var code1C = "132132134";
        var rusName = NameStub.getName();
        var kazName = NameStub.getName();
        var category = CategoryStub.getCategory();
        var brand = BrandStub.getBrand();
        var producer = ProducerStub.getProducer();
        var barcode = BarcodeStub.getBarcode();
        var price = PriceStub.getPrice();
        var size = SizeStub.getSize();
        var imagePath = StringGenerator.getRandomString();

        var builder = new ProductBuilder();

        var ex = assertThrows(CodedException.class,
                () -> builder.
                        setCode1C(code1C).
                        setRusName(rusName).
                        setKazName(kazName).
                        setCategory(category).
                        setBrand(brand).
                        setProducer(producer).
                        setBarcode(barcode).
                        setPrice(price).
                        setSize(size).
                        setImagePath(imagePath).
                        build()
        );
        assertEquals(ProductError.NULL_PRODUCT_ID_VALUE, ex.getCode());
    }

    @Test
    void build_Code1CIsNull_ThrowEx() {
        var productID = ProductID.newID();
        var rusName = NameStub.getName();
        var kazName = NameStub.getName();
        var category = CategoryStub.getCategory();
        var brand = BrandStub.getBrand();
        var producer = ProducerStub.getProducer();
        var barcode = BarcodeStub.getBarcode();
        var price = PriceStub.getPrice();
        var size = SizeStub.getSize();
        var imagePath = StringGenerator.getRandomString();

        var builder = new ProductBuilder();

        var ex = assertThrows(CodedException.class,
                () -> builder.
                        setProductID(productID).
                        setRusName(rusName).
                        setKazName(kazName).
                        setCategory(category).
                        setBrand(brand).
                        setProducer(producer).
                        setBarcode(barcode).
                        setPrice(price).
                        setSize(size).
                        setImagePath(imagePath).
                        build()
        );
        assertEquals(ProductError.NULL_CODE1C_PRODUCT_VALUE, ex.getCode());
    }

    @Test
    void build_rusNameIsNull_ThrowEx() {
        var productID = ProductID.newID();
        var code1C = "132132134";
        var kazName = NameStub.getName();
        var category = CategoryStub.getCategory();
        var brand = BrandStub.getBrand();
        var producer = ProducerStub.getProducer();
        var barcode = BarcodeStub.getBarcode();
        var price = PriceStub.getPrice();
        var size = SizeStub.getSize();
        var imagePath = StringGenerator.getRandomString();

        var builder = new ProductBuilder();

        var ex = assertThrows(CodedException.class,
                () -> builder.
                        setProductID(productID).
                        setCode1C(code1C).
                        setKazName(kazName).
                        setCategory(category).
                        setBrand(brand).
                        setProducer(producer).
                        setBarcode(barcode).
                        setPrice(price).
                        setSize(size).
                        setImagePath(imagePath).
                        build()
        );
        assertEquals(ProductError.NULL_NAME_PRODUCT_VALUE, ex.getCode());
    }

    @Test
    void build_kazNameIsNull_ThrowEx() {
        var productID = ProductID.newID();
        var code1C = "132132134";
        var rusName = NameStub.getName();
        var category = CategoryStub.getCategory();
        var brand = BrandStub.getBrand();
        var producer = ProducerStub.getProducer();
        var barcode = BarcodeStub.getBarcode();
        var price = PriceStub.getPrice();
        var size = SizeStub.getSize();
        var imagePath = StringGenerator.getRandomString();

        var builder = new ProductBuilder();

        var ex = assertThrows(CodedException.class,
                () -> builder.
                        setProductID(productID).
                        setCode1C(code1C).
                        setRusName(rusName).
                        setCategory(category).
                        setBrand(brand).
                        setProducer(producer).
                        setBarcode(barcode).
                        setPrice(price).
                        setSize(size).
                        setImagePath(imagePath).
                        build()
        );
        assertEquals(ProductError.NULL_NAME_PRODUCT_VALUE, ex.getCode());
    }

    @Test
    void build_CategoryIsNull_ThrowEx() {
        var productID = ProductID.newID();
        var code1C = "132132134";
        var rusName = NameStub.getName();
        var kazName = NameStub.getName();
        var brand = BrandStub.getBrand();
        var producer = ProducerStub.getProducer();
        var barcode = BarcodeStub.getBarcode();
        var price = PriceStub.getPrice();
        var size = SizeStub.getSize();
        var imagePath = StringGenerator.getRandomString();

        var builder = new ProductBuilder();

        var ex = assertThrows(CodedException.class,
                () -> builder.
                        setProductID(productID).
                        setCode1C(code1C).
                        setRusName(rusName).
                        setKazName(kazName).
                        setBrand(brand).
                        setProducer(producer).
                        setBarcode(barcode).
                        setPrice(price).
                        setSize(size).
                        setImagePath(imagePath).
                        build()
        );
        assertEquals(ProductError.NULL_CATEGORY_PRODUCT_VALUE, ex.getCode());
    }

    @Test
    void build_BrandIsNull_ThrowEx() {
        var productID = ProductID.newID();
        var code1C = "132132134";
        var rusName = NameStub.getName();
        var kazName = NameStub.getName();
        var category = CategoryStub.getCategory();
        var producer = ProducerStub.getProducer();
        var barcode = BarcodeStub.getBarcode();
        var price = PriceStub.getPrice();
        var size = SizeStub.getSize();
        var imagePath = StringGenerator.getRandomString();

        var builder = new ProductBuilder();

        var ex = assertThrows(CodedException.class,
                () -> builder.
                        setProductID(productID).
                        setCode1C(code1C).
                        setRusName(rusName).
                        setKazName(kazName).
                        setCategory(category).
                        setProducer(producer).
                        setBarcode(barcode).
                        setPrice(price).
                        setSize(size).
                        setImagePath(imagePath).
                        build()
        );
        assertEquals(ProductError.NULL_BRAND_PRODUCT_VALUE, ex.getCode());
    }

    @Test
    void build_ProducerIsNull_ThrowEx() {
        var productID = ProductID.newID();
        var code1C = "132132134";
        var rusName = NameStub.getName();
        var kazName = NameStub.getName();
        var category = CategoryStub.getCategory();
        var brand = BrandStub.getBrand();
        var barcode = BarcodeStub.getBarcode();
        var price = PriceStub.getPrice();
        var size = SizeStub.getSize();
        var imagePath = StringGenerator.getRandomString();

        var builder = new ProductBuilder();

        var ex = assertThrows(CodedException.class,
                () -> builder.
                        setProductID(productID).
                        setCode1C(code1C).
                        setRusName(rusName).
                        setKazName(kazName).
                        setCategory(category).
                        setBrand(brand).
                        setBarcode(barcode).
                        setPrice(price).
                        setSize(size).
                        setImagePath(imagePath).
                        build()
        );
        assertEquals(ProductError.NULL_PRODUCER_PRODUCT_VALUE, ex.getCode());
    }

    @Test
    void build_BarcodeIsNull_ThrowEx() {
        var productID = ProductID.newID();
        var code1C = "132132134";
        var rusName = NameStub.getName();
        var kazName = NameStub.getName();
        var category = CategoryStub.getCategory();
        var brand = BrandStub.getBrand();
        var producer = ProducerStub.getProducer();
        var price = PriceStub.getPrice();
        var size = SizeStub.getSize();
        var imagePath = StringGenerator.getRandomString();

        var builder = new ProductBuilder();

        var ex = assertThrows(CodedException.class,
                () -> builder.
                        setProductID(productID).
                        setCode1C(code1C).
                        setRusName(rusName).
                        setKazName(kazName).
                        setCategory(category).
                        setBrand(brand).
                        setProducer(producer).
                        setPrice(price).
                        setSize(size).
                        setImagePath(imagePath).
                        build()
        );
        assertEquals(ProductError.NULL_BARCODE_PRODUCT_VALUE, ex.getCode());
    }

    @Test
    void build_SizeIsNull_ThrowEx() {
        var productID = ProductID.newID();
        var code1C = "132132134";
        var rusName = NameStub.getName();
        var kazName = NameStub.getName();
        var category = CategoryStub.getCategory();
        var brand = BrandStub.getBrand();
        var producer = ProducerStub.getProducer();
        var barcode = BarcodeStub.getBarcode();
        var price = PriceStub.getPrice();
        var imagePath = StringGenerator.getRandomString();

        var builder = new ProductBuilder();

        var ex = assertThrows(CodedException.class,
                () -> builder.
                        setProductID(productID).
                        setCode1C(code1C).
                        setRusName(rusName).
                        setKazName(kazName).
                        setCategory(category).
                        setBrand(brand).
                        setProducer(producer).
                        setBarcode(barcode).
                        setPrice(price).
                        setSize(null).
                        setImagePath(imagePath).
                        build()
        );
        assertEquals(ProductError.NULL_SIZE_PRODUCT_VALUE, ex.getCode());
    }

    @Test
    void build_PriceIsNull_ReturnProductWithEmptyPrice() {
        var productID = ProductID.newID();
        var code1C = "132132134";
        var rusName = NameStub.getName();
        var kazName = NameStub.getName();
        var category = CategoryStub.getCategory();
        var brand = BrandStub.getBrand();
        var producer = ProducerStub.getProducer();
        var barcode = BarcodeStub.getBarcode();
        var size = SizeStub.getSize();
        var imagePath = StringGenerator.getRandomString();

        var product = new ProductBuilder().
                setProductID(productID).
                setCode1C(code1C).
                setRusName(rusName).
                setKazName(kazName).
                setCategory(category).
                setBrand(brand).
                setProducer(producer).
                setBarcode(barcode).
                setPrice(null).
                setSize(size).
                setImagePath(imagePath).
                build();

        assertNotNull(product);
        assertEquals(productID, product.getProductID());
        assertEquals(code1C, product.getCode1C());
        assertEquals(rusName, product.getRusName());
        assertEquals(kazName, product.getKazName());
        assertEquals(category, product.getCategory());
        assertEquals(brand, product.getBrand());
        assertEquals(producer, product.getProducer());
        assertNull(product.getPrice());
        assertEquals(barcode, product.getBarcode());
        assertEquals(size, product.getSize());
        assertEquals(imagePath, product.getImagePath());
    }

    @Test
    void build_ImagePathIsNull_ReturnProductWithEmptyImagePath() {
        var productID = ProductID.newID();
        var code1C = "132132134";
        var rusName = NameStub.getName();
        var kazName = NameStub.getName();
        var category = CategoryStub.getCategory();
        var brand = BrandStub.getBrand();
        var producer = ProducerStub.getProducer();
        var barcode = BarcodeStub.getBarcode();
        var price = PriceStub.getPrice();
        var size = SizeStub.getSize();

        var product = new ProductBuilder().
                setProductID(productID).
                setCode1C(code1C).
                setRusName(rusName).
                setKazName(kazName).
                setCategory(category).
                setBrand(brand).
                setProducer(producer).
                setBarcode(barcode).
                setPrice(price).
                setSize(size).
                setImagePath(null).
                build();

        assertNotNull(product);
        assertEquals(productID, product.getProductID());
        assertEquals(code1C, product.getCode1C());
        assertEquals(rusName, product.getRusName());
        assertEquals(kazName, product.getKazName());
        assertEquals(category, product.getCategory());
        assertEquals(brand, product.getBrand());
        assertEquals(producer, product.getProducer());
        assertEquals(price, product.getPrice());
        assertEquals(barcode, product.getBarcode());
        assertEquals(size, product.getSize());
        assertNull(product.getImagePath());
    }

    @Test
    void build_AllParamsIsValid_ReturnProductWithAllParams() {
        var productID = ProductID.newID();
        var code1C = "132132134";
        var rusName = NameStub.getName();
        var kazName = NameStub.getName();
        var category = CategoryStub.getCategory();
        var brand = BrandStub.getBrand();
        var producer = ProducerStub.getProducer();
        var barcode = BarcodeStub.getBarcode();
        var price = PriceStub.getPrice();
        var size = SizeStub.getSize();
        var imagePath = StringGenerator.getRandomString();

        var product = new ProductBuilder().
                setProductID(productID).
                setCode1C(code1C).
                setRusName(rusName).
                setKazName(kazName).
                setCategory(category).
                setBrand(brand).
                setProducer(producer).
                setBarcode(barcode).
                setPrice(price).
                setSize(size).
                setImagePath(imagePath).
                build();

        assertNotNull(product);
        assertEquals(productID, product.getProductID());
        assertEquals(code1C, product.getCode1C());
        assertEquals(rusName, product.getRusName());
        assertEquals(kazName, product.getKazName());
        assertEquals(category, product.getCategory());
        assertEquals(brand, product.getBrand());
        assertEquals(producer, product.getProducer());
        assertEquals(price, product.getPrice());
        assertEquals(barcode, product.getBarcode());
        assertEquals(size, product.getSize());
        assertEquals(imagePath,product.getImagePath());
    }
}
