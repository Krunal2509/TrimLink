package com.trimlink.controllers;

import java.io.IOException;
import java.util.List;

import com.trimlink.DAO.UrlDAO;
import com.trimlink.models.Url;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/dashboard")

public class dashboardController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			HttpSession session = req.getSession(false);
			
			UrlDAO urlDao = new UrlDAO();
			List<Url> listOfUrls = urlDao.getAllUrls((int)session.getAttribute("user_id"));
			
			req.setAttribute("urls", listOfUrls );
			req.getRequestDispatcher("dashboard.jsp").forward(req, resp);
			
		}catch (Exception e) {
			System.out.println("Error in dashboard Controller");
		}
	}
}
