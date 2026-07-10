package com.freshmart.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.freshmart.dao.OrderItemDAO;
import com.freshmart.dto.OrderItem;
import com.freshmart.utility.Connector;

public class OrderItemDAOImpl implements OrderItemDAO {

	private Connection con;

	public OrderItemDAOImpl() {
		this.con = Connector.requestConnection();
	}

	@Override
	public void addOrderItem(OrderItem orderItem) {

		String sql = "INSERT INTO order_item VALUES(0,?,?,?,?)";

		try (PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, orderItem.getOid());
			ps.setInt(2, orderItem.getPid());
			ps.setDouble(3, orderItem.getQuantity());
			ps.setDouble(4, orderItem.getTotalPrice());

			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error adding order item : " + e.getMessage());
		}
	}

	@Override
	public OrderItem getOrderItemById(Integer oiId) {

		OrderItem orderItem = null;

		String sql = "SELECT * FROM order_item WHERE oi_id=?";

		try (PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, oiId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				orderItem = new OrderItem();

				orderItem.setOiId(rs.getInt("oi_id"));
				orderItem.setOid(rs.getInt("oid"));
				orderItem.setPid(rs.getInt("pid"));
				orderItem.setQuantity(rs.getDouble("quantity"));
				orderItem.setTotalPrice(rs.getDouble("total_price"));

			}

		} catch (SQLException e) {
			System.out.println("Error fetching order item : " + e.getMessage());
		}

		return orderItem;
	}

	@Override
	public List<OrderItem> getOrderItemsByOrderId(Integer oid) {

		List<OrderItem> orderItemList = new ArrayList<>();
		OrderItem orderItem = null;

		String sql = "SELECT * FROM order_item WHERE oid=?";

		try (PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, oid);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				orderItem = new OrderItem();

				orderItem.setOiId(rs.getInt("oi_id"));
				orderItem.setOid(rs.getInt("oid"));
				orderItem.setPid(rs.getInt("pid"));
				orderItem.setQuantity(rs.getDouble("quantity"));
				orderItem.setTotalPrice(rs.getDouble("total_price"));

				orderItemList.add(orderItem);

			}

		} catch (SQLException e) {
			System.out.println("Error fetching order items : " + e.getMessage());
		}

		return orderItemList;
	}

	@Override
	public List<OrderItem> getAllOrderItems() {

		List<OrderItem> orderItemList = new ArrayList<>();
		OrderItem orderItem = null;

		String sql = "SELECT * FROM order_item";

		try (PreparedStatement ps = con.prepareStatement(sql)) {

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				orderItem = new OrderItem();

				orderItem.setOiId(rs.getInt("oi_id"));
				orderItem.setOid(rs.getInt("oid"));
				orderItem.setPid(rs.getInt("pid"));
				orderItem.setQuantity(rs.getDouble("quantity"));
				orderItem.setTotalPrice(rs.getDouble("total_price"));

				orderItemList.add(orderItem);

			}

		} catch (SQLException e) {
			System.out.println("Error fetching order items : " + e.getMessage());
		}

		return orderItemList;
	}

	@Override
	public void updateOrderItem(OrderItem orderItem) {

		String query = "UPDATE order_item SET oid=?,pid=?,quantity=?,total_price=? WHERE oi_id=?";

		try (PreparedStatement ps = con.prepareStatement(query)) {

			ps.setInt(1, orderItem.getOid());
			ps.setInt(2, orderItem.getPid());
			ps.setDouble(3, orderItem.getQuantity());
			ps.setDouble(4, orderItem.getTotalPrice());
			ps.setInt(5, orderItem.getOiId());

			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error updating order item : " + e.getMessage());
		}
	}

	@Override
	public void deleteOrderItem(Integer oiId) {

		String query = "DELETE FROM order_item WHERE oi_id=?";

		try (PreparedStatement ps = con.prepareStatement(query)) {

			ps.setInt(1, oiId);

			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error deleting order item : " + e.getMessage());
		}
	}

}