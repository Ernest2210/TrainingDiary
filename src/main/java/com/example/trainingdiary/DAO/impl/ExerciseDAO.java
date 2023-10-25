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

    }

    @Override
    public Exercise get(int id) {
        return null;
    }

    @Override
    public void update(Exercise obj) throws SQLException {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<Exercise> getAll() {
        return null;
    }

    public List<Exercise> getByUserId(int user_id, int page){
        try (
                PreparedStatement statement = JDBCConnection.getConn().prepareStatement(
                "SELECT \"Exercise\".id, user_id, date, comment, difficult, exercise_id, \"ExerciseType\".title FROM \"Exercise\" " +
                        "JOIN \"ExerciseType\" ON exercise_id = \"ExerciseType\".id " +
                        "WHERE user_id=? " +
                        "ORDER BY date DESC " +
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
