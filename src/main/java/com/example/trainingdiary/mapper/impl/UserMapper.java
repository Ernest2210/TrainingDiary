package com.example.trainingdiary.mapper.impl;

import com.example.trainingdiary.mapper.Mapper;
import com.example.trainingdiary.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements Mapper<User> {
    @Override
    public User getEntity(ResultSet rs) {
        User user = new User();
        try {
            user.setId(rs.getInt(1));
            user.setName(rs.getString(2));
            user.setSurname(rs.getString(3));
            user.setLogin(rs.getString(4));
            user.setPassword(rs.getString(5));
            user.setBirthday(rs.getDate(6));
            user.setActive(rs.getBoolean(7));
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
