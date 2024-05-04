package com.dto;

public class BankAccountDetails {
	private int customerID;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String address;
	private int accountID;
	private long accountNumber;
	private String accountType;
	private double accountBalance;
	
	public BankAccountDetails(int customerID, String firstName, String lastName, String email, String phoneNumber,
			String address, int accountID, long accountNumber, String accountType, double accountBalance) {
		super();
		this.customerID = customerID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.accountID = accountID;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.accountBalance = accountBalance;
	}
	public BankAccountDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getCustomerID() {
		return customerID;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getEmail() {
		return email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public String getAddress() {
		return address;
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
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public void setAddress(String address) {
		this.address = address;
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
	@Override
	public String toString() {
		return "BankAccountDetails [customerID=" + customerID + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", phoneNumber=" + phoneNumber + ", address=" + address + ", accountID="
				+ accountID + ", accountNumber=" + accountNumber + ", accountType=" + accountType + ", accountBalance="
				+ accountBalance + "]";
	}

	
}
