package com.example.trainingdiary.servlets;

import com.example.trainingdiary.DAO.impl.BodyParamDAO;
import com.example.trainingdiary.DAO.impl.UserDAO;
import com.example.trainingdiary.models.BodyParam;
import com.example.trainingdiary.models.User;
import com.example.trainingdiary.utils.FreemarkerConfig;
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
import java.util.LinkedList;
import java.util.List;

public class ProfileServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        HashMap<String, Object> root = Helper.getGeneralContext(request);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        for(String type : BodyParam.TYPES){
            BodyParamDAO bodyParamDAO = new BodyParamDAO(type);
            BodyParam bodyParam =
                    bodyParamDAO.getLastByUserId(Helper.getUser(request.getSession()).getId());
            root.put(type.toLowerCase(), bodyParam);
        }

        Template tmpl = FreemarkerConfig.getConfig(this.getServletContext()).getTemplate("profile.ftl");
        try {
            tmpl.process(root, response.getWriter());
        } catch (TemplateException e) {
            Helper.sendRedirect(request, response, "/error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String userName = request.getParameter("name");
        String userSurname = request.getParameter("surname");
        Date userBirthday = Date.valueOf(request.getParameter("birthday"));
        User user = Helper.getUser(request.getSession());
        user.setName(userName);
        user.setSurname(userSurname);
        user.setBirthday(userBirthday);


        List<BodyParam> bodyParamList = new LinkedList<>();
        for (String type: BodyParam.TYPES){
            String value = request.getParameter(type.toLowerCase());
            String oldValue = request.getParameter("old_" + type.toLowerCase());
            if(!value.equals(oldValue)){
                BodyParam bodyParam = new BodyParam(type);
                bodyParam.setValue(Double.parseDouble(value));
                bodyParam.setUserId(user.getId());
                bodyParamList.add(bodyParam);
            }
        }

        UserDAO userDAO = new UserDAO();
        try {
            userDAO.update(user);

            for(BodyParam bodyParam: bodyParamList){
                BodyParamDAO bodyParamDAO = new BodyParamDAO(bodyParam.getType());
                bodyParamDAO.create(bodyParam);
            }

            Helper.sendRedirect(request, response, "/profile");
        } catch (SQLException e) {
            e.printStackTrace();
            Helper.sendRedirect(request, response, "/error");
        }


    }
}
