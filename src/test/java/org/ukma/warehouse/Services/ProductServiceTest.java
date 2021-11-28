package org.ukma.warehouse.Services;

import org.ukma.warehouse.Databases.CrudCategoryRepository;
import org.ukma.warehouse.Models.Category;
import org.ukma.warehouse.Models.Product;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;

public class ProductServiceTest {

    private static ProductService service = new ProductService();

    @Test
    public void testCorrectProductValidationSuccess() {
        CrudCategoryRepository categories = new CrudCategoryRepository();
        categories.addCategory(new Category("category", "descr"));

       Product valid = new Product("name", "description", "producer", 1, 1, BigDecimal.valueOf(1));
       boolean actual = service.validateProduct(valid);

        Assertions.assertTrue(actual);
    }

    @Test
    public void testBlankNameProductValidationFails() {
        CrudCategoryRepository categories = new CrudCategoryRepository();
        categories.addCategory(new Category("category", "descr"));

        Product valid = new Product("", "description", "producer", 1, 1, BigDecimal.valueOf(1));
        boolean actual = service.validateProduct(valid);

        Assertions.assertFalse(actual);
    }

    @Test
    public void testNullNameProductValidationFails() {
        CrudCategoryRepository categories = new CrudCategoryRepository();
        categories.addCategory(new Category("category", "descr"));

        Product valid = new Product(null, "description", "producer", 1, 1, BigDecimal.valueOf(1));
        boolean actual = service.validateProduct(valid);

        Assertions.assertFalse(actual);
    }

    @Test
    public void testNullDescriptionProductValidationFails() {
        CrudCategoryRepository categories = new CrudCategoryRepository();
        categories.addCategory(new Category("category", "descr"));

        Product valid = new Product("name", null, "producer", 1, 1, BigDecimal.valueOf(1));
        boolean actual = service.validateProduct(valid);

        Assertions.assertFalse(actual);
    }

    @Test
    public void testBlankProducerProductValidationFails() {
        CrudCategoryRepository categories = new CrudCategoryRepository();
        categories.addCategory(new Category("category", "descr"));

        Product valid = new Product("name", "description", "", 1, 1, BigDecimal.valueOf(1));
        boolean actual = service.validateProduct(valid);

        Assertions.assertFalse(actual);
    }

    @Test
    public void testNullProducerProductValidationFails() {
        CrudCategoryRepository categories = new CrudCategoryRepository();
        categories.addCategory(new Category("category", "descr"));

        Product valid = new Product("name", "description", null, 1, 1, BigDecimal.valueOf(1));
        boolean actual = service.validateProduct(valid);

        Assertions.assertFalse(actual);
    }

    @Test
    public void testNegativeAmountProductValidationFails() {
        CrudCategoryRepository categories = new CrudCategoryRepository();
        categories.addCategory(new Category("category", "descr"));

        Product valid = new Product("name", "description", "producer", 1, -1, BigDecimal.valueOf(1));
        boolean actual = service.validateProduct(valid);

        Assertions.assertFalse(actual);
    }

    @Test
    public void testZeroPriceProductValidationFails() {
        CrudCategoryRepository categories = new CrudCategoryRepository();
        categories.addCategory(new Category("category", "descr"));

        Product valid = new Product("name", "description", "producer", 1, 1, BigDecimal.valueOf(0));
        boolean actual = service.validateProduct(valid);

        Assertions.assertFalse(actual);
    }

    @Test
    public void testNegativePriceProductValidationFails() {
        CrudCategoryRepository categories = new CrudCategoryRepository();
        categories.addCategory(new Category("category", "descr"));

        Product valid = new Product("name", "description", "producer", 1, 1, BigDecimal.valueOf(-1));
        boolean actual = service.validateProduct(valid);

        Assertions.assertFalse(actual);
    }

    @Test
    public void testNotExistingCategoryProductValidationFails() {
        Product valid = new Product("name", "description", "producer", 10, 1, BigDecimal.valueOf(1));
        boolean actual = service.validateProduct(valid);

        Assertions.assertFalse(actual);
    }

    @Test
    public void testJsonProductParsing() throws Exception {
        CrudCategoryRepository categories = new CrudCategoryRepository();
        categories.addCategory(new Category("category", "descr"));

        Product beforeParsingToJson = new Product("name", "description", "producer", 1, 1, BigDecimal.valueOf(1));
        String productAsJson = service.productToJson(beforeParsingToJson);
        Product afterParsingFromJson = service.parseProductFromJson(productAsJson);

        boolean equal = beforeParsingToJson.equals(afterParsingFromJson);
        Assertions.assertTrue(equal);
    }
}
