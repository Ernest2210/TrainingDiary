package com.example.trainingdiary.servlets;

import com.example.trainingdiary.DAO.impl.CommentDAO;
import com.example.trainingdiary.DAO.impl.TrainingTemplateDAO;
import com.example.trainingdiary.models.Comment;
import com.example.trainingdiary.models.TrainingTemplate;
import com.example.trainingdiary.models.User;
import com.example.trainingdiary.serializers.CommentSerializer;
import com.example.trainingdiary.serializers.TrainingMinSerializer;
import com.example.trainingdiary.utils.Helper;
import freemarker.template.utility.NullArgumentException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class CommentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page;
        int trainingId;
        trainingId = Integer.parseInt(request.getParameter("training"));
        try {
            page = Integer.parseInt(request.getParameter("page"));
        }catch (NumberFormatException e){
            page = 1;
        }

        CommentDAO commentDAO = new CommentDAO();
        List<Comment> commentList = commentDAO.getByTrainingTemplateId(trainingId, page);
        String json = "{ \"comments\": " + CommentSerializer.serializeList(commentList) + "}";
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        try {
            String text = request.getParameter("text");
            if(text == null){
                throw new NullArgumentException();
            }
            Integer trainingId = Integer.parseInt(request.getParameter("training"));

            Comment comment = new Comment();
            comment.setText(text);
            comment.setTrainingTemplateId(trainingId);
            comment.setUser(Helper.getUser(request.getSession()));
            comment.setDate(new Timestamp(System.currentTimeMillis()));

            CommentDAO commentDAO = new CommentDAO();
            commentDAO.create(comment);

            response.setStatus(200);
            String json = CommentSerializer.serialize(comment);
            response.getWriter().write(json);
        }catch (NumberFormatException e){
            response.setStatus(400);
            response.getWriter().write("{\"detail\": \"param training should be integer\"}");
        }catch (NullArgumentException e){
            response.setStatus(400);
            response.getWriter().write("{\"detail\": \"param text require\"}");
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(500);
        }
    }
}
