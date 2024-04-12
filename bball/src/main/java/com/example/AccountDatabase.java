package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.io.*;

public class AccountDatabase {

    private static final String USERS_FILE = "users.txt";
    
    //Public
    public static Map<String, Account> allAccounts;
    public static ArrayList<Account> userAccounts;

    public AccountDatabase() {
        allAccounts = new HashMap<>();
        userAccounts = new ArrayList<Account>();

        loadDatabase();
    }

    public static void storeAccount(Account account) {    
        if ((account.getType()).equals("USER")) {
            userAccounts.add(account);
        }
        allAccounts.put(account.getUsername(), account);
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

                } else if (parts.length == 7) {
                    Account account = new Account(parts[0], parts[1]);
                    account.setType(parts[2]);
                    account.AddToBalance(Double.parseDouble(parts[3]));
                    account.setPhoneNumber(parts[4]);
                    account.setAddress(parts[5]);
                    account.setFeesPaid(Boolean.parseBoolean(parts[6]));
                    storeAccount(account);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addNewAccount(Account account) {    
        if ((account.getType()).equals("USER")) {
            userAccounts.add(account);
        }
        allAccounts.put(account.getUsername(), account);
    }

    public static void sortUsersByName() {
		Collections.sort(userAccounts);
	}

    //Low to High
    public void sortAccountByBalanceAscending() {
	 Collections.sort(userAccounts, new AccountBalanceAscendingComparator());
	}

	private class AccountBalanceAscendingComparator implements Comparator<Account> {
		public int compare(Account a, Account b) {
			if (a.getBalance() > b.getBalance()) {
				return 1;
			} else if (a.getBalance() < b.getBalance()) {
				return -1;
			} else {
				return 0;
			}	
		}
	}

    //High to Low
    public void sortAccountByBalanceDescending() {
        Collections.sort(userAccounts, new AccountBalanceDescendingComparator());
    }
   
    private class AccountBalanceDescendingComparator implements Comparator<Account> {
        public int compare(Account a, Account b) {
            if (a.getBalance() < b.getBalance()) {
                return 1;
            } else if (a.getBalance() > b.getBalance()) {
                return -1;
            } else {
                return 0;
            }	
        }
    }

    //Low to High
    public void sortAccountByAttendenceAscending() {
        Collections.sort(userAccounts, new AccountAttendenceAscendingComparator());
    }
    
    private class AccountAttendenceAscendingComparator implements Comparator<Account> {
        public int compare(Account a, Account b) {
            if (a.getAttendence() > b.getAttendence()) {
                return 1;
            } else if (a.getAttendence() < b.getAttendence()) {
                return -1;
            } else {
                return 0;
            }	
        }
    }
        
    //High to Low
    public void sortAccountByAttendenceDescending() {
        Collections.sort(userAccounts, new AccountAttendenceDescendingComparator());
    }
    
    private class AccountAttendenceDescendingComparator implements Comparator<Account> {
        public int compare(Account a, Account b) {
            if (a.getAttendence() < b.getAttendence()) {
                return 1;
            } else if (a.getAttendence() > b.getAttendence()) {
                return -1;
            } else {
                return 0;
            }	
        }
    }
}