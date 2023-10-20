package com.example.trainingdiary.mapper.impl;

import com.example.trainingdiary.mapper.Mapper;
import com.example.trainingdiary.models.ExerciseComplexity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExerciseComplexityMapper implements Mapper<ExerciseComplexity> {
    @Override
    public ExerciseComplexity getEntity(ResultSet rs) {
        ExerciseComplexity complexity = new ExerciseComplexity();
        try {
            complexity.setId(rs.getInt(1));
            complexity.setText(rs.getString(2));
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return complexity;
    }
}
