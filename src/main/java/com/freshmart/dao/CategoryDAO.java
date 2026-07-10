package com.freshmart.dao;

import java.util.List;
import com.freshmart.dto.Category;

public interface CategoryDAO {

	void addCategory(Category category);

	Category getCategoryById(Integer catId);

	List<Category> getAllCategories();

	void updateCategory(Category category);

	void deleteCategory(Integer catId);

}