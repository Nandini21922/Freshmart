package com.freshmart.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.freshmart.dao.OrdersDAO;
import com.freshmart.dto.Orders;
import com.freshmart.utility.Connector;

public class OrdersDAOImpl implements OrdersDAO {

	private Connection con;

	public OrdersDAOImpl() {
		this.con = Connector.requestConnection();
	}

	@Override
	public void addOrder(Orders orders) {

		String sql = "INSERT INTO orders VALUES(0,?,?,?)";

		try (PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, orders.getCid());
			ps.setDate(2, orders.getODate());
			ps.setDouble(3, orders.getTotalPrice());

			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error adding order : " + e.getMessage());
		}
	}

	@Override
	public Orders getOrderById(Integer oid) {

		Orders orders = null;

		String sql = "SELECT * FROM orders WHERE oid=?";

		try (PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, oid);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				orders = new Orders();

				orders.setOid(rs.getInt("oid"));
				orders.setCid(rs.getInt("cid"));
				orders.setODate(rs.getDate("o_date"));
				orders.setTotalPrice(rs.getDouble("total_price"));

			}

		} catch (SQLException e) {
			System.out.println("Error fetching order : " + e.getMessage());
		}

		return orders;
	}

	@Override
	public List<Orders> getOrdersByCustomerId(Integer cid) {

		List<Orders> ordersList = new ArrayList<>();
		Orders orders = null;

		String sql = "SELECT * FROM orders WHERE cid=?";

		try (PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, cid);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				orders = new Orders();

				orders.setOid(rs.getInt("oid"));
				orders.setCid(rs.getInt("cid"));
				orders.setODate(rs.getDate("o_date"));
				orders.setTotalPrice(rs.getDouble("total_price"));

				ordersList.add(orders);

			}

		} catch (SQLException e) {
			System.out.println("Error fetching orders : " + e.getMessage());
		}

		return ordersList;
	}

	@Override
	public List<Orders> getAllOrders() {

		List<Orders> ordersList = new ArrayList<>();
		Orders orders = null;

		String sql = "SELECT * FROM orders";

		try (PreparedStatement ps = con.prepareStatement(sql)) {

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				orders = new Orders();

				orders.setOid(rs.getInt("oid"));
				orders.setCid(rs.getInt("cid"));
				orders.setODate(rs.getDate("o_date"));
				orders.setTotalPrice(rs.getDouble("total_price"));

				ordersList.add(orders);

			}

		} catch (SQLException e) {
			System.out.println("Error fetching orders : " + e.getMessage());
		}

		return ordersList;
	}

	@Override
	public void updateOrder(Orders orders) {

		String query = "UPDATE orders SET cid=?,o_date=?,total_price=? WHERE oid=?";

		try (PreparedStatement ps = con.prepareStatement(query)) {

			ps.setInt(1, orders.getCid());
			ps.setDate(2, orders.getODate());
			ps.setDouble(3, orders.getTotalPrice());
			ps.setInt(4, orders.getOid());

			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error updating order : " + e.getMessage());
		}
	}

	@Override
	public void deleteOrder(Integer oid) {

		String query = "DELETE FROM orders WHERE oid=?";

		try (PreparedStatement ps = con.prepareStatement(query)) {

			ps.setInt(1, oid);

			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error deleting order : " + e.getMessage());
		}
	}

}