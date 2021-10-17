package com.uaic.lab3.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static Database instance;
    private Connection connection;

    private final String URL = "jdbc:postgresql://localhost:5432/exam_scheduling";
    private final String USERNAME = "postgres";
    private final String PASSWORD = "postgres";

    private Database() {
        try {
            String postgresqlDriver = "org.postgresql.Driver";
            Class.forName(postgresqlDriver);
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException exception) {
            System.out.println("db.Database Connection Creation Failed: " + exception.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static Database getInstance() throws SQLException {
        if (instance == null || instance.getConnection().isClosed()) {
            instance = new Database();
        }
        return instance;
    }
}