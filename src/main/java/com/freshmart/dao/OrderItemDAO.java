package com.freshmart.dao;

import java.util.List;
import com.freshmart.dto.OrderItem;

public interface OrderItemDAO {

	void addOrderItem(OrderItem orderItem);

	OrderItem getOrderItemById(Integer oiId);

	List<OrderItem> getOrderItemsByOrderId(Integer oid);

	List<OrderItem> getAllOrderItems();

	void updateOrderItem(OrderItem orderItem);

	void deleteOrderItem(Integer oiId);

}