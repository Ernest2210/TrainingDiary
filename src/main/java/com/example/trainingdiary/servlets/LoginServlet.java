package com.example.trainingdiary.servlets;

import com.example.trainingdiary.DAO.impl.RememberDAO;
import com.example.trainingdiary.DAO.impl.UserDAO;
import com.example.trainingdiary.models.Remember;
import com.example.trainingdiary.models.User;
import com.example.trainingdiary.utils.FreemarkerConfig;
import com.example.trainingdiary.utils.Hasher;
import com.example.trainingdiary.utils.Helper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HashMap<String, Object> root = Helper.getGeneralContext(request);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        Template tmpl = FreemarkerConfig.getConfig(this.getServletContext()).getTemplate("login.ftl");
        try {
            tmpl.process(root, response.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String login = request.getParameter("login");
        String password = Hasher.hash(request.getParameter("password"));
        Boolean remember = (request.getParameter("remember") == null) ? Boolean.FALSE : Boolean.TRUE;
        UserDAO dao = new UserDAO();
        User user = dao.getByLogin(login);
        if(user.getPassword().equals(password)){
            HttpSession session = request.getSession();
            session.setAttribute("User", user);
            System.out.println(remember);
            if(remember){
                boolean uniqUUID = false;
                Remember rememberUUID = new Remember(user.getId());
                while (!uniqUUID){
                    RememberDAO rememberDAO = new RememberDAO();
                    try {
                        rememberDAO.create(rememberUUID);
                        uniqUUID = true;
                    }catch (SQLException e){
                        e.printStackTrace();
                        rememberUUID = new Remember(user.getId());
                    }
                }

                Cookie rememberMeCookie = new Cookie("Rem", rememberUUID.getUuid());
                rememberMeCookie.setMaxAge(20*365*24*60*60);
                response.addCookie(rememberMeCookie);
            }
            String path = request.getContextPath() + "/";
            response.sendRedirect(path);
        }

    }
}
