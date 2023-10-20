package com.example.trainingdiary.servlets;

import com.example.trainingdiary.DAO.impl.ExerciseTypeDAO;
import com.example.trainingdiary.models.ExerciseType;
import com.example.trainingdiary.utils.FreemarkerConfig;
import com.example.trainingdiary.utils.Helper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ExerciseListServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        HashMap<String, Object> root = Helper.getGeneralContext(request);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        try {
            int musculeId = Integer.parseInt(request.getParameter("muscle"));
            System.out.println(musculeId);
            ExerciseTypeDAO exerciseTypeDAO = new ExerciseTypeDAO();
            List<ExerciseType> exerciseTypeList = exerciseTypeDAO.getAllBYMuscule(musculeId);
            root.put("exerciseTypeList", exerciseTypeList);
            Template tmpl = FreemarkerConfig.getConfig(this.getServletContext()).getTemplate("exercise_list.ftl");
            tmpl.process(root, response.getWriter());

        } catch (TemplateException e) {
            throw new RuntimeException(e);
        } catch (NumberFormatException e){
            throw new RuntimeException(e);
        }
    }
}
