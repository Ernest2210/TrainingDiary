package com.example.trainingdiary.DAO.impl;

import com.example.trainingdiary.DAO.DAO;
import com.example.trainingdiary.connectors.JDBCConnection;
import com.example.trainingdiary.mapper.impl.ApproachMapper;
import com.example.trainingdiary.models.Approach;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ApproachDAO implements DAO<Approach> {

    @Override
    public void create(Approach obj) throws SQLException {
        PreparedStatement statement = JDBCConnection.getConn().prepareCall(
                "INSERT INTO \"Approach\" (count, weight, exercise_id) " +
                        "VALUES (?, ?, ?);"
        );

        statement.setInt(1, obj.getCount());
        statement.setDouble(2, obj.getWeight());
        statement.setInt(3, obj.getExerciseId());

        statement.executeUpdate();
    }

    @Override
    public Approach get(int id) {
        try (PreparedStatement statement = JDBCConnection.getConn().prepareStatement(
                "SELECT * FROM \"Approach\" WHERE id=?;"
        )){
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                return (new ApproachMapper()).getEntity(rs);
            }else{
                return null;
            }
        }catch (SQLException e){
            return null;
        }
    }

    @Override
    public void update(Approach obj) throws SQLException {
        PreparedStatement statement = JDBCConnection.getConn().prepareCall(
                "UPDATE \"Approach\" " +
                        "SET count=?, weight=?, exercise_id=? " +
                        "WHERE id=?;"
        );
        statement.setDouble(1, obj.getCount());
        statement.setDouble(2, obj.getWeight());
        statement.setInt(3, obj.getExerciseId());
        statement.setInt(4, obj.getId());

        statement.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement statement = JDBCConnection.getConn().prepareCall(
                "DELETE FROM\"Approach\" " +
                        "WHERE id=?;"
        );

        statement.setInt(1, id);

        statement.executeUpdate();
    }

    @Override
    public List<Approach> getAll() {
        try (PreparedStatement statement = JDBCConnection.getConn().prepareStatement(
                "SELECT * FROM \"Approach\";"
        )){
            ResultSet rs = statement.executeQuery();
            ApproachMapper approachMapper = new ApproachMapper();
            List<Approach> approachList = new LinkedList<>();

            while (rs.next()){
                approachList.add(approachMapper.getEntity(rs));
            }

            return approachList;
        }catch (SQLException e){
            return null;
        }
    }

}
