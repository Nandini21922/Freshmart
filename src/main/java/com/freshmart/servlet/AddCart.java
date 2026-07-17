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
import com.freshmart.dto.Customer;
import com.freshmart.dto.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/addCart")
public class AddCart extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();

		Customer customer = (Customer) session.getAttribute("user");

		if (customer != null) {

			CartDAO cdao = new CartDAOImpl();
			CartItemDAO cidao = new CartItemDAOImpl();
			ProductDAO pdao = new ProductDAOImpl();

			Integer pid = Integer.parseInt(req.getParameter("pid"));
			Double quantity = Double.parseDouble(req.getParameter("quantity"));

			Product product = pdao.getProductById(pid);

			if (product != null) {

				Cart cart = cdao.getCartByCustomerId(customer.getCid());

				// Create cart if it doesn't exist
				if (cart == null) {

					cart = new Cart();
					cart.setCid(customer.getCid());
					cart.setTotalPrice(0.0);

					cdao.addCart(cart);

					cart = cdao.getCartByCustomerId(customer.getCid());
				}

				// Check whether the product already exists in the cart
				CartItem cartItem = cidao.getCartItemByCartIdAndProductId(cart.getCartId(), pid);

				Double itemTotal = quantity * product.getPricePerKg();

				if (cartItem == null) {

					cartItem = new CartItem();

					cartItem.setCartId(cart.getCartId());
					cartItem.setPid(pid);
					cartItem.setQuantity(quantity);
					cartItem.setTotalPrice(itemTotal);

					cidao.addCartItem(cartItem);

				} else {

					cartItem.setQuantity(cartItem.getQuantity() + quantity);
					cartItem.setTotalPrice(cartItem.getQuantity() * product.getPricePerKg());

					cidao.updateCartItem(cartItem);
				}

				// Recalculate total cart price
				Double cartTotal = 0.0;

				for (CartItem item : cidao.getCartItemsByCartId(cart.getCartId())) {

					cartTotal += item.getTotalPrice();

				}

				cart.setTotalPrice(cartTotal);

				cdao.updateCart(cart);

				req.setAttribute("success", "Product added to cart successfully!");
				req.getRequestDispatcher("customerHome.jsp").forward(req, resp);

			} else {

				req.setAttribute("error", "Product not found!");
				req.getRequestDispatcher("customerHome.jsp").forward(req, resp);

			}

		} else {

			req.setAttribute("loginError", "Please login first!");
			req.getRequestDispatcher("login.jsp").forward(req, resp);

		}
	}
}