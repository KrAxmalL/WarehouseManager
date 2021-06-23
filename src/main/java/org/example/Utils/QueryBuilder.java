package org.example.Utils;

public class QueryBuilder {

    //Prepared statements for products
    public static final String PRODUCT_TABLE_CREATION_QUERY_H2 =
            "CREATE TABLE IF NOT EXISTS product (" +
                    "id INTEGER PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR," +
                    "description VARCHAR," +
                    "producer VARCHAR," +
                    "category_id INTEGER NULL," +
                    "amount INTEGER," +
                    "price DOUBLE," +
                    "CONSTRAINT CATEGORY_OF_PRODUCT_ID FOREIGN KEY(category_id) REFERENCES category(id) ON DELETE CASCADE ON UPDATE CASCADE);";

    public static final String INSERTING_INTO_PRODUCT = "INSERT INTO product(name, description, producer, category_id, " +
            "amount, price) VALUES(?, ?, ?, ?, ?, ?)";
    public static final String UPDATING_PRODUCT = "UPDATE product SET name = ?, description = ?, producer = ?," +
                                                    "category_id = ?, amount = ?, " +
                                                    " price = ? WHERE id = ?";
    public static final String DELETING_ONE_FROM_PRODUCT = "DELETE FROM product WHERE id = ?";
    public static final String READING_ALL_FROM_PRODUCT = "SELECT * FROM product";
    public static final String READING_ONE_FROM_PRODUCT = "SELECT * FROM product WHERE id = ?";
    public static final String READING_ONE_BY_NAME_FROM_PRODUCT = "SELECT * FROM product WHERE name = ?";


    public static final String CLEAR_PRODUCT = "DELETE FROM product";
    public static final String DELETE_PRODUCT_TABLE = "DROP TABLE product";

    //Prepared statements for categories
    public static final String CATEGORY_TABLE_CREATION_QUERY_H2 =
            "CREATE TABLE IF NOT EXISTS category (" +
                    "id INTEGER PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR," +
                    "description VARCHAR );";

    public static final String INSERTING_INTO_CATEGORY = "INSERT INTO category(name, description) VALUES(?, ?)";
    public static final String UPDATING_CATEGORY = "UPDATE category SET name = ?, description = ? " +
                                                 "WHERE id = ?";
    public static final String DELETING_ONE_FROM_CATEGORY = "DELETE FROM category WHERE id = ?";
    public static final String READING_ALL_FROM_CATEGORY = "SELECT * FROM category";
    public static final String READING_ONE_FROM_CATEGORY = "SELECT * FROM category WHERE id = ?";
    public static final String READING_ONE_BY_NAME_FROM_CATEGORY = "SELECT * FROM category WHERE name = ?";

    public static final String CLEAR_CATEGORY = "DELETE FROM category";
    public static final String DELETE_CATEGORY_TABLE = "DROP TABLE category";

    //Prepared statements for products
    public static final String USER_TABLE_CREATION_QUERY_H2 =
            "CREATE TABLE IF NOT EXISTS user (" +
                    "id INTEGER PRIMARY KEY AUTO_INCREMENT," +
                    "login VARCHAR," +
                    "password VARCHAR);";

    public static final String INSERTING_INTO_USER = "INSERT INTO user(login, password)" +
            "                                         VALUES(?, ?)";

    public static final String DELETING_ONE_FROM_USER = "DELETE FROM user WHERE id = ?";

    public static final String READING_ALL_FROM_USER = "SELECT * FROM user";
    public static final String READING_ONE_FROM_USER = "SELECT * FROM user WHERE id = ?";
    public static final String READING_ONE_BY_LOGIN_FROM_USER = "SELECT * FROM user WHERE login = ?";

    public static final String CLEAR_USER = "DELETE FROM user";
    public static final String DELETE_USER_TABLE = "DROP TABLE user";

    /* for parameters array:

       if selecting by text field - must contain 1 string
       returns all rows in which parameter contains given string
       for blank, null, or empty string - returns all rows

       if selecting by numeric field - must contain 2 strings - min and max values
       returns all rows in which given field values are between provided min and max parameters
       if given less than 2 values, or at least 1 value is null, empty, blank string
       or can't be correctly parsed to integer - returns all rows

       for null array - means no parameters are given
       returns all rows sorted by other criteries       */
    public static String selectCriteria(int commandType, String[] parameters) {
        String result = "SELECT * FROM " + selectTable(commandType) + " ";
        if(parameters != null) {
            result += selectRange(commandType, parameters) + " ";
        }
        result += "ORDER BY " + selectOrderField(commandType) + " "
                  + selectOrder(commandType) + ";";
        return result;
    }

    private static String selectTable(int commandType) {
        if(CommandTypeEncoder.isProduct(commandType)) {
            return "product";
        }
        if(CommandTypeEncoder.isCategory(commandType)) {
            return "category";
        }
        else {
            return "product";
        }
    }

    private static String selectRange(int commandType, String[] parameters) {
        String orderField = selectOrderField(commandType);
        String result = "";
        if(orderField.equalsIgnoreCase("price") || orderField.equalsIgnoreCase("amount")
           || orderField.equalsIgnoreCase("category_id")) {
            double min = 0;
            double max = Double.MAX_VALUE;
            if((parameters.length < 2) || (parameters[0] == null) || (parameters[1] == null) ||
                    (parameters[0].trim().isEmpty()) || parameters[1].trim().isEmpty()) {
            }
            else {
                try {
                    min = Double.parseDouble(parameters[0].trim());
                    max = Double.parseDouble(parameters[1].trim());
                    if(min < 0 || max < 0) {
                        min = 0;
                        max = Double.MAX_VALUE;
                    }
                }
                catch(NumberFormatException ex) {
                    min = 0;
                    max = Double.MAX_VALUE;
                }
            }

            if(orderField.equalsIgnoreCase("amount")) {
                result += "WHERE " + orderField + " >= " + (int)min + " AND "
                        + orderField + " <= " + (int)max;
            }
            else {
                result += "WHERE " + orderField + " >= " + min + " AND "
                        + orderField + " <= " + max;
            }
        }
        else {
            result += "WHERE " + orderField + " LIKE '%";
            if((parameters.length == 0) || (parameters[0] == null) || (parameters[0].trim().isEmpty())) {
                result += "'";
            }
            else {
                result += parameters[0].trim() + "%'";
            }
        }
        return result;
    }

    private static String selectOrderField(int commandType) {
        if((commandType & CommandTypeEncoder.CRITERIA_NAME) == CommandTypeEncoder.CRITERIA_NAME) {
            return "name";
        }
        else if((commandType & CommandTypeEncoder.CRITERIA_PRODUCER) == CommandTypeEncoder.CRITERIA_PRODUCER) {
            return "producer";
        }
        else if((commandType & CommandTypeEncoder.CRITERIA_CATEGORY_ID) == CommandTypeEncoder.CRITERIA_CATEGORY_ID) {
            return "category_id";
        }
        else if((commandType & CommandTypeEncoder.CRITERIA_PRICE) == CommandTypeEncoder.CRITERIA_PRICE) {
            return "price";
        }
        else if((commandType & CommandTypeEncoder.CRITERIA_AMOUNT) == CommandTypeEncoder.CRITERIA_AMOUNT) {
            return "amount";
        }
        else if((commandType & CommandTypeEncoder.CRITERIA_CATEGORY_NAME) == CommandTypeEncoder.CRITERIA_CATEGORY_NAME) {
            return "name";
        }
        else if((commandType & CommandTypeEncoder.CRITERIA_DESCRIPTION) == CommandTypeEncoder.CRITERIA_DESCRIPTION) {
            return "description";
        }
        else {
            return "id";
        }
    }

    private static String selectOrder(int commandType) {
        if((commandType & CommandTypeEncoder.ORDER_DESCEND) == CommandTypeEncoder.ORDER_DESCEND) {
            return "DESC";
        }
        else if((commandType & CommandTypeEncoder.ORDER_ASCEND) == CommandTypeEncoder.ORDER_ASCEND) {
            return "ASC";
        }
        else {
            //if wrong code of order - by default sorting in ascending order
            return "ASC";
        }
    }
}
