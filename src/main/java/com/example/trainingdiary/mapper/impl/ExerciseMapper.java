package com.example.trainingdiary.mapper.impl;

import com.example.trainingdiary.mapper.Mapper;
import com.example.trainingdiary.models.Exercise;
import com.example.trainingdiary.models.ExerciseComplexity;
import com.example.trainingdiary.models.ExerciseType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExerciseMapper implements Mapper<Exercise> {
    @Override
    public Exercise getEntity(ResultSet rs) throws SQLException {
        try {
            Exercise exercise = new Exercise();
            ExerciseType exerciseType = new ExerciseType();

            exerciseType.setId(rs.getInt(6));
            exerciseType.setTitle(rs.getString(7));

            exercise.setId(rs.getInt(1));
            exercise.setUserId(rs.getInt(2));
            exercise.setDate(rs.getDate(3));
            exercise.setComment(rs.getString(4));
            exercise.setDifficult(rs.getInt(5));

            exercise.setExerciseType(exerciseType);
            return exercise;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
