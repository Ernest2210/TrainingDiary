package com.example.trainingdiary.servlets;

import com.example.trainingdiary.utils.FreemarkerConfig;
import com.example.trainingdiary.utils.Helper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class ServerErrorServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        HashMap<String, Object> root = Helper.getGeneralContext(request);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        Template tmpl = FreemarkerConfig.getConfig(this.getServletContext()).getTemplate("500.ftl");
        try {
            tmpl.process(root, response.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
