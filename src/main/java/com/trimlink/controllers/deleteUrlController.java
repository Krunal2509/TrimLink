package com.trimlink.controllers;

import java.io.IOException;

import com.trimlink.DAO.UrlDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteurl")

public class deleteUrlController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int url_id = Integer.parseInt(req.getParameter("url_id"));
			
			UrlDAO urlDAO = new UrlDAO();
			urlDAO.deleteUrl(url_id);
			
			resp.sendRedirect("dashboard");
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
