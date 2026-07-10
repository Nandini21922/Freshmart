package com.freshmart.dao;

import java.util.List;
import com.freshmart.dto.Product;

public interface ProductDAO {

	void addProduct(Product product);

	Product getProductById(Integer pid);

	List<Product> getAllProducts();

	List<Product> getProductsByCategory(Integer catId);

	void updateProduct(Product product);

	void deleteProduct(Integer pid);

}