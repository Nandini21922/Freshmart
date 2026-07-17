package com.freshmart.servlet;

import java.io.IOException;

import com.freshmart.dao.CustomerDAO;
import com.freshmart.dao.impl.CustomerDAOImpl;
import com.freshmart.dto.Customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class Login extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		CustomerDAO cdao = new CustomerDAOImpl();

		Customer login = cdao.loginCustomer(req.getParameter("mail"), req.getParameter("password"));

		if (login != null) {

			HttpSession session = req.getSession();
			session.setAttribute("user", login);

			if (login.getCid() == 1) {

				resp.sendRedirect("admin/admin.jsp");

			} else {

				resp.sendRedirect("customer/customerHome.jsp");

			}

		} else {

			req.setAttribute("loginError", "Invalid Email or Password!");
			req.getRequestDispatcher("login.jsp").forward(req, resp);

		}
	}
}
