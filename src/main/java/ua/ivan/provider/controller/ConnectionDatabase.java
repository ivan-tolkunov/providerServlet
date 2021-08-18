package ua.ivan.provider.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDatabase {
    public static Connection initializeDatabase()
            throws SQLException, ClassNotFoundException
    {
        // Initialize all the information regarding
        // Database Connection
        String dbDriver = "com.mysql.jdbc.Driver";
        String dbURL = "jdbc:mysql://containers-us-west-6.railway.app:7771/railway?useSSL=false";
        // Database name to access
        String dbUsername = "root";
        String dbPassword = "wk0GAUQIBVaZmPI1dWeK";

        Class.forName(dbDriver);
        Connection con = DriverManager.getConnection(dbURL,
                dbUsername,
                dbPassword);
        return con;
    }
}
