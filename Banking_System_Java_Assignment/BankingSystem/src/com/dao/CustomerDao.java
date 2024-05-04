package com.dao;

import java.sql.SQLException;

import com.model.Customer;

public interface CustomerDao {
	int createCustomer(Customer customer) throws SQLException;
	
}
