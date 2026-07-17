package com.freshmart.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.freshmart.dao.CartItemDAO;
import com.freshmart.dto.CartItem;
import com.freshmart.utility.Connector;

public class CartItemDAOImpl implements CartItemDAO {

	private Connection con;

	public CartItemDAOImpl() {
		this.con = Connector.requestConnection();
	}

	@Override
	public void addCartItem(CartItem cartItem) {

		String sql = "INSERT INTO cart_item VALUES(0,?,?,?,?)";

		try (PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, cartItem.getCartId());
			ps.setInt(2, cartItem.getPid());
			ps.setDouble(3, cartItem.getQuantity());
			ps.setDouble(4, cartItem.getTotalPrice());

			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error adding cart item : " + e.getMessage());
		}
	}

	@Override
	public CartItem getCartItemById(Integer ciId) {

		CartItem cartItem = null;

		String sql = "SELECT * FROM cart_item WHERE ci_id=?";

		try (PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, ciId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				cartItem = new CartItem();

				cartItem.setCiId(rs.getInt("ci_id"));
				cartItem.setCartId(rs.getInt("cart_id"));
				cartItem.setPid(rs.getInt("pid"));
				cartItem.setQuantity(rs.getDouble("quantity"));
				cartItem.setTotalPrice(rs.getDouble("total_price"));

			}

		} catch (SQLException e) {
			System.out.println("Error fetching cart item : " + e.getMessage());
		}

		return cartItem;
	}

	@Override
	public List<CartItem> getCartItemsByCartId(Integer cartId) {

		List<CartItem> cartItemList = new ArrayList<>();
		CartItem cartItem = null;

		String sql = "SELECT * FROM cart_item WHERE cart_id=?";

		try (PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, cartId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				cartItem = new CartItem();

				cartItem.setCiId(rs.getInt("ci_id"));
				cartItem.setCartId(rs.getInt("cart_id"));
				cartItem.setPid(rs.getInt("pid"));
				cartItem.setQuantity(rs.getDouble("quantity"));
				cartItem.setTotalPrice(rs.getDouble("total_price"));

				cartItemList.add(cartItem);

			}

		} catch (SQLException e) {
			System.out.println("Error fetching cart items : " + e.getMessage());
		}

		return cartItemList;
	}

	@Override
	public List<CartItem> getAllCartItems() {

		List<CartItem> cartItemList = new ArrayList<>();
		CartItem cartItem = null;

		String sql = "SELECT * FROM cart_item";

		try (PreparedStatement ps = con.prepareStatement(sql)) {

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				cartItem = new CartItem();

				cartItem.setCiId(rs.getInt("ci_id"));
				cartItem.setCartId(rs.getInt("cart_id"));
				cartItem.setPid(rs.getInt("pid"));
				cartItem.setQuantity(rs.getDouble("quantity"));
				cartItem.setTotalPrice(rs.getDouble("total_price"));

				cartItemList.add(cartItem);

			}

		} catch (SQLException e) {
			System.out.println("Error fetching cart items : " + e.getMessage());
		}

		return cartItemList;
	}

	@Override
	public void updateCartItem(CartItem cartItem) {

		String query = "UPDATE cart_item SET cart_id=?,pid=?,quantity=?,total_price=? WHERE ci_id=?";

		try (PreparedStatement ps = con.prepareStatement(query)) {

			ps.setInt(1, cartItem.getCartId());
			ps.setInt(2, cartItem.getPid());
			ps.setDouble(3, cartItem.getQuantity());
			ps.setDouble(4, cartItem.getTotalPrice());
			ps.setInt(5, cartItem.getCiId());

			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error updating cart item : " + e.getMessage());
		}
	}

	@Override
	public void deleteCartItem(Integer ciId) {

		String query = "DELETE FROM cart_item WHERE ci_id=?";

		try (PreparedStatement ps = con.prepareStatement(query)) {

			ps.setInt(1, ciId);

			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error deleting cart item : " + e.getMessage());
		}
	}
	
	@Override
	public CartItem getCartItemByCartIdAndProductId(Integer cartId, Integer pid) {

		CartItem cartItem = null;

		String query = "SELECT * FROM cart_item WHERE cart_id=? AND pid=?";

		try (PreparedStatement ps = con.prepareStatement(query)) {

			ps.setInt(1, cartId);
			ps.setInt(2, pid);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				cartItem = new CartItem();

				cartItem.setCiId(rs.getInt("ci_id"));
				cartItem.setCartId(rs.getInt("cart_id"));
				cartItem.setPid(rs.getInt("pid"));
				cartItem.setQuantity(rs.getDouble("quantity"));
				cartItem.setTotalPrice(rs.getDouble("total_price"));

			}

		} catch (SQLException e) {

			System.out.println("Error fetching cart item : " + e.getMessage());

		}

		return cartItem;
	}

}