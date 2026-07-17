package com.freshmart.dao;

import java.util.List;
import com.freshmart.dto.CartItem;

public interface CartItemDAO {

	void addCartItem(CartItem cartItem);

	CartItem getCartItemById(Integer ciId);

	List<CartItem> getCartItemsByCartId(Integer cartId);

	List<CartItem> getAllCartItems();

	void updateCartItem(CartItem cartItem);

	void deleteCartItem(Integer ciId);
	
	CartItem getCartItemByCartIdAndProductId(Integer cartId, Integer pid);

}