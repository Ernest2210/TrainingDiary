package com.example.trainingdiary.DAO.impl;

import com.example.trainingdiary.DAO.DAO;
import com.example.trainingdiary.connectors.JDBCConnection;
import com.example.trainingdiary.models.Approach;

import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        return null;
    }

    @Override
    public void update(Approach obj) throws SQLException {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<Approach> getAll() {
        return null;
    }

}
