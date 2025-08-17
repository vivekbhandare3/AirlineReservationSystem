package com.ars.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    // !! IMPORTANT !! Update these with your MySQL details
    private static final String URL = "jdbc:mysql://localhost:3306/airline_db?useSSL=false";
    private static final String USER = "root";  // Your MySQL username
    private static final String PASS = "0112553";  // Your MySQL password

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found.", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}