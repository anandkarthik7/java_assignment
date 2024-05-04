package com.daoimpl;

import java.sql.*;
import java.util.*;

import com.dao.AccountDao;
import com.dto.BankAccountDetails;
import com.model.*;
import com.utility.*;

public class AccountDaoImpl implements AccountDao {

	@Override
	public boolean findAccount(int accountNumber) throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = "select account_number from Account where account_number=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, accountNumber);
		ResultSet rs = pstmt.executeQuery();
		boolean status = rs.next();
		DBConnection.dbClose();
		return status;
	}
	@Override
	public int createAccount(Account account) throws SQLException {
	    Connection con = DBConnection.dbConnect();
	    String sql = "insert into Account (account_id, account_number, account_type, account_balance, customer_id) values (?,?,?,?,?)";
	    PreparedStatement pstmt = con.prepareStatement(sql);
	    pstmt.setInt(1, account.getAccountID()); // account_id
	    pstmt.setLong(2, account.getAccountNumber()); // account_number
	    pstmt.setString(3, account.getAccountType()); // account_type
	    pstmt.setDouble(4, account.getAccountBalance()); // account_balance
	    pstmt.setInt(5, account.getCustomerId()); // customer_id
	    int status = pstmt.executeUpdate();
	    DBConnection.dbClose();
	    return status;
	}


	

	
	@Override
	public int deposit(int accountNumber, double amount) throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = "select account_balance from Account where account_number=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, accountNumber);
		ResultSet rs = pstmt.executeQuery();
		double accountBalance = -1;
		if (rs.next())
			accountBalance = rs.getDouble("account_balance");
		accountBalance += amount;
		sql = "update Account set account_balance=? where account_number=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setDouble(1, accountBalance);
		pstmt.setInt(2, accountNumber);
		int status = pstmt.executeUpdate();
		DBConnection.dbClose();
		return status;
	}

	@Override
	public double withdraw(int accountNumber, double amount) throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = "select account_balance from Account where account_number=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, accountNumber);
		ResultSet rs = pstmt.executeQuery();
		double accountBalance = -1;
		if (rs.next())
			accountBalance = rs.getDouble("account_balance");
		accountBalance -= amount;
		sql = "update account set account_balance=? where account_number=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setDouble(1, accountBalance);
		pstmt.setInt(2, accountNumber);
		pstmt.executeUpdate();
		DBConnection.dbClose();
		return amount;
	}
	
	
	@Override
	public double getAccountBalance(int accountNumber) throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = "select account_balance from Account where account_number=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, accountNumber);
		ResultSet rs = pstmt.executeQuery();
		double accountBalance = -1;
		if (rs.next())
			accountBalance = rs.getDouble("account_balance");
		DBConnection.dbClose();
		return accountBalance;
	}

	@Override
	public double transfer(int payeeAccountNumber, int beneficiaryAccountNumber, double transferAmount)
			throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = "select account_balance from Account where account_number=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, payeeAccountNumber);
		ResultSet rs = pstmt.executeQuery();

		double senderAccountBalance = -1;
		if (rs.next())
			senderAccountBalance = rs.getDouble("account_balance");
		senderAccountBalance -= transferAmount;
		sql = "update Account set account_balance=? where account_number=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setDouble(1, senderAccountBalance);
		pstmt.setInt(2, payeeAccountNumber);
		pstmt.executeUpdate();

		sql = "select account_balance from Account where account_number=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, beneficiaryAccountNumber);
		rs = pstmt.executeQuery();
		double receiverAccountBalance = -1;
		if (rs.next())
			receiverAccountBalance = rs.getDouble("account_balance");
		receiverAccountBalance += transferAmount;
		sql = "update Account set account_balance=? where account_number=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setDouble(1, receiverAccountBalance);
		pstmt.setInt(2, beneficiaryAccountNumber);
		pstmt.executeUpdate();
		DBConnection.dbClose();
		return transferAmount;
	}



	@Override
	public BankAccountDetails getBankAccountsDetails(long accountNumber) throws SQLException {
	    Connection con = DBConnection.dbConnect();
	    String sql = "SELECT * FROM Customer c JOIN Account a ON c.customer_id = a.customer_id WHERE account_number = ?";
	    PreparedStatement pstmt = con.prepareStatement(sql);
	    pstmt.setLong(1, accountNumber);
	    ResultSet rs = pstmt.executeQuery();
	    BankAccountDetails accountDetails = null; // Initialize accountDetails outside the while loop
	    while (rs.next()) {
	        int customerId = rs.getInt("customer_id");
	        String firstName = rs.getString("first_name");
	        String lastName = rs.getString("last_name");
	        String email = rs.getString("email_address");
	        String phoneNumber = rs.getString("phone_number");
	        String address = rs.getString("address");
	        int accountId = rs.getInt("account_id");
	        int accountNo = rs.getInt("account_number");
	        String accountType = rs.getString("account_type");
	        double accountBalance = rs.getDouble("account_balance");
	        accountDetails = new BankAccountDetails(customerId, firstName, lastName, email, phoneNumber,
	                address, accountId, accountNo, accountType, accountBalance);
	    }
	    DBConnection.dbClose();
	    return accountDetails;
	}


	@Override
	public double calculateInterest(int accountNumber) throws SQLException {
	    Connection con = DBConnection.dbConnect();
	    String sql = "select account_balance from Account where account_number=?";
	    PreparedStatement pstmt = con.prepareStatement(sql);
	    pstmt.setInt(1, accountNumber);
	    ResultSet rs = pstmt.executeQuery();
	    double accountBalance = -1;
	    if (rs.next())
	        accountBalance = rs.getDouble("account_balance");
	    double interest = accountBalance * 0.045;
	    DBConnection.dbClose();
	    return interest;
	}
	
	@Override
	public List<Account> getAllAccounts() throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = "select * from account";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		List<Account> list = new ArrayList<>();
		while (rs.next()) {
			int accountId = rs.getInt("account_id");
			int accountNumber = rs.getInt("account_number");
			String accountType = rs.getString("account_type");
			double accountBalance = rs.getDouble("account_balance");
			int customerId = rs.getInt("customer_id");
			Account account = new Account(accountId, accountNumber, accountType, accountBalance, customerId);
			list.add(account);
		}
		DBConnection.dbClose();
		return list;
	}
	
}
