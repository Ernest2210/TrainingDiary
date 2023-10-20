package com.example.trainingdiary.mapper.impl;

import com.example.trainingdiary.mapper.Mapper;
import com.example.trainingdiary.models.ExerciseComplexity;
import com.example.trainingdiary.models.ExerciseType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExerciseTypeMapper implements Mapper<ExerciseType> {
    @Override
    public ExerciseType getEntity(ResultSet rs) {
        try {
            ExerciseComplexity exerciseComplexity = new ExerciseComplexity();
            exerciseComplexity.setId(rs.getInt(6));
            exerciseComplexity.setText(rs.getString(7));


            ExerciseType exerciseType = new ExerciseType();
            exerciseType.setId(rs.getInt(1));
            exerciseType.setTitle(rs.getString(2));
            exerciseType.setPhotoPath(rs.getString(3));
            exerciseType.setDescription(rs.getString(4));
            exerciseType.setTechnique(rs.getString(5));
            exerciseType.setComplexity(exerciseComplexity);
            return exerciseType;
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
