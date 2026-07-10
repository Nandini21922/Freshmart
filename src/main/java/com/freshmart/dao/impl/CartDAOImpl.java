package com.freshmart.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.freshmart.dao.CartDAO;
import com.freshmart.dto.Cart;
import com.freshmart.utility.Connector;

public class CartDAOImpl implements CartDAO {

	private Connection con;

	public CartDAOImpl() {
		this.con = Connector.requestConnection();
	}

	@Override
	public void addCart(Cart cart) {

		String sql = "INSERT INTO cart VALUES(0,?,?)";

		try (PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, cart.getCid());
			ps.setDouble(2, cart.getTotalPrice());

			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error adding cart : " + e.getMessage());
		}
	}

	@Override
	public Cart getCartById(Integer cartId) {

		Cart cart = null;

		String sql = "SELECT * FROM cart WHERE cart_id=?";

		try (PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, cartId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				cart = new Cart();

				cart.setCartId(rs.getInt("cart_id"));
				cart.setCid(rs.getInt("cid"));
				cart.setTotalPrice(rs.getDouble("total_price"));

			}

		} catch (SQLException e) {
			System.out.println("Error fetching cart : " + e.getMessage());
		}

		return cart;
	}

	@Override
	public Cart getCartByCustomerId(Integer cid) {

		Cart cart = null;

		String sql = "SELECT * FROM cart WHERE cid=?";

		try (PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, cid);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				cart = new Cart();

				cart.setCartId(rs.getInt("cart_id"));
				cart.setCid(rs.getInt("cid"));
				cart.setTotalPrice(rs.getDouble("total_price"));

			}

		} catch (SQLException e) {
			System.out.println("Error fetching cart : " + e.getMessage());
		}

		return cart;
	}

	@Override
	public List<Cart> getAllCarts() {

		List<Cart> cartList = new ArrayList<>();
		Cart cart = null;

		String sql = "SELECT * FROM cart";

		try (PreparedStatement ps = con.prepareStatement(sql)) {

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				cart = new Cart();

				cart.setCartId(rs.getInt("cart_id"));
				cart.setCid(rs.getInt("cid"));
				cart.setTotalPrice(rs.getDouble("total_price"));

				cartList.add(cart);

			}

		} catch (SQLException e) {
			System.out.println("Error fetching carts : " + e.getMessage());
		}

		return cartList;
	}

	@Override
	public void updateCart(Cart cart) {

		String query = "UPDATE cart SET cid=?,total_price=? WHERE cart_id=?";

		try (PreparedStatement ps = con.prepareStatement(query)) {

			ps.setInt(1, cart.getCid());
			ps.setDouble(2, cart.getTotalPrice());
			ps.setInt(3, cart.getCartId());

			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error updating cart : " + e.getMessage());
		}
	}

	@Override
	public void deleteCart(Integer cartId) {

		String query = "DELETE FROM cart WHERE cart_id=?";

		try (PreparedStatement ps = con.prepareStatement(query)) {

			ps.setInt(1, cartId);

			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error deleting cart : " + e.getMessage());
		}
	}

}