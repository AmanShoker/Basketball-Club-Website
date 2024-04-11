package com.example;

import java.util.HashMap;
import java.util.Map;
import java.io.*;

public class AccountDatabase {

    private static final String USERS_FILE = "users.txt";
    
    public static Map<String, Account> allAccounts;
    public static Map<String, Account> userAccounts;

    public AccountDatabase() {
        allAccounts = new HashMap<>();
        userAccounts = new HashMap<>();

        loadDatabase();
    }

    public static void storeAccount(Account account) {
        
        if (!allAccounts.containsKey((account.getUsername()))) {
            if ((account.getType()).equals("USER")) {
                userAccounts.put(account.getUsername(), account);
            }
            allAccounts.put(account.getUsername(), account);
        }
    }

    public static void loadDatabase() {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    Account account = new Account(parts[0], parts[1]);
                    account.setType(parts[2]);
                    storeAccount(account);

                } else if (parts.length == 4) {
                    Account account = new Account(parts[0], parts[1]);
                    account.setType(parts[2]);
                    account.AddToBalance(Double.parseDouble(parts[3]));
                    storeAccount(account);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}