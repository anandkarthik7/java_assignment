package com.controller;

import java.sql.SQLException;

import java.util.*;
import com.dto.BankAccountDetails;
import com.exception.*;
import com.model.*;
import com.service.*;

public class BankController {
	public static void main(String[] args) throws SQLException {
		CustomerService customerService = new CustomerService();
		AccountService accountService = new AccountService();
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println();
			System.out.println("------->Banking System<-------");
			System.out.println();
			System.out.print("Choose an option: ");
			System.out.println();
			System.out.println("Press 1. Create Account");
			System.out.println("Press 2. Show All Accounts");
			System.out.println("Press 3. Show Account Details");
			System.out.println("Press 4. Calculate Interest");
			System.out.println("Press 5. Withdraw Amount");
			System.out.println("Press 6. Transfer Amount");
			System.out.println("Press 7. Deposit Amount");
			System.out.println("Press 8. View Account Balance");
			System.out.println("Press 0. To Exit");
			
			int input = sc.nextInt();
			if (input == 0) {
				System.out.println("Exiting from Banking System...");
				System.out.println("Thank you for using banking system application");
				break;
			}
			switch (input) {
			case 1:
				System.out.println();
				System.out.println("------->Create Account<-------");
				Random random = new Random();
				int randomNumber = random.nextInt();
				int customerId = randomNumber < 0 ? randomNumber * -1 : randomNumber;
				sc.nextLine();
				System.out.print("Enter First name : ");
				String firstName = sc.nextLine().trim();
				System.out.print("Enter Last name : ");
				String lastName = sc.nextLine().trim();
				System.out.print("Enter email : ");
				String email = sc.nextLine().trim();
				System.out.print("Enter phone number : ");
				String phoneNumber = sc.nextLine().trim();
				System.out.print("Enter address : ");
				String address = sc.nextLine().trim();

				Customer customer = new Customer(customerId, firstName, lastName, email, phoneNumber, address);

				random = new Random();
				randomNumber = random.nextInt();
				int accountId = randomNumber < 0 ? randomNumber * -1 : randomNumber;

				random = new Random();
				randomNumber = random.nextInt();
				int accountNumber = randomNumber < 0 ? randomNumber * -1 : randomNumber;
				System.out.print("Enter account type : ");
				String accountType = sc.nextLine().trim();

				Account account = new Account(accountId, accountNumber, accountType, 0.0, customerId);

				try {
					int customerStatus = customerService.save(customer);
					int accountStatus = accountService.save(account);
					if (customerStatus == 1 && accountStatus == 1)
						System.out.println("Account creation successful!");
					else
						System.out.println("Account creation failure...");
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				break;
				
			case 2:
				System.out.println();
				System.out.println("------->List of all Accounts<-------");
				try {
					List<Account> list = accountService.getAllAccounts();
					for (Account a : list)
						System.out.println(a);
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				break;


		
			case 3:
			    System.out.println();
			    System.out.println("------->Get Account Details by Account Number<-------");
			    System.out.print("Enter Account Number: ");
			    long accountNumberInput = sc.nextLong();
			    try {
			        BankAccountDetails accountDetails = accountService.getBankAccountsDetails(accountNumberInput);
			        if (accountDetails != null) {
			            System.out.println("Account Details:");
			            System.out.println(accountDetails);
			        } else {
			            System.out.println("Account not found with account number: " + accountNumberInput);
			        }
			    } catch (SQLException e) {
			        System.out.println("Error occurred while fetching account details: " + e.getMessage());
			    } catch (NullPointerException e) {
			        System.out.println("NullPointerException occurred: " + e.getMessage());
			    }
			    break;

			case 4:
				  
                System.out.println();
                System.out.println("------->Calculate Interest<-------");
                System.out.print("Enter Account Number : ");
                int interestAccountNumber = sc.nextInt();
                double interest = accountService.calculateInterest(interestAccountNumber);
				  System.out.println("Interest calculated: " + interest);
                break;
			

			case 5:
				System.out.println();
				System.out.println("------->Withdraw Amount<-------");
				System.out.print("Enter account number : ");
				accountNumber = sc.nextInt();
				System.out.print("Enter withdrawal amount: ");
				double withdrawalAmount = sc.nextDouble();
				try {
					withdrawalAmount = accountService.withdraw(accountNumber, withdrawalAmount);
					if (withdrawalAmount > -1)
						System.out.println("Rs. " + withdrawalAmount + " Withdrawn successfully!!!");
					else
						System.out.println("Amount Withdrawn failed...");
				} catch (InvalidAccountException e) {
					System.out.println(e.getMessage());

				} catch (InsufficientFundException e) {
					System.out.println(e.getMessage());
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				break;

			case 6:
				System.out.println();
				System.out.println("------->Transfer Amount<-------");
				System.out.print("Enter Sender's account number : ");
				int payeeAccountNumber = sc.nextInt();
				System.out.print("Enter Receiver's account number : ");
				int beneficiaryAccountNumber = sc.nextInt();
				System.out.print("Enter withdrawal amount: ");
				double transferAmount = sc.nextDouble();
				try {
					transferAmount = accountService.transfer(payeeAccountNumber, beneficiaryAccountNumber,
							transferAmount);
					if (transferAmount > -1)
						System.out.println("Rs. " + transferAmount + " transfered successfully!!!");
					else
						System.out.println("Amount transition failed...");
				} catch (InsufficientFundException | InvalidAccountException | SQLException e) {
					System.out.println(e.getMessage());
				}
				break;

			case 7:
				System.out.println();
				System.out.println("------->Deposit Amount<-------");
				System.out.print("Enter account number : ");
				accountNumber = sc.nextInt();
				System.out.print("Enter deposit amount: ");
				double depositAmount = sc.nextDouble();
				try {
					int depositStatus = accountService.deposit(accountNumber, depositAmount);
					if (depositStatus == 1)
						System.out.println("Amount Deposited successfully!!!");
					else
						System.out.println("Amount Deposition failed...");
				} catch (InvalidAccountException e) {
					System.out.println(e.getMessage());
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				break;
				
			case 8:
				System.out.println();
				System.out.println("------->Check Account Balance<-------");
				System.out.print("Enter account number : ");
				accountNumber = sc.nextInt();
				try {
					double accountBalance = accountService.getAccountBalance(accountNumber);
					System.out.println("Your account balance is " + accountBalance + " Rs");
				} catch (InvalidAccountException e) {
					System.out.println(e.getMessage());
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				break;

			default:
				System.out.println();
				System.out.println("Option Invalid !! Please select a valid option..");
			}
		}
		sc.close();
	}
}
