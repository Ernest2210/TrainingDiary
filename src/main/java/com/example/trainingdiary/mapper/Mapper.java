package com.example.trainingdiary.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Mapper <T>{
    T getEntity(ResultSet rs) throws SQLException;
}
