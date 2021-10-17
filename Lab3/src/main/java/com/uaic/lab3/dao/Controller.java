package com.uaic.lab3.dao;

import com.uaic.lab3.db.Database;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class Controller {
    protected Connection connection = null;

    protected Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = Database.getInstance().getConnection();
        }
        return connection;
    }
}