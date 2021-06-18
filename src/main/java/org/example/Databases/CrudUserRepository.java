package org.example.Databases;

import org.example.Models.Product;
import org.example.Models.User;
import org.example.Utils.QueryBuilder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CrudUserRepository {

    private DBConnection dbConnection;

    public CrudUserRepository() {
        try {
            dbConnection = DBConnection.getInstance();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean addUser(User user) {
        if(user != null) {
            try {
                PreparedStatement query = dbConnection.getConnection().prepareStatement(QueryBuilder.INSERTING_INTO_USER);
                query.setString(1, user.getLogin());
                query.setString(2, user.getPassword());
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

    public boolean deleteUser(int id) {
        try {
            PreparedStatement query = dbConnection.getConnection().prepareStatement(QueryBuilder.DELETING_ONE_FROM_USER);
            query.setInt(1, id);
            int result = query.executeUpdate();
            query.close();
            return (result == 1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public User getUser(int id) {
        try {
            PreparedStatement query = dbConnection.getConnection().prepareStatement(QueryBuilder.READING_ONE_FROM_USER);
            query.setInt(1, id);
            ResultSet resultSet = query.executeQuery();
            User result = null;
            if(resultSet.next()) {
                result = rowToUser(resultSet);
            }
            query.close();
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public User getUserByLogin(String login) {
        try {
            PreparedStatement query = dbConnection.getConnection().prepareStatement(QueryBuilder.READING_ONE_BY_LOGIN_FROM_USER);
            query.setString(1, login);
            ResultSet resultSet = query.executeQuery();
            User result = null;
            if(resultSet.next()) {
                result = rowToUser(resultSet);
            }
            query.close();
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public List<User> getAll() {
        try {
            Statement query = dbConnection.getConnection().createStatement();
            ResultSet result = query.executeQuery(QueryBuilder.READING_ALL_FROM_USER);
            List<User> res = resultSetToList(result);
            query.close();
            return res;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private List<User> resultSetToList(ResultSet resultSet) {
        ArrayList<User> res = new ArrayList<>();
        try {
            while (resultSet.next()) {
               User user = rowToUser(resultSet);
                res.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return res;
    }

    private User rowToUser(ResultSet resultSet) {
        User user = new User();
        try {
            user.setId(resultSet.getInt(1));
            user.setLogin(resultSet.getString(2));
            user.setPassword(resultSet.getString(3));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    public boolean clearUsers() {
        try {
            Statement query = dbConnection.getConnection().createStatement();
            int res = query.executeUpdate(QueryBuilder.CLEAR_USER);
            query.close();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean deleteUsersTable() {
        try {
            Statement query = dbConnection.getConnection().createStatement();
            int res = query.executeUpdate(QueryBuilder.DELETE_USER_TABLE);
            query.close();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
