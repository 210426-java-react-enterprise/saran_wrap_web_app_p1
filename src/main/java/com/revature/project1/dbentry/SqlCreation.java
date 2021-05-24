package com.revature.project1.dbentry;

import com.revature.project1.util.ConnectionFactory;

import java.sql.Connection;

public class SqlCreation {
    Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }
}
