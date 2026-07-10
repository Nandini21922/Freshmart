package com.freshmart.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.freshmart.dao.ProductDAO;
import com.freshmart.dto.Product;
import com.freshmart.utility.Connector;

public class ProductDAOImpl implements ProductDAO {

	private Connection con;

	public ProductDAOImpl() {
		this.con = Connector.requestConnection();
	}

	@Override
	public void addProduct(Product product) {

		String sql = "INSERT INTO product VALUES(0,?,?,?,?,?)";

		try (PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, product.getPname());
			ps.setDouble(2, product.getPricePerKg());
			ps.setInt(3, product.getCatId());
			ps.setString(4, product.getDescription());
			ps.setString(5, product.getImageUrl());

			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error adding product : " + e.getMessage());
		}
	}

	@Override
	public Product getProductById(Integer pid) {

		Product product = null;

		String sql = "SELECT * FROM product WHERE pid=?";

		try (PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, pid);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				product = new Product();

				product.setPid(rs.getInt("pid"));
				product.setPname(rs.getString("pname"));
				product.setPricePerKg(rs.getDouble("price_per_kg"));
				product.setCatId(rs.getInt("cat_id"));
				product.setDescription(rs.getString("description"));
				product.setImageUrl(rs.getString("image_url"));

			}

		} catch (SQLException e) {
			System.out.println("Error fetching product : " + e.getMessage());
		}

		return product;
	}

	@Override
	public List<Product> getAllProducts() {

		List<Product> productList = new ArrayList<>();
		Product product = null;

		String sql = "SELECT * FROM product";

		try (PreparedStatement ps = con.prepareStatement(sql)) {

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				product = new Product();

				product.setPid(rs.getInt("pid"));
				product.setPname(rs.getString("pname"));
				product.setPricePerKg(rs.getDouble("price_per_kg"));
				product.setCatId(rs.getInt("cat_id"));
				product.setDescription(rs.getString("description"));
				product.setImageUrl(rs.getString("image_url"));

				productList.add(product);

			}

		} catch (SQLException e) {
			System.out.println("Error fetching products : " + e.getMessage());
		}

		return productList;
	}

	@Override
	public List<Product> getProductsByCategory(Integer catId) {

		List<Product> productList = new ArrayList<>();
		Product product = null;

		String sql = "SELECT * FROM product WHERE cat_id=?";

		try (PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, catId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				product = new Product();

				product.setPid(rs.getInt("pid"));
				product.setPname(rs.getString("pname"));
				product.setPricePerKg(rs.getDouble("price_per_kg"));
				product.setCatId(rs.getInt("cat_id"));
				product.setDescription(rs.getString("description"));
				product.setImageUrl(rs.getString("image_url"));

				productList.add(product);

			}

		} catch (SQLException e) {
			System.out.println("Error fetching products by category : " + e.getMessage());
		}

		return productList;
	}

	@Override
	public void updateProduct(Product product) {

		String query = "UPDATE product SET pname=?,price_per_kg=?,cat_id=?,description=?,image_url=? WHERE pid=?";

		try (PreparedStatement ps = con.prepareStatement(query)) {

			ps.setString(1, product.getPname());
			ps.setDouble(2, product.getPricePerKg());
			ps.setInt(3, product.getCatId());
			ps.setString(4, product.getDescription());
			ps.setString(5, product.getImageUrl());
			ps.setInt(6, product.getPid());

			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error updating product : " + e.getMessage());
		}
	}

	@Override
	public void deleteProduct(Integer pid) {

		String query = "DELETE FROM product WHERE pid=?";

		try (PreparedStatement ps = con.prepareStatement(query)) {

			ps.setInt(1, pid);

			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error deleting product : " + e.getMessage());
		}
	}

}