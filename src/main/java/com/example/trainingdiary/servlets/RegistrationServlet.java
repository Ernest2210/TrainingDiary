package com.example.trainingdiary.servlets;

import com.example.trainingdiary.DAO.DAO;
import com.example.trainingdiary.DAO.impl.UserDAO;
import com.example.trainingdiary.models.User;
import com.example.trainingdiary.utils.FreemarkerConfig;
import com.example.trainingdiary.utils.Hasher;
import com.example.trainingdiary.utils.Helper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;

// TODO проверка логина в js с ajax
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HashMap<String, Object> root = Helper.getGeneralContext(request);
        Boolean passwordError = (request.getParameter("passwordsNotMatch") == null) ? Boolean.FALSE : Boolean.TRUE;
        Boolean loginError = (request.getParameter("loginNotUnique") == null) ? Boolean.FALSE : Boolean.TRUE;
        root.put("passwordError", passwordError);
        root.put("loginError", loginError);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        Template tmpl = FreemarkerConfig.getConfig(this.getServletContext()).getTemplate("registration.ftl");
        try {
            tmpl.process(root, response.getWriter());
        } catch (TemplateException e) {
            Helper.sendRedirect(request, response, "/error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        request.setCharacterEncoding("UTF-8");
        if(request.getParameter("password").equals(request.getParameter("password_confirm"))){
            user.setLogin(request.getParameter("login"));
            user.setPassword(Hasher.hash(request.getParameter("password")));
            user.setBirthday(Date.valueOf(request.getParameter("birthday")));
            user.setName(request.getParameter("name"));
            user.setSurname(request.getParameter("surname"));
            user.setActive(true);
            DAO<User> dao = new UserDAO();
            try {
                dao.create(user);
            }catch (SQLException e){
                String redirectPath = request.getContextPath() + "/registration?loginNotUnique=true";
                response.sendRedirect(redirectPath);
            }
            String redirectPath = request.getContextPath() + "/login";
            response.sendRedirect(redirectPath);
        }else{
            String redirectPath = request.getContextPath() + "/registration?passwordsNotMatch=true";
            response.sendRedirect(redirectPath);
        }
    }
}
