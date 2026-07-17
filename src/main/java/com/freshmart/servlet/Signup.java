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

@WebServlet("/signup")
public class Signup extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		CustomerDAO cdao = new CustomerDAOImpl();

		Customer customer = new Customer();

		Customer alreadyExists = cdao.getCustomerByMail(req.getParameter("mail"));

		if (alreadyExists == null) {

			if (req.getParameter("password").equals(req.getParameter("confirm"))) {

				customer.setCname(req.getParameter("name"));
				customer.setPhone(Long.parseLong(req.getParameter("phone")));
				customer.setMail(req.getParameter("mail"));
				customer.setPwd(req.getParameter("password"));

				cdao.registerCustomer(customer);

				req.setAttribute("signupSuccess", "Account created successfully!");
				req.getRequestDispatcher("login.jsp").forward(req, resp);

			} else {

				req.setAttribute("signupError", "Password and Confirm Password should be same!");
				req.getRequestDispatcher("register.jsp").forward(req, resp);

			}

		} else {

			req.setAttribute("signupError", "Account already exists with this email!");
			req.getRequestDispatcher("register.jsp").forward(req, resp);

		}
	}
}