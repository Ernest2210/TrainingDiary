package com.example.trainingdiary.servlets;

import com.example.trainingdiary.DAO.impl.UserDAO;
import com.example.trainingdiary.models.User;
import freemarker.template.utility.NullArgumentException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UniqueLoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        try {
            String login = request.getParameter("login");
            if(login == null){
                throw new NullArgumentException();
            }
            UserDAO userDAO = new UserDAO();
            User user = userDAO.getByLogin(login);

            String json = "{\"unique\": ";

            if (user == null){
                json += "true";
            }else{
                json += "false";
            }

            json += "}";
            response.setStatus(200);
            response.getWriter().write(json);
        }catch (NullArgumentException e){
            response.setStatus(400);
            response.getWriter().write("{\"detail\": \"param login require\"}");
        }
    }
}
