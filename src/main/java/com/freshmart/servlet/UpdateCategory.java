package com.freshmart.servlet;

import java.io.IOException;

import com.freshmart.dao.CategoryDAO;
import com.freshmart.dao.impl.CategoryDAOImpl;
import com.freshmart.dto.Category;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/updateCategory")
public class UpdateCategory extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		CategoryDAO cdao = new CategoryDAOImpl();

		Integer catId = Integer.parseInt(req.getParameter("catId"));

		Category category = cdao.getCategoryById(catId);

		if (category != null) {

			category.setCatName(req.getParameter("categoryName"));

			cdao.updateCategory(category);

			req.setAttribute("success", "Category updated successfully!");
			req.getRequestDispatcher("admin.jsp").forward(req, resp);

		} else {

			req.setAttribute("error", "Category not found!");
			req.getRequestDispatcher("admin.jsp").forward(req, resp);

		}

	}

}