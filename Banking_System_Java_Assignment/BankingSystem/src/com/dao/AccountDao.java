package com.dao;

import java.sql.SQLException;
import java.util.List;


import com.dto.BankAccountDetails;
import com.model.Account;

public interface AccountDao {
	boolean findAccount(int accountNumber) throws SQLException;
	int createAccount(Account account) throws SQLException;
	double getAccountBalance(int accountNumber) throws SQLException;
	int deposit(int accountNumber,double amount) throws SQLException;
	double withdraw(int accountNumber,double amount) throws SQLException;
	double transfer(int payeeAccountNumber,int beneficiaryAccountNumber,double transferAmount) throws SQLException;
	double calculateInterest(int accountNumber) throws SQLException;
	List<Account> getAllAccounts() throws SQLException;
	BankAccountDetails getBankAccountsDetails(long accountNumber) throws SQLException;
}
