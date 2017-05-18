/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.example1spark;

import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static spark.Spark.*;

/**
 *
 * @author Admin
 */
public class Server {

    public static void main(String[] args) {
        
        Logger logger = LoggerFactory.getLogger(Server.class);
        
        // Create account service implementation
        post("/createAccount", (request, response) -> {
            logger.info("Request received in /createAccount service");
            JSONParser parser = new JSONParser();
            String jsonToString = "[" + request.body() + "]";
            Object obj = parser.parse(jsonToString);
            JSONArray jsonArray = (JSONArray) obj;

            JSONObject parsedObject = (JSONObject) jsonArray.get(0);

            String username = (String) parsedObject.get("username");
            String password = (String) parsedObject.get("password");
            String email = (String) parsedObject.get("email");
            Account account = new Account(username, password, email);
            try {
                AccountManager.createAccount(account);
            } catch (Exception e) {
                return "Unable to create account";
            }
            return "Account Created";
        });
        
        // Get accounts service implementation
        get("/getAccounts", (request, response) -> {
            logger.info("Request received in /getAccount service");
            ArrayList<Account> accounts = AccountManager.getAccounts();
            
            JSONObject resultJson = new JSONObject();
            
            JSONArray accountList = new JSONArray();
            
            for(int i = 0; i < accounts.size(); i++) {
                JSONObject account = new JSONObject();
                account.put("username", accounts.get(i).getUsername());
                account.put("password", accounts.get(i).getPassword());
                account.put("email", accounts.get(i).getEmail());
                accountList.add(account);
            }
            
            resultJson.put("accounts", accountList);
            
            return resultJson.toJSONString();
        });
    }
}
