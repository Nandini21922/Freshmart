package com.freshmart.dao;

import java.util.List;
import com.freshmart.dto.Orders;

public interface OrdersDAO {

	void addOrder(Orders orders);

	Orders getOrderById(Integer oid);

	List<Orders> getOrdersByCustomerId(Integer cid);

	List<Orders> getAllOrders();

	void updateOrder(Orders orders);

	void deleteOrder(Integer oid);

}