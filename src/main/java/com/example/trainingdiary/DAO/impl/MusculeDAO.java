package com.example.trainingdiary.DAO.impl;

import com.example.trainingdiary.DAO.DAO;
import com.example.trainingdiary.connectors.JDBCConnection;
import com.example.trainingdiary.mapper.impl.MusculeMapper;
import com.example.trainingdiary.models.Muscule;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class MusculeDAO implements DAO<Muscule> {

    @Override
    public void create(Muscule obj) throws SQLException {

    }

    @Override
    public Muscule get(int id) {
        return null;
    }

    @Override
    public void update(Muscule obj) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<Muscule> getAll() {
        List<Muscule> muscules = new LinkedList<>();
        try {
            PreparedStatement statement = JDBCConnection.getConn().prepareStatement(
                    "SELECT * FROM \"Muscule\""
            );
            ResultSet rs = statement.executeQuery();
            MusculeMapper musculeMapper = new MusculeMapper();
            while (rs.next()){
                muscules.add(musculeMapper.getEntity(rs));
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException();
        }
        return muscules;
    }
}
