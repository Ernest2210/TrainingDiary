package com.example.trainingdiary.servlets;

import com.example.trainingdiary.DAO.impl.ExerciseDAO;
import com.example.trainingdiary.DAO.impl.TrainingTemplateDAO;
import com.example.trainingdiary.models.Exercise;
import com.example.trainingdiary.models.TrainingTemplate;
import com.example.trainingdiary.serializers.ExerciseSerializer;
import com.example.trainingdiary.serializers.TrainingMinSerializer;
import com.example.trainingdiary.utils.Helper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TrainingTemplateDataServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        }catch (NumberFormatException e){
            page = 1;
        }

        TrainingTemplateDAO trainingTemplateDAO = new TrainingTemplateDAO();
        List<TrainingTemplate> trainingTemplateList = trainingTemplateDAO.getAll(page);
        String json = "{ \"trainings\": " + TrainingMinSerializer.serializeList(trainingTemplateList) + "}";
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(json);
    }
}
