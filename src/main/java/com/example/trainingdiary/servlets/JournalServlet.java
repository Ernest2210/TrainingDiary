package com.example.trainingdiary.servlets;

import com.example.trainingdiary.utils.FreemarkerConfig;
import com.example.trainingdiary.utils.Helper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class JournalServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        HashMap<String, Object> root = Helper.getGeneralContext(request);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        Template tmpl = FreemarkerConfig.getConfig(this.getServletContext()).getTemplate("journal.ftl");
        try {
            tmpl.process(root, response.getWriter());
        } catch (TemplateException e) {
            Helper.sendRedirect(request, response, "/error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getParameter("count"));
        String[] countList = request.getParameterMap().get("count");
        String[] weightList = request.getParameterMap().get("weight");
        for(int i = 0; i < countList.length; i++){

        }
    }
}
