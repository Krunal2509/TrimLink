package com.trimlink.controllers;

import java.io.IOException;

import com.trimlink.DAO.UrlDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/redirectShortUrl/*")
public class redirectShortUrlController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pathInfo = req.getPathInfo();
		
		if(pathInfo == null || pathInfo.equals("/")) {
			return;
		}
		
		String slug = pathInfo.substring(1);
		
		UrlDAO urlDao = new UrlDAO();
		
		String long_url = urlDao.getLongUrl(slug);
		
		if(long_url != null) {
			resp.sendRedirect(long_url);
		}
		else {
			System.out.println("Errror in redirect short url controller");
		}
		
	}
}
