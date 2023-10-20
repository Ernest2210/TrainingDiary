package com.example.trainingdiary.DAO.impl;

import com.example.trainingdiary.DAO.DAO;
import com.example.trainingdiary.connectors.JDBCConnection;
import com.example.trainingdiary.mapper.impl.BodyParamMapper;
import com.example.trainingdiary.mapper.impl.RememberMapper;
import com.example.trainingdiary.models.BodyParam;
import com.example.trainingdiary.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.sql.Date;
import java.util.List;

public class BodyParamDAO implements DAO<BodyParam> {
    private String type;
    public BodyParamDAO(String type){
        this.type = type;
    }
    @Override
    public void create(BodyParam obj) throws SQLException {
        PreparedStatement preparedStatement = JDBCConnection.getConn().prepareCall(
                "INSERT INTO \"" + this.type + "\" (user_id, value) VALUES (?, ?);"
        );

        preparedStatement.setInt(1, obj.getUserId());
        preparedStatement.setDouble(2, obj.getValue());

        preparedStatement.executeUpdate();
    }

    @Override
    public BodyParam get(int id) {
        return null;
    }

    @Override
    public void update(BodyParam obj) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<BodyParam> getAll() {
        return null;
    }

    public BodyParam getLastByUserId(int id){
        try {
            PreparedStatement statement = JDBCConnection.getConn().prepareStatement(
                    "SELECT * FROM \"" + this.type + "\" WHERE user_id=? ORDER BY date DESC LIMIT 1;"
            );
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                BodyParamMapper bodyParamMapper = new BodyParamMapper(this.type);
                return bodyParamMapper.getEntity(rs);
            }else{
                return null;
            }
        } catch (SQLException e) {
            return null;
        }
    }
}
