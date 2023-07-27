package org.kainos.ea.util;

import org.kainos.ea.exception.DatabaseConnectionException;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnector {
    private static Connection conn;

    public Connection getConnection() throws SQLException, DatabaseConnectionException {

        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");
        String host = System.getenv("DB_HOST");
        String name = System.getenv("DB_NAME");

        if (conn != null && !conn.isClosed()) { return conn; }

        try {
            String connection = "jdbc:mysql://" + host + "/" + name + "?useSSL=false";
            conn = DriverManager.getConnection(connection, user, password);
            return conn;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new DatabaseConnectionException();
        }
    }
}