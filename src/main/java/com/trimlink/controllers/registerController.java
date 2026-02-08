package com.trimlink.controllers;

import java.io.IOException;

import com.trimlink.DAO.UserDAO;
import com.trimlink.models.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class registerController extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String userName = req.getParameter("user_name");
		String password = req.getParameter("password");
		 
		User user = new User();
		user.setUser_name(userName);
		user.setPassword(password);
		
		UserDAO userDao = new UserDAO();
		
		Boolean result = userDao.registerUser(user);
		
		System.out.println("" + result);
		
		if(result) {
			res.sendRedirect("login.jsp");
		}
		else {
			res.sendRedirect("error.jsp");
		}
	}
	
}
