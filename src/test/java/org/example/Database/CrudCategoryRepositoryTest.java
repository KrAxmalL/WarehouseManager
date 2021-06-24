package org.example.Database;

import org.example.Databases.CrudCategoryRepository;
import org.example.Databases.CrudProductRepository;
import org.example.Models.Category;
import org.example.Models.Product;
import org.example.Utils.CommandTypeEncoder;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class CrudCategoryRepositoryTest {

    @Test
    public void testAddingCategory() {
            CrudCategoryRepository repo = new CrudCategoryRepository();
            repo.clearCategories();

            Category added = new Category("category", "description");
            repo.addCategory(added);

            //getting all categories from database
            List<Category> all = repo.getAll();
            Category readed = all.get(0);

            added.setId(readed.getId());

            Assertions.assertEquals(added, readed);
    }

    @Test
    public void testReadingCategory() {
            CrudCategoryRepository repo = new CrudCategoryRepository();
            repo.clearCategories();

            Category added = new Category("category", "description");
            repo.addCategory(added);

            //getting all categories from database
            List<Category> all = repo.getAll();
            Category readed = all.get(0);

            Category readedById = repo.getCategory(readed.getId());
            added.setId(readedById.getId());

            Assertions.assertEquals(added, readedById);
    }

    @Test
    public void testUpdatingCategory() {
            CrudCategoryRepository repo = new CrudCategoryRepository();
            repo.clearCategories();

            Category added = new Category("category", "description");
            repo.addCategory(added);

            List<Category> all = repo.getAll();
            Category fromDatabase = all.get(0);

            fromDatabase.setName("updated name");
            repo.updateCategory(fromDatabase);

            all = repo.getAll();
            Category fromDatabaseUpdated = all.get(0);

            Assertions.assertEquals(fromDatabase, fromDatabaseUpdated);
    }

    @Test
    public void testDeletingCategory() {
            CrudCategoryRepository repo = new CrudCategoryRepository();
            repo.clearCategories();
            Category toAdd = new Category("category", "description");
            repo.addCategory(toAdd);

            List<Category> all = repo.getAll();
            Category added = all.get(0);

            boolean deleted = repo.deleteCategory(added);
            List<Category> allAfterDeleting = repo.getAll();

            boolean actual = deleted && allAfterDeleting.isEmpty();

            Assertions.assertTrue(actual);
    }

    @Test
    //test if category is deleted
    //then all products of this category are also deleted
    public void testDeletingConnectedToCategoryProducts() {
            CrudCategoryRepository repo = new CrudCategoryRepository();
            repo.clearCategories();
            Category toAdd = new Category("category", "description");
            repo.addCategory(toAdd);
            toAdd = new Category("category other", "description other");
            repo.addCategory(toAdd);

            List<Category> all = repo.getAll();
            Category added = all.get(0);
            Category other = all.get(1);
            CrudProductRepository productRepo = new CrudProductRepository();
            int productsToAdd = 5;
            for(int i = 0; i < productsToAdd; i++){
                productRepo.addProduct(new Product("product " + i, "description " + i, "producer " + i, added.getId(), i, BigDecimal.valueOf(i)));
            }

            int productsOfOtherCategoryToAdd = 3;
            for(int i = productsToAdd; i < productsToAdd + productsOfOtherCategoryToAdd; i++){
                productRepo.addProduct(new Product("product " + i, "description " + i, "producer " + i, other.getId(), i, BigDecimal.valueOf(i)));
            }

            repo.deleteCategory(added);

            List<Product> productsAfterDeleting = productRepo.getAll();

            boolean actual = (productsAfterDeleting.size() == productsOfOtherCategoryToAdd);

            Assertions.assertTrue(actual);
    }

    @Test
    public void testNameCriteriaAscending() {
            CrudCategoryRepository categories = new CrudCategoryRepository();
            categories.clearCategories();

            for(int i = 9; i >= 0; i--) {
                categories.addCategory(new Category(String.valueOf((char)i + 'a'), String.valueOf((char)i + 'a')));
            }

            int command = CommandTypeEncoder.createCommand(CommandTypeEncoder.CATEGORY, CommandTypeEncoder.LIST_BY_CRITERIA,
                    CommandTypeEncoder.CRITERIA_CATEGORY_NAME, CommandTypeEncoder.ORDER_ASCEND);

            Category[] sorted = categories.listByCriteria(command, null).toArray(new Category[0]);

            boolean actual = true;

            //check that previous name is less or equal to current
            for(int i = 0; i < sorted.length-1; i++) {
                actual &= (sorted[i].getName().compareTo(sorted[i+1].getName()) < 1);
            }

            Assertions.assertTrue(actual);
    }

    @Test
    public void testNameCriteriaDescending() {
            CrudCategoryRepository categories = new CrudCategoryRepository();
            categories.clearCategories();

            for(int i = 0; i < 10; i++) {
                categories.addCategory(new Category(String.valueOf((char)i + 'a'), String.valueOf((char)i + 'a')));
            }

            int command = CommandTypeEncoder.createCommand(CommandTypeEncoder.CATEGORY, CommandTypeEncoder.LIST_BY_CRITERIA,
                    CommandTypeEncoder.CRITERIA_CATEGORY_NAME, CommandTypeEncoder.ORDER_DESCEND);

            Category[] sorted = categories.listByCriteria(command, null).toArray(new Category[0]);

            boolean actual = true;

            //check that previous name is greater or equal to current
            for(int i = 0; i < sorted.length-1; i++) {
                actual &= (sorted[i].getName().compareTo(sorted[i+1].getName()) > -1);
            }

            Assertions.assertTrue(actual);
    }

    @Test
    public void testDescriptionCriteriaAscending() {
            CrudCategoryRepository categories = new CrudCategoryRepository();
            categories.clearCategories();

            for(int i = 0; i < 10; i++) {
                categories.addCategory(new Category(String.valueOf((char)i + 'a'), String.valueOf((char)i + 'a')));
            }

            int command = CommandTypeEncoder.createCommand(CommandTypeEncoder.CATEGORY, CommandTypeEncoder.LIST_BY_CRITERIA,
                    CommandTypeEncoder.CRITERIA_DESCRIPTION, CommandTypeEncoder.ORDER_ASCEND);

            Category[] sorted = categories.listByCriteria(command, null).toArray(new Category[0]);

            boolean actual = true;

            //check that previous description is less or equal to current
            for(int i = 0; i < sorted.length-1; i++) {
                actual &= (sorted[i].getDescription().compareTo(sorted[i+1].getDescription()) < 1);
            }

            Assertions.assertTrue(actual);
    }

    @Test
    public void testDescriptionCriteriaDescending() {
            CrudCategoryRepository categories = new CrudCategoryRepository();
            categories.clearCategories();

            for(int i = 0; i < 10; i++) {
                categories.addCategory(new Category(String.valueOf((char)i + 'a'), String.valueOf((char)i + 'a')));
            }

            int command = CommandTypeEncoder.createCommand(CommandTypeEncoder.CATEGORY, CommandTypeEncoder.LIST_BY_CRITERIA,
                    CommandTypeEncoder.CRITERIA_DESCRIPTION, CommandTypeEncoder.ORDER_DESCEND);

            Category[] sorted = categories.listByCriteria(command, null).toArray(new Category[0]);

            boolean actual = true;

            //check that previous description is greater or equal to current
            for(int i = 0; i < sorted.length-1; i++) {
                actual &= (sorted[i].getDescription().compareTo(sorted[i+1].getDescription()) >-1);
            }

            Assertions.assertTrue(actual);
    }

    @Test
    public void testTextFieldCriteriaWithParameterValuesPresent() {
            CrudCategoryRepository categories = new CrudCategoryRepository();
            categories.clearCategories();

            String param = "z";
            for(int i = 0; i < 10; i++) {
                String name = String.valueOf((char)i + 'a');
                if(i % 2 == 0) {
                    name += String.valueOf(param);
                }
                categories.addCategory(new Category(name, String.valueOf((char)i + 'a')));
            }

            int command = CommandTypeEncoder.createCommand(CommandTypeEncoder.CATEGORY, CommandTypeEncoder.LIST_BY_CRITERIA,
                    CommandTypeEncoder.CRITERIA_CATEGORY_NAME, CommandTypeEncoder.ORDER_ASCEND);

            Category[] sorted = categories.listByCriteria(command, new String[] {param}).toArray(new Category[0]);

            boolean actual = true;
            for(int i = 0; i < sorted.length; i++) {
                actual &= sorted[i].getName().contains(param);
            }

            Assertions.assertTrue(actual);
    }

    @Test
    public void testTextFieldCriteriaWithParameterValuesAbsent() {
            CrudCategoryRepository categories = new CrudCategoryRepository();
            categories.clearCategories();

            String param = "z";
            for(int i = 0; i < 10; i++) {
                String name = String.valueOf((char)i + 'a');
                categories.addCategory(new Category(name, String.valueOf((char)i + 'a')));
            }

            int command = CommandTypeEncoder.createCommand(CommandTypeEncoder.CATEGORY, CommandTypeEncoder.LIST_BY_CRITERIA,
                    CommandTypeEncoder.CRITERIA_CATEGORY_NAME, CommandTypeEncoder.ORDER_ASCEND);

            Category[] sorted = categories.listByCriteria(command, new String[] {param}).toArray(new Category[0]);

            boolean actual = (sorted.length == 0);

            Assertions.assertTrue(actual);
    }

    @Test
    public void testTextFieldCriteriaWithInvalidParameters() {
            CrudCategoryRepository categories = new CrudCategoryRepository();
            categories.clearCategories();

            for(int i = 0; i < 10; i++) {
                String name = String.valueOf((char)i + 'a');
                categories.addCategory(new Category(name, String.valueOf((char)i + 'a')));
            }

            int command = CommandTypeEncoder.createCommand(CommandTypeEncoder.CATEGORY, CommandTypeEncoder.LIST_BY_CRITERIA,
                    CommandTypeEncoder.CRITERIA_CATEGORY_NAME, CommandTypeEncoder.ORDER_ASCEND);

            Category[] all = categories.getAll().toArray(new Category[0]);
            Category[] sortedByNull = categories.listByCriteria(command, new String[] {null}).toArray(new Category[0]);
            Category[] sortedByEmptyString = categories.listByCriteria(command, new String[] {""}).toArray(new Category[0]);
            Category[] sortedByStringWithWhitespaces = categories.listByCriteria(command, new String[] {"     "}).toArray(new Category[0]);

            boolean actual = Arrays.deepEquals(sortedByNull, sortedByEmptyString)
                    && Arrays.deepEquals(sortedByNull, sortedByStringWithWhitespaces)
                    && Arrays.deepEquals(sortedByStringWithWhitespaces, sortedByEmptyString)
                    && (sortedByNull.length == all.length);

            Assertions.assertTrue(actual);
    }
}
