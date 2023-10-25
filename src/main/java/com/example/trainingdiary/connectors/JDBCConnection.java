package com.example.trainingdiary.connectors;

import com.example.trainingdiary.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
    private static Connection conn = null;
    private final static String URL = "jdbc:postgresql://localhost:5431/trainingdiary_db";
    private final static String USERNAME = "postgres";
    private final static String PASSWORD = Config.POSTGRES_PASSWORD;

    public static Connection getConn() {
        if (conn == null) {
            try {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }
}
