/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.example1spark;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class AccountManager {
    static boolean createAccount(Account account) throws ClassNotFoundException, SQLException {
        String query = "INSERT INTO account VALUES ('" + account.getUsername() + "', '" + account.getPassword() + "', '" + account.getEmail() + "');";
        DatabaseConnector.getInstance().getStatement().executeUpdate(query);
        return true;
    }
    
    static ArrayList<Account> getAccounts() throws ClassNotFoundException, SQLException {
        String query = "SELECT * FROM account;";
        ResultSet results = DatabaseConnector.getInstance().getStatement().executeQuery(query);
        
        ArrayList<Account> accounts = new ArrayList<>();
        while(results.next()) {
            String username = results.getString("username");
            String password = results.getString("password");
            String email = results.getString("email");
            
            accounts.add(new Account(username, password, email));
        }
        
        return accounts;
    }
}
