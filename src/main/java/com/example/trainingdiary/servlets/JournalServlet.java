package com.example.trainingdiary.servlets;

import com.example.trainingdiary.DAO.impl.ApproachDAO;
import com.example.trainingdiary.DAO.impl.ExerciseDAO;
import com.example.trainingdiary.DAO.impl.ExerciseTypeDAO;
import com.example.trainingdiary.models.Approach;
import com.example.trainingdiary.models.Exercise;
import com.example.trainingdiary.models.ExerciseType;
import com.example.trainingdiary.utils.FreemarkerConfig;
import com.example.trainingdiary.utils.Helper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
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
        request.setCharacterEncoding("UTF-8");
        try {
            System.out.println(request.getParameter("count"));
            String[] countList = request.getParameterMap().get("count");
            String[] weightList = request.getParameterMap().get("weight");
            String exerciseTitle = request.getParameter("exercise-title");
            String comment = request.getParameter("comment");
            int difficult = Integer.parseInt(request.getParameter("difficult"));
            ExerciseType exerciseType = (new ExerciseTypeDAO()).getIdByTitle(exerciseTitle);
            if (exerciseType == null) {
                Helper.sendRedirect(request, response, "/diary?exercise_not_found="+exerciseTitle);
            } else {
                Exercise exercise = new Exercise();
                exercise.setExerciseType(exerciseType);
                exercise.setDifficult(difficult);
                exercise.setDate(Date.valueOf(LocalDate.now()));
                exercise.setComment(comment);
                exercise.setUserId(Helper.getUser(request.getSession()).getId());
                ExerciseDAO exerciseDAO = new ExerciseDAO();

                exerciseDAO.create(exercise);
                exercise.setId(exerciseDAO.
                        getByUserId(Helper.getUser(request.getSession()).getId(), 1).get(0).getId());
                ApproachDAO approachDAO = new ApproachDAO();
                for (int i = 0; i < weightList.length; i++){
                    Approach approach = new Approach();
                    approach.setWeight(Double.parseDouble(weightList[i]));
                    approach.setCount(Integer.parseInt(countList[i]));
                    approach.setExerciseId(exercise.getId());
                    approachDAO.create(approach);
                }
                Helper.sendRedirect(request, response, "/diary");
            }
        } catch (SQLException e){
            Helper.sendRedirect(request, response, "/error");
        }
    }
}
