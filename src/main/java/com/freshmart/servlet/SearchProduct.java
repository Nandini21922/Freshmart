package com.freshmart.servlet;

import java.io.IOException;
import java.util.List;

import com.freshmart.dao.ProductDAO;
import com.freshmart.dao.impl.ProductDAOImpl;
import com.freshmart.dto.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/searchProduct")
public class SearchProduct extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		ProductDAO pdao = new ProductDAOImpl();

		String keyword = req.getParameter("keyword");

		List<Product> productList = pdao.searchProducts(keyword);

		req.setAttribute("productList", productList);

		req.getRequestDispatcher("customerHome.jsp").forward(req, resp);
	}
}