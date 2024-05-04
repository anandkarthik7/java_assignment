package com.model;

public class Account {
	private int accountID;
	private long accountNumber;
	private String accountType;
	private double accountBalance;
	private int customerId;
	public Account(int accountID, long accountNumber, String accountType, double accountBalance, int customerId) {
		super();
		this.accountID = accountID;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.accountBalance = accountBalance;
		this.customerId = customerId;
	}
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getAccountID() {
		return accountID;
	}
	public long getAccountNumber() {
		return accountNumber;
	}
	public String getAccountType() {
		return accountType;
	}
	public double getAccountBalance() {
		return accountBalance;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	@Override
	public String toString() {
		return "Account [accountID=" + accountID + ", accountNumber=" + accountNumber + ", accountType=" + accountType
				+ ", accountBalance=" + accountBalance + ", customerId=" + customerId + "]";
	}


	
}
