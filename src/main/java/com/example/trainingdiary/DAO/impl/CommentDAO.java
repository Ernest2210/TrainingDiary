package com.example.trainingdiary.DAO.impl;

import com.example.trainingdiary.Config;
import com.example.trainingdiary.DAO.DAO;
import com.example.trainingdiary.connectors.JDBCConnection;
import com.example.trainingdiary.mapper.impl.ApproachMapper;
import com.example.trainingdiary.mapper.impl.CommentMapper;
import com.example.trainingdiary.mapper.impl.ExerciseMapper;
import com.example.trainingdiary.models.Approach;
import com.example.trainingdiary.models.Comment;
import com.example.trainingdiary.models.Exercise;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CommentDAO implements DAO<Comment> {
    @Override
    public void create(Comment obj) throws SQLException {
        PreparedStatement statement = JDBCConnection.getConn().prepareCall(
                "INSERT INTO \"Comment\" (training_template_id, user_id, text, publish_date) " +
                        "VALUES (?, ?, ?, ?);"
        );

        statement.setInt(1, obj.getTrainingTemplateId());
        statement.setInt(2, obj.getUser().getId());
        statement.setString(3, obj.getText());
        statement.setTimestamp(4, obj.getDate());

        statement.executeUpdate();
    }

    @Override
    public Comment get(int id) {
        try (PreparedStatement statement = JDBCConnection.getConn().prepareStatement(
                "SELECT * FROM \"Comment\" WHERE id=?;"
        )){
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                return (new CommentMapper()).getEntity(rs);
            }else{
                return null;
            }
        }catch (SQLException e){
            return null;
        }
    }

    @Override
    public void update(Comment obj) throws SQLException {
        PreparedStatement statement = JDBCConnection.getConn().prepareCall(
                "UPDATE \"Comment\" " +
                        "SET training_template_id=?, user_id=?, text=?, " +
                        "publish_date=?" +
                        "WHERE id=?;"
        );
        statement.setInt(1, obj.getTrainingTemplateId());
        statement.setInt(2, obj.getUser().getId());
        statement.setString(3, obj.getText());
        statement.setTimestamp(4, obj.getDate());
        statement.setInt(5, obj.getId());

        statement.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement statement = JDBCConnection.getConn().prepareCall(
                "DELETE FROM\"Comment\" " +
                        "WHERE id=?;"
        );

        statement.setInt(1, id);

        statement.executeUpdate();
    }

    @Override
    public List<Comment> getAll() {
        try (PreparedStatement statement = JDBCConnection.getConn().prepareStatement(
                "SELECT * FROM \"Comment\";"
        )){
            List<Comment> commentsList = new LinkedList<>();

            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                CommentMapper commentMapper = new CommentMapper();
                Comment comment = commentMapper.getEntity(rs);

                commentsList.add(comment);
            }
            return commentsList;
        }catch (SQLException e){
            return null;
        }
    }

    public List<Comment> getByTrainingTemplateId(int id, int page){
        try (
                PreparedStatement statement = JDBCConnection.getConn().prepareStatement(
                        "SELECT id, training_template_id, user_id, text, publish_date " +
                                "FROM \"Comment\" " +
                                "WHERE training_template_id=? " +
                                "ORDER BY publish_date DESC, id DESC " +
                                "LIMIT ? OFFSET ?;"
                )
        ) {
            List<Comment> commentsList = new LinkedList<>();

            statement.setInt(1, id);
            statement.setInt(2, Config.PAGE_SIZE);
            statement.setInt(3, Config.PAGE_SIZE * (page - 1));

            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                CommentMapper commentMapper = new CommentMapper();
                Comment comment = commentMapper.getEntity(rs);

                commentsList.add(comment);
            }

            return commentsList;
        } catch (SQLException e) {
            e.printStackTrace();
            return new LinkedList<>();
        }
    }
}

