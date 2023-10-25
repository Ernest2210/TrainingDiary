package com.example.trainingdiary.mapper.impl;

import com.example.trainingdiary.mapper.Mapper;
import com.example.trainingdiary.models.Approach;
import com.example.trainingdiary.models.ExerciseComplexity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ApproachMapper implements Mapper<Approach> {
    @Override
    public Approach getEntity(ResultSet rs) throws SQLException {
        Approach approach = new Approach();
        try {
            approach.setId(rs.getInt(1));
            approach.setCount(rs.getInt(2));
            approach.setWeight(rs.getDouble(3));
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return approach;
    }
}
