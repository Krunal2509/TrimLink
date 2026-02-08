package com.trimlink.controllers;

import java.io.IOException;

import com.trimlink.DAO.UserDAO;
import com.trimlink.models.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

@WebServlet("/login")
public class loginController extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String userName = req.getParameter("user_name");
		String password = req.getParameter("password");
		 
		User user = new User();
		user.setUser_name(userName);
		user.setPassword(password);
		
		UserDAO userDao = new UserDAO();
		
		User currUser = userDao.loginUser(user);
		
		
		if(currUser != null) {
			HttpSession session = req.getSession(true);
			session.setAttribute("username", userName);
			session.setAttribute("user_id", user.getUser_id());
			
			res.sendRedirect("dashboard");
		}
		else {
			res.sendRedirect("error.jsp");
		}
	}
	
}
