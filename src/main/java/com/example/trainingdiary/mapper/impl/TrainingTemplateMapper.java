package com.example.trainingdiary.mapper.impl;

import com.example.trainingdiary.mapper.Mapper;
import com.example.trainingdiary.models.TrainingTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TrainingTemplateMapper implements Mapper<TrainingTemplate> {
    @Override
    public TrainingTemplate getEntity(ResultSet rs) throws SQLException {
        TrainingTemplate trainingTemplate = new TrainingTemplate();

        trainingTemplate.setId(rs.getInt(1));
        trainingTemplate.setTitle(rs.getString(2));
        trainingTemplate.setDescription(rs.getString(3));
        trainingTemplate.setDifficult(rs.getInt(4));
        trainingTemplate.setCalories(rs.getDouble(5));
        trainingTemplate.setTime(rs.getInt(6));

        return trainingTemplate;
    }
}
