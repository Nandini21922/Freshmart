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

@WebServlet("/updateProduct")
public class UpdateProduct extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ProductDAO pdao = new ProductDAOImpl();

		Integer pid = Integer.parseInt(req.getParameter("pid"));

		Product product = pdao.getProductById(pid);

		if (product != null) {

			product.setPname(req.getParameter("pname"));
			product.setPricePerKg(Double.parseDouble(req.getParameter("pricePerKg")));
			product.setCatId(Integer.parseInt(req.getParameter("catId")));
			product.setDescription(req.getParameter("description"));
			product.setImageUrl(req.getParameter("imageUrl"));

			pdao.updateProduct(product);

			req.setAttribute("success", "Product updated successfully!");
			req.getRequestDispatcher("admin.jsp").forward(req, resp);

		} else {

			req.setAttribute("error", "Product not found!");
			req.getRequestDispatcher("admin.jsp").forward(req, resp);

		}

	}
}