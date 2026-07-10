package com.freshmart.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.freshmart.dao.CategoryDAO;
import com.freshmart.dto.Category;
import com.freshmart.utility.Connector;

public class CategoryDAOImpl implements CategoryDAO {

	private Connection con;

	public CategoryDAOImpl() {
		this.con = Connector.requestConnection();
	}

	@Override
	public void addCategory(Category category) {

		String sql = "INSERT INTO category VALUES(0,?)";

		try (PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, category.getCatName());

			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error adding category : " + e.getMessage());
		}
	}

	@Override
	public Category getCategoryById(Integer catId) {

		Category category = null;

		String sql = "SELECT * FROM category WHERE cat_id=?";

		try (PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, catId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				category = new Category();

				category.setCatId(rs.getInt("cat_id"));
				category.setCatName(rs.getString("cat_name"));

			}

		} catch (SQLException e) {
			System.out.println("Error fetching category : " + e.getMessage());
		}

		return category;
	}

	@Override
	public List<Category> getAllCategories() {

		List<Category> categoryList = new ArrayList<>();
		Category category = null;

		String sql = "SELECT * FROM category";

		try (PreparedStatement ps = con.prepareStatement(sql)) {

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				category = new Category();

				category.setCatId(rs.getInt("cat_id"));
				category.setCatName(rs.getString("cat_name"));

				categoryList.add(category);

			}

		} catch (SQLException e) {
			System.out.println("Error fetching categories : " + e.getMessage());
		}

		return categoryList;
	}

	@Override
	public void updateCategory(Category category) {

		String query = "UPDATE category SET cat_name=? WHERE cat_id=?";

		try (PreparedStatement ps = con.prepareStatement(query)) {

			ps.setString(1, category.getCatName());
			ps.setInt(2, category.getCatId());

			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error updating category : " + e.getMessage());
		}
	}

	@Override
	public void deleteCategory(Integer catId) {

		String query = "DELETE FROM category WHERE cat_id=?";

		try (PreparedStatement ps = con.prepareStatement(query)) {

			ps.setInt(1, catId);

			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error deleting category : " + e.getMessage());
		}
	}

}