package com.example.trainingdiary.servlets;

import com.example.trainingdiary.DAO.impl.ExerciseTypeDAO;
import com.example.trainingdiary.models.ExerciseType;
import com.example.trainingdiary.serializers.ExerciseTypeMinSerializer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExerciseTitleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String like = request.getParameter("keyword").toLowerCase();
        String json = "[]";
        if(!like.isEmpty()){
            ExerciseTypeDAO exerciseTypeDAO = new ExerciseTypeDAO();
            json = ExerciseTypeMinSerializer.serializeList(exerciseTypeDAO.getByTitleLike(like));
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
        }
        response.getWriter().write("{\"exercises\": " + json + "}");

    }
}
