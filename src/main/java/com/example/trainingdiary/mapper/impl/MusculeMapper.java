package com.example.trainingdiary.mapper.impl;

import com.example.trainingdiary.mapper.Mapper;
import com.example.trainingdiary.models.Muscule;
import com.example.trainingdiary.models.Remember;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MusculeMapper implements Mapper<Muscule> {
    @Override
    public Muscule getEntity(ResultSet rs) {
        Muscule muscule = null;
        try {
            muscule = new Muscule(rs.getInt(1), rs.getString(2));
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return muscule;
    }
}
