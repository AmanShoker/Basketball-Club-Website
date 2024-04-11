package com.example;

public class Account {
    
    private String username;			
	private String password;
    private double balance;
	private String type;
	
	public Account() {
		this.username = "";
        this.password = "";
        this.balance = 0;
        this.type = "USER";
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
    }

    public void payBalance(double amount) {
        this.balance -= amount;
    }

}
