package org.example.Databases;

import org.example.Models.Category;
import org.example.Utils.QueryBuilder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CrudCategoryRepository {

    private DBConnection dbConnection;

    public CrudCategoryRepository() {
        try {
            dbConnection = DBConnection.getInstance();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean addCategory(Category category) {
        try {
            PreparedStatement query = dbConnection.getConnection().prepareStatement(QueryBuilder.INSERTING_INTO_CATEGORY);
            query.setString(1, category.getName());
            query.setString(2, category.getDescription());
            int res = query.executeUpdate();
            query.close();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean deleteCategory(Category category) {
        return deleteCategory(category.getId());
    }

    public boolean deleteCategory(int id) {
        try {
            PreparedStatement query = dbConnection.getConnection().prepareStatement(QueryBuilder.DELETING_ONE_FROM_CATEGORY);
            query.setInt(1, id);
            int result = query.executeUpdate();
            query.close();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean updateCategory(Category category) {
        if(category != null) {
            try {
                PreparedStatement query = dbConnection.getConnection().prepareStatement(QueryBuilder.READING_ONE_FROM_CATEGORY);
                query.setInt(1, category.getId());
                ResultSet result = query.executeQuery();
                if (result.next()) {
                    query.close();

                    query = dbConnection.getConnection().prepareStatement(QueryBuilder.UPDATING_CATEGORY);

                    query.setString(1, category.getName());
                    query.setString(2, category.getDescription());
                    query.setInt(3, category.getId());

                    boolean res = (query.executeUpdate() == 1);

                    query.close();

                    return res;
                }
                else {
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

    public Category getCategory(int id) {
        try {
            PreparedStatement query = dbConnection.getConnection().prepareStatement(QueryBuilder.READING_ONE_FROM_CATEGORY);
            query.setInt(1, id);
            ResultSet resultSet = query.executeQuery();
            Category result = null;
            if(resultSet.next()) {
                result = rowToCategory(resultSet);
            }
            query.close();
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public List<Category> getAll() {
        List<Category> result = new ArrayList<>();
        try {
            Statement query = dbConnection.getConnection().createStatement();
            ResultSet resultSet = query.executeQuery(QueryBuilder.READING_ALL_FROM_CATEGORY);
            result = resultSetToList(resultSet);
            query.close();
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public List<Category> listByCriteria(int commandType, String[] parameters) {
        List<Category> result = new ArrayList<>();
        String strQuery = QueryBuilder.selectCriteria(commandType, parameters);
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

    private List<Category> resultSetToList(ResultSet resultSet) {
        ArrayList<Category> res = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Category category = rowToCategory(resultSet);
                res.add(category);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return res;
    }

    private Category rowToCategory(ResultSet resultSet) {
        Category category = new Category();
        try {
            category.setId(resultSet.getInt(1));
            category.setName(resultSet.getString(2));
            category.setDescription(resultSet.getString(3));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return category;
    }

    public boolean clearCategories() {
        try {
            Statement query = dbConnection.getConnection().createStatement();
            int res = query.executeUpdate(QueryBuilder.CLEAR_CATEGORY);
            query.close();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean deleteCategoriesTable() {
        try {
            Statement query = dbConnection.getConnection().createStatement();
            int res = query.executeUpdate(QueryBuilder.DELETE_CATEGORY_TABLE);
            query.close();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
