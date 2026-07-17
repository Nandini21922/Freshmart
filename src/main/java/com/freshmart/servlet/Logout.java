package com.freshmart.servlet;

import java.io.IOException;

import com.freshmart.dto.Customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/logout")
public class Logout extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();

		Customer user = (Customer) session.getAttribute("user");

		if (user != null) {

			session.invalidate();

			resp.sendRedirect("index.jsp");

		} else {

			req.setAttribute("loginError", "Session Expired!");
			req.getRequestDispatcher("login.jsp").forward(req, resp);

		}

	}
}