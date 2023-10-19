package com.example.trainingdiary.mapper.impl;

import com.example.trainingdiary.mapper.Mapper;
import com.example.trainingdiary.models.Remember;
import com.example.trainingdiary.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RememberMapper implements Mapper<Remember> {
    @Override
    public Remember getEntity(ResultSet rs) {
        Remember remember = new Remember();
        try {
            remember.setUuid(rs.getString(2));
            remember.setUserId(rs.getInt(3));
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return remember;
    }
}
