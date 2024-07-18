package com.bankingapp.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        boolean isLoggedIn = (session != null && session.getAttribute("admin") != null);
        String loginURI = httpRequest.getContextPath() + "/adminLogin.jsp";

        boolean isLoginRequest = httpRequest.getRequestURI().equals(loginURI);
        boolean isLoginPage = httpRequest.getRequestURI().endsWith("adminLogin.jsp");

        if (isLoggedIn && (isLoginRequest || isLoginPage)) {
            // The admin is already logged in and trying to access login page again
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/adminDashboard.jsp");
        } else if (isLoggedIn || isLoginRequest) {
            // The admin is already logged in or trying to log in
            chain.doFilter(request, response);
        } else {
            // The admin is not logged in, redirect to login page
            httpResponse.sendRedirect(loginURI);
        }
    }

    @Override
    public void destroy() {
        // Cleanup code
    }
}
