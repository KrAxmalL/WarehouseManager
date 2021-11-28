package org.ukma.warehouse.Databases;

import org.ukma.warehouse.Models.Product;
import org.ukma.warehouse.Utils.QueryBuilder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CrudProductRepository {

    private DBConnection dbConnection;

    public CrudProductRepository() {
        try {
            dbConnection = DBConnection.getInstance();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean addProduct(Product product) {
        if(product != null) {
            try {
                PreparedStatement query = dbConnection.getConnection().prepareStatement(QueryBuilder.INSERTING_INTO_PRODUCT);
                query.setString(1, product.getName());
                query.setString(2, product.getDescription());
                query.setString(3, product.getProducer());
                query.setInt(4, product.getCategoryId());
                query.setInt(5, product.getAmount());
                query.setBigDecimal(6, product.getPrice());
                int res = query.executeUpdate();
                query.close();
                return true;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return false;
            }
        }

        return false;
    }

    public boolean deleteProduct(Product product) {
        if(product != null) {
            return deleteProduct(product.getId());
        }
        else {
            return false;
        }
    }

    public boolean deleteProduct(int id) {
        try {
            PreparedStatement query = dbConnection.getConnection().prepareStatement(QueryBuilder.DELETING_ONE_FROM_PRODUCT);
            query.setInt(1, id);
            int result = query.executeUpdate();
            query.close();
            return (result == 1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean updateProduct(Product product) {
        if(product != null) {
            try {
                PreparedStatement query = dbConnection.getConnection().prepareStatement(QueryBuilder.READING_ONE_FROM_PRODUCT);
                query.setInt(1, product.getId());
                ResultSet result = query.executeQuery();
                if (result.next()) {
                    //Product toUpdate = rowToProduct(result);
                    query.close();

                    query = dbConnection.getConnection().prepareStatement(QueryBuilder.UPDATING_PRODUCT);

                    query.setString(1, product.getName());
                    query.setString(2, product.getDescription());
                    query.setString(3, product.getProducer());
                    query.setInt(4, product.getCategoryId());
                    query.setInt(5, product.getAmount());
                    query.setBigDecimal(6, product.getPrice());
                    query.setInt(7, product.getId());
                    boolean res = (query.executeUpdate() == 1);
                    query.close();
                    return res;
                } else {
                    query.close();
                    return false;
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public Product getProduct(int id) {
        try {
            PreparedStatement query = dbConnection.getConnection().prepareStatement(QueryBuilder.READING_ONE_FROM_PRODUCT);
            query.setInt(1, id);
            ResultSet resultSet = query.executeQuery();
            Product result = null;
            if(resultSet.next()) {
                result = rowToProduct(resultSet);
            }
            query.close();
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public Product getProductByName(String name) {
        try {
            PreparedStatement query = dbConnection.getConnection().prepareStatement(QueryBuilder.READING_ONE_BY_NAME_FROM_PRODUCT);
            query.setString(1, name);
            ResultSet resultSet = query.executeQuery();
            Product result = null;
            if(resultSet.next()) {
                result = rowToProduct(resultSet);
            }
            query.close();
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public List<Product> getAll() {
        try {
            Statement query = dbConnection.getConnection().createStatement();
            ResultSet result = query.executeQuery(QueryBuilder.READING_ALL_FROM_PRODUCT);
            List<Product> res = resultSetToList(result);
            query.close();
            return res;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public List<Product> listByCriteria(int commandType, String[] parameters) {
        List<Product> result = new ArrayList<>();
        String strQuery = QueryBuilder.selectCriteria(commandType, parameters);
        System.out.println("Query: " + strQuery);
        System.out.println(Arrays.deepToString(parameters));
        try {
            Statement query = dbConnection.getConnection().createStatement();
            ResultSet resultSet = query.executeQuery(strQuery);
            result = resultSetToList(resultSet);
            query.close();
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    private List<Product> resultSetToList(ResultSet resultSet) {
        ArrayList<Product> res = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Product product = rowToProduct(resultSet);
                res.add(product);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return res;
    }

    private Product rowToProduct(ResultSet resultSet) {
        Product product = new Product();
        try {
            product.setId(resultSet.getInt(1));
            product.setName(resultSet.getString(2));
            product.setDescription(resultSet.getString(3));
            product.setProducer(resultSet.getString(4));
            product.setCategoryId(resultSet.getInt(5));
            product.setAmount(resultSet.getInt(6));
            product.setPrice(resultSet.getBigDecimal(7));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return product;
    }

    public boolean clearProducts() {
        try {
            Statement query = dbConnection.getConnection().createStatement();
            int res = query.executeUpdate(QueryBuilder.CLEAR_PRODUCT);
            query.close();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean deleteProductsTable() {
        try {
            Statement query = dbConnection.getConnection().createStatement();
            int res = query.executeUpdate(QueryBuilder.DELETE_PRODUCT_TABLE);
            query.close();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
