package com.example.trainingdiary.DAO.impl;

import com.example.trainingdiary.DAO.DAO;
import com.example.trainingdiary.connectors.JDBCConnection;
import com.example.trainingdiary.mapper.impl.ApproachMapper;
import com.example.trainingdiary.mapper.impl.MusculeMapper;
import com.example.trainingdiary.models.Muscule;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MusculeDAO implements DAO<Muscule> {

    @Override
    public void create(Muscule obj) throws SQLException {
        PreparedStatement statement = JDBCConnection.getConn().prepareCall(
                "INSERT INTO \"Muscule\" (title) " +
                        "VALUES (?);"
        );

        statement.setString(1, obj.getTitle());

        statement.executeUpdate();
    }

    @Override
    public Muscule get(int id) {
        try (PreparedStatement statement = JDBCConnection.getConn().prepareStatement(
                "SELECT * FROM \"Muscule\" WHERE id=?;"
        )){
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                return (new MusculeMapper()).getEntity(rs);
            }else{
                return null;
            }
        }catch (SQLException e){
            return null;
        }
    }

    @Override
    public void update(Muscule obj) throws SQLException {
        PreparedStatement statement = JDBCConnection.getConn().prepareCall(
                "UPDATE \"Muscule\" " +
                        "SET title=? " +
                        "WHERE id=?;"
        );
        statement.setString(1, obj.getTitle());
        statement.setInt(2, obj.getId());

        statement.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement statement = JDBCConnection.getConn().prepareCall(
                "DELETE FROM\"Muscule\" " +
                        "WHERE id=?;"
        );

        statement.setInt(1, id);

        statement.executeUpdate();
    }

    @Override
    public List<Muscule> getAll() {
        try {
            PreparedStatement statement = JDBCConnection.getConn().prepareStatement(
                    "SELECT * FROM \"Muscule\""
            );
            ResultSet rs = statement.executeQuery();
            MusculeMapper musculeMapper = new MusculeMapper();
            List<Muscule> muscules = new LinkedList<>();

            while (rs.next()){
                muscules.add(musculeMapper.getEntity(rs));
            }
            return muscules;
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public List<Muscule> getByExerciseId(int id){
        try {
            List<Muscule> muscules = new LinkedList<>();
            PreparedStatement statement = JDBCConnection.getConn().prepareStatement(
                    "SELECT id, title FROM \"Muscule\"\n" +
                            "JOIN \"ExerciseMuscule\" ON id = muscule_id\n" +
                            "WHERE exercise_id=?;"
            );
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();
            MusculeMapper musculeMapper = new MusculeMapper();
            while (rs.next()){
                muscules.add(musculeMapper.getEntity(rs));
            }
            return muscules;
        }catch (SQLException e){
            return new ArrayList<>();
        }
    }
}
