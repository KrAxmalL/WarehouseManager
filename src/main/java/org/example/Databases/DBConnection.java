package org.example.Databases;

import org.example.Utils.Config;
import org.example.Utils.QueryBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//Singleton
public class DBConnection {

    private static volatile DBConnection dbConnection;
    private Connection connection;
    private boolean isClosed;

    private DBConnection() throws SQLException {
        isClosed = true;
        try {
            Class.forName(Config.dbDriverName);
            openConnection();
            initTables();
        } catch (ClassNotFoundException ex) {
            System.err.println("Database Connection Creation Failed : " + ex.getMessage());
        }
    }

    public void openConnection() throws SQLException {
        if(isClosed()) {
            this.connection = DriverManager.getConnection(Config.dbUrl);
            isClosed = false;
        }
    }

    public void closeConnection() throws SQLException {
        if(!isClosed()) {
            connection.close();
            isClosed = true;
        }
    }

    private void initTables() {
        try {
            connection.setAutoCommit(false);
            PreparedStatement query = connection.prepareStatement(QueryBuilder.CATEGORY_TABLE_CREATION_QUERY_H2);
            query.executeUpdate();
            query.close();
            query = connection.prepareStatement(QueryBuilder.PRODUCT_TABLE_CREATION_QUERY_H2);
            query.executeUpdate();
            query.close();
            query = connection.prepareStatement(QueryBuilder.USER_TABLE_CREATION_QUERY_H2);
            query.executeUpdate();
            query.close();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static DBConnection getInstance() throws SQLException {
        //Double-Checked Locking
        DBConnection localInstance = dbConnection;
        if(localInstance == null) {
            synchronized (DBConnection.class) {
                localInstance = dbConnection;
                if(localInstance == null) {
                    localInstance = dbConnection = new DBConnection();
                }
                return localInstance;
            }
        }
        else {
            return localInstance;
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public boolean isClosed() {
        return isClosed;
    }
}
