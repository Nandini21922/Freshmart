package com.freshmart.dao;

import java.util.List;
import com.freshmart.dto.Customer;

public interface CustomerDAO {

	void registerCustomer(Customer customer);

	Customer loginCustomer(String mail, String pwd);

	void updateCustomer(Customer customer);

	Customer getCustomerById(Integer cid);

	void deleteCustomer(Integer cid);

	List<Customer> getAllCustomers();

}