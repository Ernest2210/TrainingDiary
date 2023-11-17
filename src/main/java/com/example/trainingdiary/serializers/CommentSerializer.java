package com.example.trainingdiary.serializers;

import com.example.trainingdiary.DAO.impl.CommentDAO;
import com.example.trainingdiary.models.Comment;
import com.example.trainingdiary.models.Exercise;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class CommentSerializer {
    public static String serialize(Comment comment){
        String json = "{ ";
        json += "\"id\": " + comment.getId() + ", ";
        json += "\"user_name\": \"" + comment.getUser().getName() + "\", ";
        json += "\"date\": \"" + comment.getDate().toLocalDateTime().format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "\", ";
        json += "\"text\": \"" + comment.getText() + "\", ";
        json += "\"training_template_id\": " + comment.getTrainingTemplateId();
        json += "}";
        return json;
    }

    public static String serializeList(List<Comment> commentList){
        String json = "[";
        for (int i = 0; i < commentList.size(); i++){
            json += serialize(commentList.get(i));
            if(i < commentList.size() - 1){
                json += ", ";
            }
        }
        json += " ] ";
        return json;
    }
}
