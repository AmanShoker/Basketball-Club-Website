package com.example;

import java.io.*;

public class FileManager {

    private static final String USERS_FILE = "users.txt";

    public static boolean validateUser(Account account) {
        Account storedAccount = AccountDatabase.allAccounts.get(account.getUsername());
        
        if (storedAccount != null && storedAccount.getPassword().equals(account.getPassword())) {
            return true;
        }
        return false;
    }

    public static boolean isEmployee(Account account) {
        Account storedAccount = AccountDatabase.allAccounts.get(account.getUsername());
        
        if (storedAccount != null && storedAccount.getPassword().equals(account.getPassword()) 
            && storedAccount.getType().equals("COACH")) {
            return true;
        }
        return false;
    }

    public static boolean isTreasurer(Account account) {
        Account storedAccount = AccountDatabase.allAccounts.get(account.getUsername());
        
        if (storedAccount != null && storedAccount.getPassword().equals(account.getPassword()) 
            && storedAccount.getType().equals("TREASURER")) {
            return true;
        }
        return false;
    }

    public static boolean registerUser(Account account) {
        // Check if the user already exists
        if (userExists(account.getUsername())) {
            System.out.println("User already exists.");
            return false;
        }

        // Register the new user
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE, true))) {
            writer.newLine();
            writer.write(account.getUsername() + "," + account.getPassword() + ",USER" + "\n");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean userExists(String username) {
        Account storedAccount = AccountDatabase.allAccounts.get(username);
        
        if (storedAccount != null) {
            return true;
        }
        return false;
    }
}
