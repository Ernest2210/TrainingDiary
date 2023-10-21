package com.example.trainingdiary.servlets;

import com.example.trainingdiary.utils.Helper;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("User");
        Cookie cookie = new Cookie("Rem", "");
        cookie.setMaxAge(1);
        response.addCookie(cookie);
        Helper.sendRedirect(request, response, "/");
    }
}
