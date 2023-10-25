package com.example.trainingdiary.servlets;

import com.example.trainingdiary.DAO.impl.ExerciseTypeDAO;
import com.example.trainingdiary.DAO.impl.MusculeDAO;
import com.example.trainingdiary.models.ExerciseType;
import com.example.trainingdiary.models.Muscule;
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

public class ExerciseServlet extends HttpServlet {
    // TODO выводить сложность тут и в ExerciseList
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        HashMap<String, Object> root = Helper.getGeneralContext(request);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        try {
            int exerciseId = Integer.parseInt(request.getParameter("id"));
            ExerciseTypeDAO exerciseTypeDAO = new ExerciseTypeDAO();
            ExerciseType exerciseType = exerciseTypeDAO.get(exerciseId);
            if(exerciseType == null){
                throw new NumberFormatException();
            }

            MusculeDAO musculeDAO = new MusculeDAO();
            List<Muscule> muscules = musculeDAO.getByExerciseId(exerciseId);

            root.put("exercise", exerciseType);
            root.put("muscules", muscules);

            Template tmpl = FreemarkerConfig.getConfig(this.getServletContext()).getTemplate("exercise.ftl");
            tmpl.process(root, response.getWriter());

        }catch (NumberFormatException e){
            Helper.sendRedirect(request, response, "/notfound");
        }catch (TemplateException e){
            Helper.sendRedirect(request, response, "/error");
        }
    }
}
