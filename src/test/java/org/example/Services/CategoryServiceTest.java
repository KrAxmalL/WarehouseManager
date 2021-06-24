package org.example.Services;

import org.example.Databases.CrudCategoryRepository;
import org.example.Models.Category;
import org.example.Models.Category;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;

public class CategoryServiceTest {

    private static CategoryService service = new CategoryService();

    @Test
    public void testCorrectCategoryValidationSuccess() {
        Category valid = new Category("name", "description");
        boolean actual = service.validateCategory(valid);

        Assertions.assertTrue(actual);
    }

    @Test
    public void testBlankNameCategoryValidationFails() {
        Category valid = new Category("", "description");
        boolean actual = service.validateCategory(valid);

        Assertions.assertFalse(actual);
    }

    @Test
    public void testNullNameCategoryValidationFails() {
        Category valid = new Category(null, "description");
        boolean actual = service.validateCategory(valid);

        Assertions.assertFalse(actual);
    }

    @Test
    public void testNullDescriptionCategoryValidationFails() {
        Category valid = new Category("name", null);
        boolean actual = service.validateCategory(valid);

        Assertions.assertFalse(actual);
    }

    @Test
    public void testJsonCategoryParsing() throws Exception {
        Category beforeParsingToJson = new Category("name", "description");
        String categoryAsJson = service.categoryToJson(beforeParsingToJson);
        Category afterParsingFromJson = service.parseCategoryFromJson(categoryAsJson);

        boolean equal = beforeParsingToJson.equals(afterParsingFromJson);
        Assertions.assertTrue(equal);
    }
}
