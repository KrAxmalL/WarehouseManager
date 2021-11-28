package org.ukma.warehouse.Database;

import org.ukma.warehouse.Databases.CrudCategoryRepository;
import org.ukma.warehouse.Databases.CrudProductRepository;
import org.ukma.warehouse.Models.Category;
import org.ukma.warehouse.Models.Product;
import org.ukma.warehouse.Utils.CommandTypeEncoder;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class CrudProductRepositoryTest {

    @Test
    public void testAddingProduct() {
            CrudCategoryRepository categories = null;
                categories = new CrudCategoryRepository();
                categories.addCategory(new Category("category", "description"));

            CrudProductRepository repo = new CrudProductRepository();
            repo.clearProducts();

            Product added = new Product("product", "description", "producer", 1, 1, BigDecimal.valueOf(1.0));
            repo.addProduct(added);

            //getting all products from database
            List<Product> all = repo.getAll();
            Product readed = all.get(0);

            added.setId(readed.getId());

            Assertions.assertEquals(added, readed);
    }

    @Test
    public void testReadingProduct() {
            CrudCategoryRepository categories = null;
                categories = new CrudCategoryRepository();
                categories.addCategory(new Category("category", "description"));
            CrudProductRepository repo = new CrudProductRepository();
            repo.clearProducts();

            Product added = new Product("product", "description", "producer", 1, 1, BigDecimal.valueOf(1.0));
            repo.addProduct(added);

            //getting all products from database
            List<Product> all = repo.getAll();
            Product readed = all.get(0);

            Product readedById = repo.getProduct(readed.getId());
            added.setId(readedById.getId());

            Assertions.assertEquals(added, readedById);
    }

    @Test
    public void testUpdatingProduct() {
            CrudCategoryRepository categories = null;
                categories = new CrudCategoryRepository();
                categories.addCategory(new Category("category", "description"));

            CrudProductRepository repo = new CrudProductRepository();
            repo.clearProducts();

            Product toAdd = new Product("product", "description", "producer", 1, 1, BigDecimal.valueOf(1));
            repo.addProduct(toAdd);

            List<Product> all = repo.getAll();
            Product fromDatabase = all.get(0);

            fromDatabase.setName("updated name");
            repo.updateProduct(fromDatabase);

            all = repo.getAll();
            Product fromDatabaseUpdated = all.get(0);
            boolean equal = fromDatabase.equals(fromDatabaseUpdated);
            Assertions.assertTrue(equal);
    }

    @Test
    public void testDeletingProduct() {
            CrudCategoryRepository categories = null;
                categories = new CrudCategoryRepository();
                categories.addCategory(new Category("category", "description"));

            CrudProductRepository repo = new CrudProductRepository();
            repo.clearProducts();
            Product toAdd = new Product("product", "description", "producer", 1, 1, BigDecimal.valueOf(1));
            repo.addProduct(toAdd);

            List<Product> all = repo.getAll();
            Product added = all.get(0);

            boolean deleted = repo.deleteProduct(added);
            List<Product> allAfterDeleting = repo.getAll();

            boolean actual = deleted && allAfterDeleting.isEmpty();

            Assertions.assertTrue(actual);
    }

    @Test
    public void testNameCriteriaAscending() {
            CrudCategoryRepository categories = null;
                categories = new CrudCategoryRepository();
                categories.addCategory(new Category("category", "description"));

            CrudProductRepository repo = new CrudProductRepository();
            repo.clearProducts();
            for(int i = 9; i >= 0; i--) {
                repo.addProduct(new Product(String.valueOf((char)i + 'a'), String.valueOf((char)i + 'a'), String.valueOf((char)i + 'a'), 1, i, BigDecimal.valueOf(i)));
            }

            //command to sort products by name in ascending order
            int command = CommandTypeEncoder.createCommand(CommandTypeEncoder.PRODUCT, CommandTypeEncoder.LIST_BY_CRITERIA,
                                                        CommandTypeEncoder.CRITERIA_NAME, CommandTypeEncoder.ORDER_ASCEND);

            Product[] sorted = repo.listByCriteria(command, null).toArray(new Product[0]);

            boolean actual = true;

            //check that previous name is less or equal to current
            for(int i = 0; i < sorted.length-1; i++) {
                actual &= (sorted[i].getName().compareTo(sorted[i+1].getName()) < 1);
            }

            Assertions.assertTrue(actual);
    }

    @Test
    public void testNameCriteriaDescending() {
            CrudCategoryRepository categories = null;
                categories = new CrudCategoryRepository();
                categories.addCategory(new Category("category", "description"));

            CrudProductRepository repo = new CrudProductRepository();
            repo.clearProducts();
            for(int i = 0; i < 10; i++) {
                repo.addProduct(new Product(String.valueOf((char)i + 'a'), String.valueOf((char)i + 'a'), String.valueOf((char)i + 'a'), 1, i, BigDecimal.valueOf(i)));
            }

            //command to sort products by name in descending order
            int command = CommandTypeEncoder.createCommand(CommandTypeEncoder.PRODUCT, CommandTypeEncoder.LIST_BY_CRITERIA,
                    CommandTypeEncoder.CRITERIA_NAME, CommandTypeEncoder.ORDER_DESCEND);

            Product[] sorted = repo.listByCriteria(command, null).toArray(new Product[0]);

            boolean actual = true;

            //check that previous name is greater or equal to current
            for(int i = 0; i < sorted.length-1; i++) {
                actual &= (sorted[i].getName().compareTo(sorted[i+1].getName()) > -1);
            }

            Assertions.assertTrue(actual);
    }

    @Test
    public void testProducerCriteriaAscending() {
            CrudCategoryRepository categories = null;
                categories = new CrudCategoryRepository();
                categories.addCategory(new Category("category", "description"));

            CrudProductRepository repo = new CrudProductRepository();
            repo.clearProducts();
            for(int i = 9; i >= 0; i--) {
                repo.addProduct(new Product(String.valueOf((char)i + 'a'), String.valueOf((char)i + 'a'), String.valueOf((char)i + 'a'), 1, i, BigDecimal.valueOf(i)));
            }

            //command to sort products by producer in ascending order
            int command = CommandTypeEncoder.createCommand(CommandTypeEncoder.PRODUCT, CommandTypeEncoder.LIST_BY_CRITERIA,
                    CommandTypeEncoder.CRITERIA_PRODUCER, CommandTypeEncoder.ORDER_ASCEND);

            Product[] sorted = repo.listByCriteria(command, null).toArray(new Product[0]);

            boolean actual = true;

            //check that previous producer is less or equal to current
            for(int i = 0; i < sorted.length-1; i++) {
                actual &= (sorted[i].getProducer().compareTo(sorted[i+1].getProducer()) < 1);
            }

            Assertions.assertTrue(actual);
    }

    @Test
    public void testProducerCriteriaDescending() {
            CrudCategoryRepository categories = null;
                categories = new CrudCategoryRepository();
                categories.addCategory(new Category("category", "description"));

            CrudProductRepository repo = new CrudProductRepository();
            repo.clearProducts();
            for(int i = 0; i < 10; i++) {
                repo.addProduct(new Product(String.valueOf((char)i + 'a'), String.valueOf((char)i + 'a'), String.valueOf((char)i + 'a'), 1, i, BigDecimal.valueOf(i)));
            }

            //command to sort products by producer in descending order
            int command = CommandTypeEncoder.createCommand(CommandTypeEncoder.PRODUCT, CommandTypeEncoder.LIST_BY_CRITERIA,
                    CommandTypeEncoder.CRITERIA_PRODUCER, CommandTypeEncoder.ORDER_DESCEND);

            Product[] sorted = repo.listByCriteria(command, null).toArray(new Product[0]);

            boolean actual = true;

            //check that previous producer is greater or equal to current
            for(int i = 0; i < sorted.length-1; i++) {
                actual &= (sorted[i].getProducer().compareTo(sorted[i+1].getProducer()) > -1);
            }

            Assertions.assertTrue(actual);
    }

    @Test
    public void testCategoryIdCriteriaAscending() {
            CrudCategoryRepository categories = null;
                categories = new CrudCategoryRepository();
                for(int i = 1; i <= 10; i++) {
                    categories.addCategory(new Category("category " + i, "description" + i));
                }
            CrudProductRepository repo = new CrudProductRepository();
            repo.clearProducts();
            for(int i = 9; i >= 0; i--) {
                repo.addProduct(new Product(String.valueOf((char)i + 'a'), String.valueOf((char)i + 'a'), String.valueOf((char)i + 'a'), i + 1, i, BigDecimal.valueOf(i)));
            }

            //command to sort products by category id in ascending order
            int command = CommandTypeEncoder.createCommand(CommandTypeEncoder.PRODUCT, CommandTypeEncoder.LIST_BY_CRITERIA,
                    CommandTypeEncoder.CRITERIA_CATEGORY_ID, CommandTypeEncoder.ORDER_ASCEND);

            Product[] sorted = repo.listByCriteria(command, null).toArray(new Product[0]);

            boolean actual = true;

            //check that previous category id is less or equal to current
            for(int i = 0; i < sorted.length-1; i++) {
                actual &= (sorted[i].getCategoryId() <= sorted[i+1].getCategoryId());
            }

            Assertions.assertTrue(actual);
    }

    @Test
    public void testCategoryIdCriteriaDescending() {
            CrudCategoryRepository categories = null;
                categories = new CrudCategoryRepository();
                for(int i = 1; i <= 10; i++) {
                    categories.addCategory(new Category("category " + i, "description" + i));
                }

            CrudProductRepository repo = new CrudProductRepository();
            repo.clearProducts();
            for(int i = 0; i < 10; i++) {
                repo.addProduct(new Product(String.valueOf((char)i + 'a'), String.valueOf((char)i + 'a'), String.valueOf((char)i + 'a'), i + 1, i, BigDecimal.valueOf(i)));
            }

            //command to sort products by category id in descending order
            int command = CommandTypeEncoder.createCommand(CommandTypeEncoder.PRODUCT, CommandTypeEncoder.LIST_BY_CRITERIA,
                    CommandTypeEncoder.CRITERIA_CATEGORY_ID, CommandTypeEncoder.ORDER_DESCEND);

            Product[] sorted = repo.listByCriteria(command, null).toArray(new Product[0]);

            boolean actual = true;

            //check that previous category id is greater or equal to current
            for(int i = 0; i < sorted.length-1; i++) {
                actual &= (sorted[i].getCategoryId() >= sorted[i+1].getCategoryId());
            }

            Assertions.assertTrue(actual);
    }

    @Test
    public void testPriceCriteriaAscending() {
            CrudCategoryRepository categories = null;
                categories = new CrudCategoryRepository();
                categories.addCategory(new Category("category", "description"));
            CrudProductRepository repo = new CrudProductRepository();
            repo.clearProducts();
            for(int i = 9; i >= 0; i--) {
                repo.addProduct(new Product(String.valueOf((char)i + 'a'), String.valueOf((char)i + 'a'), String.valueOf((char)i + 'a'), 1, i, BigDecimal.valueOf(i)));
            }

            //command to sort products by price in ascending order
            int command = CommandTypeEncoder.createCommand(CommandTypeEncoder.PRODUCT, CommandTypeEncoder.LIST_BY_CRITERIA,
                    CommandTypeEncoder.CRITERIA_PRICE, CommandTypeEncoder.ORDER_ASCEND);

            Product[] sorted = repo.listByCriteria(command, null).toArray(new Product[0]);

            boolean actual = true;

            //check that previous price is less or equal to current
            for(int i = 0; i < sorted.length-1; i++) {
                actual &= (sorted[i].getPrice().compareTo(sorted[i+1].getPrice()) <= 0);
            }

            Assertions.assertTrue(actual);
    }

    @Test
    public void testPriceCriteriaDescending() {
            CrudCategoryRepository categories = null;
                categories = new CrudCategoryRepository();
                categories.addCategory(new Category("category", "description"));

            CrudProductRepository repo = new CrudProductRepository();
            repo.clearProducts();
            for(int i = 0; i < 10; i++) {
                repo.addProduct(new Product(String.valueOf((char)i + 'a'), String.valueOf((char)i + 'a'), String.valueOf((char)i + 'a'), 1, i, BigDecimal.valueOf(i)));
            }

            //command to sort products by price in descending order
            int command = CommandTypeEncoder.createCommand(CommandTypeEncoder.PRODUCT, CommandTypeEncoder.LIST_BY_CRITERIA,
                    CommandTypeEncoder.CRITERIA_PRICE, CommandTypeEncoder.ORDER_DESCEND);

            Product[] sorted = repo.listByCriteria(command, null).toArray(new Product[0]);

            boolean actual = true;

            //check that previous price is greater or equal to current
            for(int i = 0; i < sorted.length-1; i++) {
                actual &= (sorted[i].getPrice().compareTo(sorted[i+1].getPrice()) >= 0);
            }

            Assertions.assertTrue(actual);
    }

    @Test
    public void testAmountCriteriaAscending() {
            CrudCategoryRepository categories = null;
                categories = new CrudCategoryRepository();
                categories.addCategory(new Category("category", "description"));

            CrudProductRepository repo = new CrudProductRepository();
            repo.clearProducts();
            for(int i = 9; i >= 0; i--) {
                repo.addProduct(new Product(String.valueOf((char)i + 'a'), String.valueOf((char)i + 'a'), String.valueOf((char)i + 'a'), 1, i, BigDecimal.valueOf(i)));
            }

            //command to sort products by amount in ascending order
            int command = CommandTypeEncoder.createCommand(CommandTypeEncoder.PRODUCT, CommandTypeEncoder.LIST_BY_CRITERIA,
                    CommandTypeEncoder.CRITERIA_AMOUNT, CommandTypeEncoder.ORDER_ASCEND);

            Product[] sorted = repo.listByCriteria(command, null).toArray(new Product[0]);

            boolean actual = true;

            //check that previous amount is less or equal to current
            for(int i = 0; i < sorted.length-1; i++) {
                actual &= (sorted[i].getAmount() <= sorted[i+1].getAmount());
            }

            Assertions.assertTrue(actual);
    }

    @Test
    public void testAmountCriteriaDescending() {
            CrudCategoryRepository categories = null;
                categories = new CrudCategoryRepository();
                categories.addCategory(new Category("category", "description"));

            CrudProductRepository repo = new CrudProductRepository();
            repo.clearProducts();
            for(int i = 0; i < 10; i++) {
                repo.addProduct(new Product(String.valueOf((char)i + 'a'), String.valueOf((char)i + 'a'), String.valueOf((char)i + 'a'), 1, i, BigDecimal.valueOf(i)));
            }

            //command to sort products by amount in descending order
            int command = CommandTypeEncoder.createCommand(CommandTypeEncoder.PRODUCT, CommandTypeEncoder.LIST_BY_CRITERIA,
                    CommandTypeEncoder.CRITERIA_AMOUNT, CommandTypeEncoder.ORDER_DESCEND);

            Product[] sorted = repo.listByCriteria(command, null).toArray(new Product[0]);

            boolean actual = true;

            //check that previous amount is greater or equal to current
            for(int i = 0; i < sorted.length-1; i++) {
                actual &= (sorted[i].getAmount() >= sorted[i+1].getAmount());;
            }

            Assertions.assertTrue(actual);
    }

    @Test
    public void testTextFieldCriteriaWithParameterValuesPresent() {
            CrudCategoryRepository categories = null;
                categories = new CrudCategoryRepository();
                categories.addCategory(new Category("category", "description"));

            String param = "z";
            CrudProductRepository repo = new CrudProductRepository();
            repo.clearProducts();
            for(int i = 9; i >= 0; i--) {
                String name = String.valueOf((char) i + 'a');
                if(i % 2 == 0) {
                    name += String.valueOf(param);
                }
                repo.addProduct(new Product(name, String.valueOf((char)i + 'a'), String.valueOf((char)i + 'a'), 1, i, BigDecimal.valueOf(i)));
            }

            int command = CommandTypeEncoder.createCommand(CommandTypeEncoder.PRODUCT, CommandTypeEncoder.LIST_BY_CRITERIA,
                    CommandTypeEncoder.CRITERIA_NAME, CommandTypeEncoder.ORDER_ASCEND);

            Product[] sorted = repo.listByCriteria(command, new String[] {param}).toArray(new Product[0]);

            boolean actual = true;
            for(int i = 0; i < sorted.length; i++) {
                actual &= sorted[i].getName().contains(param);
            }

            Assertions.assertTrue(actual);
    }

    @Test
    public void testTextFieldCriteriaWithParameterValuesAbsent() {
            CrudCategoryRepository categories = null;
                categories = new CrudCategoryRepository();
                categories.addCategory(new Category("category", "description"));

            String absentParam = "z";
            CrudProductRepository repo = new CrudProductRepository();
            repo.clearProducts();
            for(int i = 9; i >= 0; i--) {
                String name = String.valueOf((char) i + 'a');
                repo.addProduct(new Product(name, String.valueOf((char)i + 'a'), String.valueOf((char)i + 'a'), 1, i, BigDecimal.valueOf(i)));
            }

            int command = CommandTypeEncoder.createCommand(CommandTypeEncoder.PRODUCT, CommandTypeEncoder.LIST_BY_CRITERIA,
                    CommandTypeEncoder.CRITERIA_NAME, CommandTypeEncoder.ORDER_ASCEND);

            Product[] sorted = repo.listByCriteria(command, new String[] {absentParam}).toArray(new Product[0]);

            boolean actual = (sorted.length == 0);

            Assertions.assertTrue(actual);
    }

    @Test
    public void testTextFieldCriteriaWithInvalidParameters() {
            CrudCategoryRepository categories = null;
                categories = new CrudCategoryRepository();
                categories.addCategory(new Category("category", "description"));

            String param = "z";
            CrudProductRepository repo = new CrudProductRepository();
            repo.clearProducts();
            for(int i = 9; i >= 0; i--) {
                String name = String.valueOf((char) i + 'a');
                if(i % 2 == 0) {
                    name += String.valueOf(param);
                }
                repo.addProduct(new Product(name, String.valueOf((char)i + 'a'), String.valueOf((char)i + 'a'), 1, i, BigDecimal.valueOf(i)));
            }

            int command = CommandTypeEncoder.createCommand(CommandTypeEncoder.PRODUCT, CommandTypeEncoder.LIST_BY_CRITERIA,
                    CommandTypeEncoder.CRITERIA_NAME, CommandTypeEncoder.ORDER_ASCEND);

            Product[] all = repo.getAll().toArray(new Product[0]);
            //Product[] sortedByNull = repo.listByCriteria(command, new String[] {null}).toArray(new Product[0]);
            Product[] sortedByEmptyString = repo.listByCriteria(command, new String[] {""}).toArray(new Product[0]);
            Product[] sortedByStringWithWhitespaces = repo.listByCriteria(command, new String[] {"      "}).toArray(new Product[0]);

            boolean actual = //Arrays.deepEquals(sortedByNull, sortedByEmptyString)
                   // && Arrays.deepEquals(sortedByNull, sortedByStringWithWhitespaces)
                    /*&&*/ Arrays.deepEquals(sortedByStringWithWhitespaces, sortedByEmptyString)
                    /*&& (sortedByNull.length == all.length)*/;

            Assertions.assertTrue(actual);
    }

    @Test
    public void testNumericFieldCriteriaWithParameterValuesPresent() {
            CrudCategoryRepository categories = null;
                categories = new CrudCategoryRepository();
                categories.addCategory(new Category("category", "description"));

            int min = 3;
            int max = 8;
            CrudProductRepository repo = new CrudProductRepository();
            repo.clearProducts();
            for(int i = 9; i >= 0; i--) {
                String name = String.valueOf((char) i + 'a');
                repo.addProduct(new Product(name, String.valueOf((char)i + 'a'), String.valueOf((char)i + 'a'), 1, i, BigDecimal.valueOf(i)));
            }

            int command = CommandTypeEncoder.createCommand(CommandTypeEncoder.PRODUCT, CommandTypeEncoder.LIST_BY_CRITERIA,
                    CommandTypeEncoder.CRITERIA_PRICE, CommandTypeEncoder.ORDER_ASCEND);

            Product[] sorted = repo.listByCriteria(command, new String[] {String.valueOf(min), String.valueOf(max)}).toArray(new Product[0]);

            boolean actual = true;
            for(int i = 0; i < sorted.length; i++) {
                double price = sorted[i].getPrice().doubleValue();
                actual &= (price >= min) & (price <= max);
            }

            Assertions.assertTrue(actual);
    }

    @Test
    public void testNumericFieldCriteriaWithParameterValuesAbsent() {
            CrudCategoryRepository categories = null;
                categories = new CrudCategoryRepository();
                categories.addCategory(new Category("category", "description"));

            int min = 15;
            int max = 30;
            CrudProductRepository repo = new CrudProductRepository();
            repo.clearProducts();
            for(int i = 9; i >= 0; i--) {
                String name = String.valueOf((char) i + 'a');
                repo.addProduct(new Product(name, String.valueOf((char)i + 'a'), String.valueOf((char)i + 'a'), 1, i, BigDecimal.valueOf(i)));
            }

            int command = CommandTypeEncoder.createCommand(CommandTypeEncoder.PRODUCT, CommandTypeEncoder.LIST_BY_CRITERIA,
                    CommandTypeEncoder.CRITERIA_PRICE, CommandTypeEncoder.ORDER_ASCEND);

            Product[] sorted = repo.listByCriteria(command, new String[] {String.valueOf(min), String.valueOf(max)}).toArray(new Product[0]);

            boolean actual = (sorted.length == 0);

            Assertions.assertTrue(actual);
    }

    @Test
    public void testNumericFieldCriteriaWithInvalidValues() {
            CrudCategoryRepository categories = null;
                categories = new CrudCategoryRepository();
                categories.addCategory(new Category("category", "description"));

            int min = 3;
            int max = 8;
            CrudProductRepository repo = new CrudProductRepository();
            repo.clearProducts();
            for(int i = 9; i >= 0; i--) {
                String name = String.valueOf((char) i + 'a');
                repo.addProduct(new Product(name, String.valueOf((char)i + 'a'), String.valueOf((char)i + 'a'), 1, i, BigDecimal.valueOf(i)));
            }

            int command = CommandTypeEncoder.createCommand(CommandTypeEncoder.PRODUCT, CommandTypeEncoder.LIST_BY_CRITERIA,
                    CommandTypeEncoder.CRITERIA_PRICE, CommandTypeEncoder.ORDER_ASCEND);

            Product[] sortedNoParameters = repo.listByCriteria(command, new String[] {null, null}).toArray(new Product[0]);
            Product[] sortedOneParameter = repo.listByCriteria(command, new String[] {"5"}).toArray(new Product[0]);
            Product[] sortedInvalidNumbers = repo.listByCriteria(command, new String[] {"string", ""}).toArray(new Product[0]);

            boolean actual = Arrays.deepEquals(sortedNoParameters, sortedOneParameter)
                            && Arrays.deepEquals(sortedNoParameters, sortedInvalidNumbers)
                            && Arrays.deepEquals(sortedInvalidNumbers, sortedOneParameter)
                            && (sortedNoParameters.length == 10);

            Assertions.assertTrue(actual);
    }
}
