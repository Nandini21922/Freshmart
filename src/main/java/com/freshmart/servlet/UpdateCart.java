package com.freshmart.servlet;

import java.io.IOException;

import com.freshmart.dao.CartDAO;
import com.freshmart.dao.CartItemDAO;
import com.freshmart.dao.ProductDAO;
import com.freshmart.dao.impl.CartDAOImpl;
import com.freshmart.dao.impl.CartItemDAOImpl;
import com.freshmart.dao.impl.ProductDAOImpl;
import com.freshmart.dto.Cart;
import com.freshmart.dto.CartItem;
import com.freshmart.dto.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/updateCart")
public class UpdateCart extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		CartDAO cdao = new CartDAOImpl();
		CartItemDAO cidao = new CartItemDAOImpl();
		ProductDAO pdao = new ProductDAOImpl();

		Integer ciId = Integer.parseInt(req.getParameter("ciId"));
		Double quantity = Double.parseDouble(req.getParameter("quantity"));

		CartItem cartItem = cidao.getCartItemById(ciId);

		if (cartItem != null) {

			Product product = pdao.getProductById(cartItem.getPid());

			Double totalPrice = quantity * product.getPricePerKg();

			cartItem.setQuantity(quantity);
			cartItem.setTotalPrice(totalPrice);

			cidao.updateCartItem(cartItem);

			Cart cart = cdao.getCartById(cartItem.getCartId());

			Double cartTotal = 0.0;

			for (CartItem item : cidao.getCartItemsByCartId(cart.getCartId())) {

				cartTotal += item.getTotalPrice();

			}

			cart.setTotalPrice(cartTotal);

			cdao.updateCart(cart);

			req.setAttribute("success", "Cart updated successfully!");
			req.getRequestDispatcher("cart.jsp").forward(req, resp);

		} else {

			req.setAttribute("error", "Cart item not found!");
			req.getRequestDispatcher("cart.jsp").forward(req, resp);

		}

	}

}