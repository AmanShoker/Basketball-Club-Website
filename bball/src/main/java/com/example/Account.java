package com.example;

import java.util.ArrayList;

public class Account implements Comparable<Account> {
    
    private String username;			
	private String password;
    private String type;
    private double balance;
    private String phoneNumber;
    private String address;
    private Boolean feesPaid;
    private int attendence;
    private double revenue;
    private ArrayList<String> messages;
	
	public Account() {
		this.username = "";
        this.password = "";
        this.type = "USER";
        this.balance = 0;
        this.phoneNumber = "N/A";
        this.address = "N/A";
        this.feesPaid = true;
        this.attendence = 0;
        this.revenue = 0;
        this.messages = new ArrayList<String>();
	}

    public Account(String username, String password) {
		this.username = username;
        this.password = password;
	}


    //Equals Func
    public boolean equals(Object other) {
		Account otherAcc = (Account) other;
		return username.equals(otherAcc.username) && type.equals(otherAcc.type);
	}

    public int compareTo(Account other)
	{
		return getUsername().compareTo(other.getUsername());
	}

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return this.username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public double getBalance() {
        return this.balance;
    }

    public void AddToBalance(double amount) {
        this.balance += amount;
        setFeesPaid(this.balance <= 0);
    }

    public void payBalance(double amount) {
        this.balance -= amount;
        setFeesPaid(this.balance <= 0);
        addToRevenue(amount);
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean isFeesPaid() {
        return this.feesPaid;
    }

    public void setFeesPaid(Boolean feesPaid) {
        this.feesPaid = feesPaid;
    }  

    public void attendedClass() {
        this.attendence += 1;
    }

    public void setAttendence(int attendence) {
        this.attendence = attendence;
    }

    public int getAttendence() {
        return this.attendence;
    }

    public void setRevenue(double amount) {
        this.revenue = amount;
    }

    public void addToRevenue(double amount) {
        this.revenue += amount;
    }

    public double getRevenue() {
        return this.revenue;
    }

    public ArrayList<String> setMessages() {
        return this.messages = new ArrayList<String>();
    }

    public ArrayList<String> getMessages() {
        return this.messages;
    }

    public void addMessage(String message) {
        this.messages.add(message);
    }
}
