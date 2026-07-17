package com.freshmart.servlet;

import java.io.IOException;

import com.freshmart.dao.ProductDAO;
import com.freshmart.dao.impl.ProductDAOImpl;
import com.freshmart.dto.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addProduct")
public class AddProduct extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ProductDAO pdao = new ProductDAOImpl();

		Product product = new Product();

		product.setPname(req.getParameter("pname"));
		product.setPricePerKg(Double.parseDouble(req.getParameter("pricePerKg")));
		product.setCatId(Integer.parseInt(req.getParameter("catId")));
		product.setDescription(req.getParameter("description"));
		product.setImageUrl(req.getParameter("imageUrl"));

		pdao.addProduct(product);

		req.setAttribute("success", "Product added successfully!");
		req.getRequestDispatcher("admin.jsp").forward(req, resp);

	}

}