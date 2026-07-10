package com.freshmart.dao;

import java.util.List;
import com.freshmart.dto.Cart;

public interface CartDAO {

	void addCart(Cart cart);

	Cart getCartById(Integer cartId);

	Cart getCartByCustomerId(Integer cid);

	List<Cart> getAllCarts();

	void updateCart(Cart cart);

	void deleteCart(Integer cartId);

}