package com.example.trainingdiary.DAO.impl;

import com.example.trainingdiary.DAO.DAO;
import com.example.trainingdiary.connectors.JDBCConnection;
import com.example.trainingdiary.mapper.impl.ApproachMapper;
import com.example.trainingdiary.mapper.impl.BodyParamMapper;
import com.example.trainingdiary.mapper.impl.RememberMapper;
import com.example.trainingdiary.models.BodyParam;
import com.example.trainingdiary.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.sql.Date;
import java.util.LinkedList;
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
        try (PreparedStatement statement = JDBCConnection.getConn().prepareStatement(
                "SELECT * FROM \"" + this.type + "\" WHERE id=?;"
        )){
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                BodyParamMapper bodyParamMapper = new BodyParamMapper(this.type);
                return bodyParamMapper.getEntity(rs);
            }else{
                return null;
            }
        }catch (SQLException e){
            return null;
        }
    }

    @Override
    public void update(BodyParam obj) throws SQLException{
        PreparedStatement statement = JDBCConnection.getConn().prepareCall(
                "UPDATE \""+ this.type +"\" " +
                        "SET user_id=?, date=?, value=? " +
                        "WHERE id=?;"
        );
        statement.setInt(1, obj.getUserId());
        statement.setDate(2, obj.getDate());
        statement.setDouble(3, obj.getValue());
        statement.setInt(4, obj.getId());

        statement.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement statement = JDBCConnection.getConn().prepareCall(
                "DELETE FROM\"" + this.type + "\" " +
                        "WHERE id=?;"
        );

        statement.setInt(1, id);

        statement.executeUpdate();
    }

    @Override
    public List<BodyParam> getAll() {
        try {
            LinkedList<BodyParam> bodyParamLinkedList = new LinkedList<>();
            PreparedStatement statement = JDBCConnection.getConn().prepareStatement(
                    "SELECT * FROM \"" + this.type + "\";"
            );
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                BodyParamMapper bodyParamMapper = new BodyParamMapper(this.type);
                bodyParamLinkedList.add(bodyParamMapper.getEntity(rs));
            }
            return bodyParamLinkedList;
        } catch (SQLException e) {
            return null;
        }
    }

    public BodyParam getLastByUserId(int id){
        try {
            PreparedStatement statement = JDBCConnection.getConn().prepareStatement(
                    "SELECT * FROM \"" + this.type + "\" WHERE user_id=? ORDER BY date DESC, id DESC LIMIT 1;"
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

    public List<BodyParam> getAllByUserId(int id){
        try {
            LinkedList<BodyParam> bodyParamLinkedList = new LinkedList<>();
            PreparedStatement statement = JDBCConnection.getConn().prepareStatement(
                    "SELECT * FROM \"" + this.type + "\" WHERE user_id=? ORDER BY date;"
            );
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                BodyParamMapper bodyParamMapper = new BodyParamMapper(this.type);
                bodyParamLinkedList.add(bodyParamMapper.getEntity(rs));
            }
            return bodyParamLinkedList;
        } catch (SQLException e) {
            return null;
        }
    }
}
