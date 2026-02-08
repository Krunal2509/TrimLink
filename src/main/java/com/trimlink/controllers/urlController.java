package com.trimlink.controllers;

import java.io.IOException;

import com.trimlink.DAO.UrlDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/urlcontroller")
public class urlController extends HttpServlet  {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			HttpSession session = req.getSession(); 
			int userId =(int)session.getAttribute("user_id");
			
			String longUrl = req.getParameter("long_url");
			String slug = req.getParameter("slug");
			
			UrlDAO urlDao = new UrlDAO();
			
			urlDao.generateShortUrl(longUrl, slug, userId);
			
			resp.sendRedirect("dashboard");
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}
}
