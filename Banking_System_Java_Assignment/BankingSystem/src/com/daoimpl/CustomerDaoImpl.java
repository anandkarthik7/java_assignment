package com.daoimpl;

import java.sql.*;
import com.dao.CustomerDao;
import com.model.Customer;
import com.utility.DBConnection;

public class CustomerDaoImpl implements CustomerDao {

	@Override
	public int createCustomer(Customer customer) throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = "insert into Customer (customer_id,first_name, last_name, email_address, phone_number, address) values"
				+ "(?,?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, customer.getCustomerID());
		pstmt.setString(2, customer.getFirstName());
		pstmt.setString(3, customer.getLastName());
		pstmt.setString(4, customer.getEmail());
		pstmt.setString(5, customer.getPhoneNumber());
		pstmt.setString(6, customer.getAddress());
		int status = pstmt.executeUpdate();
		DBConnection.dbClose();
		return status;
	}






}
