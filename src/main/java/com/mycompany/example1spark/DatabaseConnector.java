/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.example1spark;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Admin
 */
public class DatabaseConnector {

    private final int port;
    private final String serverPath;
    private final String dataBaseName;
    private final String dataBaseUser;
    private final String dataBasePassword;

    private final String driver;
    private final String dataBase;
    private final Connection connection;
    private final Statement statement;

    private static DatabaseConnector instance;

    public DatabaseConnector() throws ClassNotFoundException, SQLException {
        port = 3306;
        serverPath = "localhost";
        dataBaseName = "example";
        dataBaseUser = "exampleUser";
        dataBasePassword = "12345";

        driver = "com.mysql.jdbc.Driver";
        dataBase = "jdbc:mysql://" + serverPath + ":" + port + "/" + dataBaseName;

        Class.forName(driver);
        connection = DriverManager.getConnection(dataBase, dataBaseUser, dataBasePassword);
        statement = connection.createStatement();
    }

    public static DatabaseConnector getInstance() throws ClassNotFoundException, SQLException {
        if (instance == null) {
            instance = new DatabaseConnector();
            return instance;
        } else {
            return instance;
        }
    }

    public Statement getStatement() {
        return statement;
    }
}
