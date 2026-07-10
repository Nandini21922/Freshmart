package com.freshmart.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.freshmart.dao.CustomerDAO;
import com.freshmart.dto.Customer;
import com.freshmart.utility.Connector;

public class CustomerDAOImpl implements CustomerDAO {

	private Connection con;

	public CustomerDAOImpl() {
		this.con = Connector.requestConnection();
	}

	@Override
	public void registerCustomer(Customer customer) {

		String sql = "INSERT INTO customer VALUES(0,?,?,?,?)";

		try (PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, customer.getCname());
			ps.setLong(2, customer.getPhone());
			ps.setString(3, customer.getMail());
			ps.setString(4, customer.getPwd());

			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error registering customer : " + e.getMessage());
		}
	}

	@Override
	public Customer loginCustomer(String mail, String pwd) {

		Customer customer = null;

		String sql = "SELECT * FROM customer WHERE mail=? AND pwd=?";

		try (PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, mail);
			ps.setString(2, pwd);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				customer = new Customer();

				customer.setCid(rs.getInt("cid"));
				customer.setCname(rs.getString("cname"));
				customer.setPhone(rs.getLong("phone"));
				customer.setMail(rs.getString("mail"));
				customer.setPwd(rs.getString("pwd"));

			}

		} catch (SQLException e) {
			System.out.println("Error during customer login : " + e.getMessage());
		}

		return customer;
	}

	@Override
	public void updateCustomer(Customer customer) {

		String query = "UPDATE customer SET cname=?,phone=?,mail=?,pwd=? WHERE cid=?";

		try (PreparedStatement ps = con.prepareStatement(query)) {

			ps.setString(1, customer.getCname());
			ps.setLong(2, customer.getPhone());
			ps.setString(3, customer.getMail());
			ps.setString(4, customer.getPwd());
			ps.setInt(5, customer.getCid());

			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error updating customer : " + e.getMessage());
		}
	}

	@Override
	public Customer getCustomerById(Integer cid) {

		Customer customer = null;

		String sql = "SELECT * FROM customer WHERE cid=?";

		try (PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, cid);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				customer = new Customer();

				customer.setCid(rs.getInt("cid"));
				customer.setCname(rs.getString("cname"));
				customer.setPhone(rs.getLong("phone"));
				customer.setMail(rs.getString("mail"));
				customer.setPwd(rs.getString("pwd"));

			}

		} catch (SQLException e) {
			System.out.println("Error fetching customer : " + e.getMessage());
		}

		return customer;
	}

	@Override
	public void deleteCustomer(Integer cid) {

		String query = "DELETE FROM customer WHERE cid=?";

		try (PreparedStatement ps = con.prepareStatement(query)) {

			ps.setInt(1, cid);

			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error deleting customer : " + e.getMessage());
		}
	}

	@Override
	public List<Customer> getAllCustomers() {

		List<Customer> customerList = new ArrayList<>();
		Customer customer = null;

		String sql = "SELECT * FROM customer";

		try (PreparedStatement ps = con.prepareStatement(sql)) {

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				customer = new Customer();

				customer.setCid(rs.getInt("cid"));
				customer.setCname(rs.getString("cname"));
				customer.setPhone(rs.getLong("phone"));
				customer.setMail(rs.getString("mail"));
				customer.setPwd(rs.getString("pwd"));

				customerList.add(customer);

			}

		} catch (SQLException e) {
			System.out.println("Error fetching customers : " + e.getMessage());
		}

		return customerList;
	}

}