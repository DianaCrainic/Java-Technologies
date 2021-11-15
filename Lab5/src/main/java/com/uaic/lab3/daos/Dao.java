package com.uaic.lab3.daos;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class Dao {
    private final DataSource examSchedulerDatasource;

    public Dao() throws NamingException {
        InitialContext initialContext = new InitialContext();
        this.examSchedulerDatasource = (DataSource) initialContext.lookup("java:/postgres");
    }

    protected Connection getConnection() throws SQLException {
        return examSchedulerDatasource.getConnection();
    }
}
