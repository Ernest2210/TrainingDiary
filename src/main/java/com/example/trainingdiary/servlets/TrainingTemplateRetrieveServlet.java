package com.example.trainingdiary.servlets;

import com.example.trainingdiary.DAO.impl.ExerciseTypeDAO;
import com.example.trainingdiary.DAO.impl.TrainingTemplateDAO;
import com.example.trainingdiary.models.ExerciseType;
import com.example.trainingdiary.models.TrainingTemplate;
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
import java.util.List;

public class TrainingTemplateRetrieveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HashMap<String, Object> root = Helper.getGeneralContext(request);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        try {
            Integer id = Integer.parseInt(request.getParameter("id"));

            TrainingTemplateDAO trainingTemplateDAO = new TrainingTemplateDAO();
            TrainingTemplate trainingTemplate = trainingTemplateDAO.get(id);

            if(trainingTemplate == null){
                throw new IllegalArgumentException();
            }

            ExerciseTypeDAO exerciseTypeDAO = new ExerciseTypeDAO();
            List<ExerciseType> exerciseTypes = exerciseTypeDAO.getByTrainingTemplate(trainingTemplate);

            root.put("training", trainingTemplate);
            root.put("exercises", exerciseTypes);
            Template tmpl = FreemarkerConfig.getConfig(this.getServletContext()).getTemplate("trainingtemplete.ftl");
            try {
                tmpl.process(root, response.getWriter());
            } catch (TemplateException e) {
                Helper.sendRedirect(request, response, "/error");
            }
        }catch (IllegalArgumentException e){
            Helper.sendRedirect(request, response, "/notfound");
        }
    }
}
