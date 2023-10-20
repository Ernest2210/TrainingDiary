package com.example.trainingdiary.mapper.impl;

import com.example.trainingdiary.mapper.Mapper;
import com.example.trainingdiary.models.BodyParam;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BodyParamMapper implements Mapper<BodyParam> {
    private String type;

    public BodyParamMapper(String type){
        this.type = type;
    }
    @Override
    public BodyParam getEntity(ResultSet rs) throws SQLException {
        BodyParam bodyParam = new BodyParam(this.type);

        bodyParam.setId(rs.getInt(1));
        bodyParam.setUserId(rs.getInt(2));
        bodyParam.setDate(rs.getDate(3));
        bodyParam.setValue(rs.getDouble(4));

        return bodyParam;
    }
}
