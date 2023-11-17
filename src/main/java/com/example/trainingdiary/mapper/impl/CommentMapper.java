package com.example.trainingdiary.mapper.impl;

import com.example.trainingdiary.DAO.impl.UserDAO;
import com.example.trainingdiary.mapper.Mapper;
import com.example.trainingdiary.models.Comment;
import com.example.trainingdiary.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentMapper implements Mapper<Comment> {
    @Override
    public Comment getEntity(ResultSet rs) throws SQLException {
        Comment comment = new Comment();
        comment.setId(rs.getInt(1));
        comment.setTrainingTemplateId(rs.getInt(2));
        comment.setText(rs.getString(4));
        comment.setDate(rs.getTimestamp(5));

        User user = (new UserDAO()).get(rs.getInt(3));
        comment.setUser(user);

        return comment;
    }
}
