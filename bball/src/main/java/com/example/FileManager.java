package com.example;

import java.io.*;

public class FileManager {

    private static final String USERS_FILE = "users.txt";
    public static AccountDatabase database;

    public static void createDatabase() {
        database = new AccountDatabase();
    }

    public static AccountDatabase getDatabase() {
        return database;
    }

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

        //Adds account to database
        account.setBalance(0.0);
        account.setPhoneNumber("000-0000");
        account.setAddress("123 Fake St");
        account.setFeesPaid(true);
        account.setAttendence(0);
        account.setRevenue(0.0);
        account.setMessages();
        account.setClasses();
        account.setType("USER");
        database.addNewAccount(account);

        return true;
    }

    private static boolean userExists(String username) {
        Account storedAccount = AccountDatabase.allAccounts.get(username);
        
        if (storedAccount != null) {
            return true;
        }
        return false;
    }
}
