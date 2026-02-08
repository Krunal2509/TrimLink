package com.trimlink.controllers;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")     
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        System.out.println("Auth filter called");

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        // Prevent back button cache
        httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);

        String path = httpServletRequest.getRequestURI();

        // Allow login and register without session
        if (path.endsWith("login.jsp") ||
            path.endsWith("register.jsp") ||
            path.endsWith("/login") ||
            path.endsWith("/register")) {

            chain.doFilter(request, response);
            return;
        }

        HttpSession session = httpServletRequest.getSession(false);

        if (session == null || session.getAttribute("username") == null) {
            httpServletResponse.sendRedirect("login.jsp");
            return;
        }

        chain.doFilter(request, response);
    }
}
