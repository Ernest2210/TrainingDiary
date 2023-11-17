package com.example.trainingdiary.DAO.impl;

import com.example.trainingdiary.Config;
import com.example.trainingdiary.DAO.DAO;
import com.example.trainingdiary.connectors.JDBCConnection;
import com.example.trainingdiary.mapper.impl.ApproachMapper;
import com.example.trainingdiary.mapper.impl.ExerciseMapper;
import com.example.trainingdiary.models.Approach;
import com.example.trainingdiary.models.Exercise;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ExerciseDAO implements DAO<Exercise> {
    @Override
    public void create(Exercise obj) throws SQLException {
        try (PreparedStatement statement = JDBCConnection.getConn().prepareCall(
                "INSERT INTO \"Exercise\" (user_id, exercise_id, date, comment, difficult)" +
                        "VALUES (?, ?, ?, ?, ?)"
        )){
            statement.setInt(1, obj.getUserId());
            statement.setInt(2, obj.getExerciseType().getId());
            statement.setDate(3, obj.getDate());
            statement.setString(4, obj.getComment());
            statement.setInt(5, obj.getDifficult());

            statement.executeUpdate();
        }
    }

    @Override
    public Exercise get(int id) {
        try (PreparedStatement statement = JDBCConnection.getConn().prepareStatement(
                "SELECT * FROM \"Exercise\" WHERE id=?;"
        )){
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                return (new ExerciseMapper()).getEntity(rs);
            }else{
                return null;
            }
        }catch (SQLException e){
            return null;
        }
    }

    @Override
    public void update(Exercise obj) throws SQLException {
        PreparedStatement statement = JDBCConnection.getConn().prepareCall(
                "UPDATE \"Exercise\" " +
                        "SET user_id=?, exercise_id=?, date=?, " +
                        "comment=?, difficult=? " +
                        "WHERE id=?;"
        );
        statement.setInt(1, obj.getUserId());
        statement.setInt(2, obj.getExerciseType().getId());
        statement.setDate(3, obj.getDate());
        statement.setInt(4, obj.getDifficult());
        statement.setInt(5, obj.getId());

        statement.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement statement = JDBCConnection.getConn().prepareCall(
                "DELETE FROM\"Exercise\" " +
                        "WHERE id=?;"
        );

        statement.setInt(1, id);

        statement.executeUpdate();
    }

    @Override
    public List<Exercise> getAll() {
        try (PreparedStatement statement = JDBCConnection.getConn().prepareStatement(
                "SELECT * FROM \"Exercise\";"
        )){
            ResultSet rs = statement.executeQuery();
            ExerciseMapper exerciseMapper = new ExerciseMapper();
            List<Exercise> exerciseList = new LinkedList<>();

            while (rs.next()){
                exerciseList.add(exerciseMapper.getEntity(rs));
            }

            return exerciseList;
        }catch (SQLException e){
            return null;
        }
    }

    public List<Exercise> getByUserId(int user_id, int page){
        try (
                PreparedStatement statement = JDBCConnection.getConn().prepareStatement(
                "SELECT \"Exercise\".id, user_id, date, comment, difficult, exercise_id, \"ExerciseType\".title FROM \"Exercise\" " +
                        "JOIN \"ExerciseType\" ON exercise_id = \"ExerciseType\".id " +
                        "WHERE user_id=? " +
                        "ORDER BY date DESC, \"Exercise\".id DESC " +
                        "LIMIT ? OFFSET ?;"
                )
        ) {
            List<Exercise> exerciseList = new LinkedList<>();

            statement.setInt(1, user_id);
            statement.setInt(2, Config.PAGE_SIZE);
            statement.setInt(3, Config.PAGE_SIZE * (page - 1));

            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                ExerciseMapper exerciseMapper = new ExerciseMapper();
                Exercise exercise = exerciseMapper.getEntity(rs);

                PreparedStatement approachStatement = JDBCConnection.getConn().prepareStatement(
                        "SELECT * FROM \"Approach\" WHERE exercise_id=? ORDER BY id"
                );
                approachStatement.setInt(1, exercise.getId());
                ResultSet approachRs = approachStatement.executeQuery();

                List<Approach> approachList = new LinkedList<>();
                while (approachRs.next()){
                    ApproachMapper approachMapper = new ApproachMapper();
                    approachList.add(approachMapper.getEntity(approachRs));
                }

                exercise.setApproaches(approachList);

                exerciseList.add(exercise);
            }

            return exerciseList;
        } catch (SQLException e) {
            e.printStackTrace();
            return new LinkedList<>();
        }
    }
}
