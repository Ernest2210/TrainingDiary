package com.example.trainingdiary.servlets;

import com.example.trainingdiary.DAO.impl.RememberDAO;
import com.example.trainingdiary.models.Remember;
import com.example.trainingdiary.utils.Helper;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class LogoutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("User");
        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;
        for(Cookie i: cookies){
            if("Rem".equals(i.getName())){
                cookie = i;
            }
        }
        if (cookie != null){
            RememberDAO rememberDAO = new RememberDAO();
            try {
                rememberDAO.deleteByUUID(cookie.getValue());
            } catch (SQLException e) {
                Helper.sendRedirect(request, response, "/error");
            }
        }
        cookie = new Cookie("Rem", "");
        cookie.setMaxAge(1);
        response.addCookie(cookie);
        Helper.sendRedirect(request, response, "/");
    }
}
