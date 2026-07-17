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

@WebServlet("/addCategory")
public class AddCategory extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		CategoryDAO cdao = new CategoryDAOImpl();

		Category category = new Category();

		category.setCatName(req.getParameter("categoryName"));

		cdao.addCategory(category);

		req.setAttribute("success", "Category added successfully!");
		req.getRequestDispatcher("admin.jsp").forward(req, resp);

	}

}