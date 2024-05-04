package com.service;

import java.sql.SQLException;

import java.util.List;

import com.dao.AccountDao;
import com.daoimpl.AccountDaoImpl;
import com.dto.BankAccountDetails;
import com.exception.InsufficientFundException;
import com.exception.InvalidAccountException;
import com.model.Account;

public class AccountService {
	AccountDao accountDao = new AccountDaoImpl();

	public BankAccountDetails getBankAccountsDetails(long accountNumber) throws SQLException {
	    BankAccountDetails accountDetails = null;
	    try {
	        accountDetails = accountDao.getBankAccountsDetails(accountNumber);
	        if (accountDetails == null) {
	            throw new NullPointerException("Account details not found for account number: " + accountNumber);
	        }
	    } catch (SQLException e) {
	        System.out.println("Account details cannot be fetched " + e.getMessage());
	    }
	    return accountDetails;
	}


	public int save(Account account) throws SQLException {
		return accountDao.createAccount(account);
	}

	public double getAccountBalance(int accountNumber) throws SQLException,InvalidAccountException {
		boolean isValid = accountDao.findAccount(accountNumber);
		if (!isValid)
			throw new InvalidAccountException("invalid Account number !");
		return accountDao.getAccountBalance(accountNumber);
	}

	public int deposit(int accountNumber, double amount) throws  SQLException,InvalidAccountException {
		boolean isValid = accountDao.findAccount(accountNumber);
		if (!isValid)
			throw new InvalidAccountException("invalid Account number !");
		return accountDao.deposit(accountNumber, amount);
	}

	public double withdraw(int accountNumber, double amount)
			throws  SQLException , InsufficientFundException, InvalidAccountException {
		boolean isValid = accountDao.findAccount(accountNumber);
		if (!isValid)
			throw new InvalidAccountException("Invalid Account number!");
		double availableAmount = accountDao.getAccountBalance(accountNumber);
		if (availableAmount < amount)
			throw new InsufficientFundException("Insufficient funds: amount exceeding account balance");
		return accountDao.withdraw(accountNumber, amount);
	}

	public double transfer(int payeeAccountNumber, int beneficiaryAccountNumber, double transferAmount)
			throws SQLException, InsufficientFundException, InvalidAccountException {
		boolean isValidSender = accountDao.findAccount(payeeAccountNumber);
		boolean isValidReceiver = accountDao.findAccount(beneficiaryAccountNumber);
		if (!isValidSender)
			throw new InvalidAccountException("Sender's account number invalid!!!");
		if (!isValidReceiver)
			throw new InvalidAccountException("Receiver's account number given is invalid!!!");
		double senderAccountBalance = accountDao.getAccountBalance(payeeAccountNumber);
		if (senderAccountBalance < transferAmount)
			throw new InsufficientFundException("Insufficient funds: Tranfer amount exceeds available balance");
		return accountDao.transfer(payeeAccountNumber, beneficiaryAccountNumber, transferAmount);
}
	
	public double calculateInterest(int accountNumber) {
        double interest = 0.0;
        try {
            interest = accountDao.calculateInterest(accountNumber);
            System.out.println("Interest calculated for account number " + accountNumber + ": " + interest);
        } catch (SQLException e) {
            System.out.println("Error occurred while calculating interest: " + e.getMessage());
        }
        return interest;
    }
	public List<Account> getAllAccounts() throws SQLException {
		return accountDao.getAllAccounts();
	}

}