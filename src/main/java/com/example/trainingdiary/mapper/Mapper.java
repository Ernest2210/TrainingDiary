package com.example.trainingdiary.mapper;

import java.sql.ResultSet;

public interface Mapper <T>{
    T getEntity(ResultSet rs);
}
