package com.example.trainingdiary.DAO.impl;

import com.example.trainingdiary.DAO.DAO;
import com.example.trainingdiary.connectors.JDBCConnection;
import com.example.trainingdiary.mapper.impl.TrainingTemplateMapper;
import com.example.trainingdiary.models.TrainingTemplate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static com.example.trainingdiary.Config.PAGE_SIZE;

public class TrainingTemplateDAO implements DAO<TrainingTemplate> {
    @Override
    public void create(TrainingTemplate obj) throws SQLException {

    }

    @Override
    public TrainingTemplate get(int id) {
        try(PreparedStatement statement = JDBCConnection.getConn().prepareStatement(
                "SELECT id, title, description, difficult, calories, \"time\" " +
                        "FROM \"TrainingTemplate\" " +
                        "WHERE id=?"
        )){
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            TrainingTemplateMapper trainingTemplateMapper = new TrainingTemplateMapper();

            if(rs.next()){
                return trainingTemplateMapper.getEntity(rs);
            }
            return null;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(TrainingTemplate obj) throws SQLException {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<TrainingTemplate> getAll() {
        return null;
    }

    public List<TrainingTemplate> getAll(int page){
        try(PreparedStatement statement = JDBCConnection.getConn().prepareStatement(
                "SELECT id, title, description, difficult, calories, time " +
                        "FROM \"TrainingTemplate\" LIMIT ? OFFSET ?"
        )){
            List<TrainingTemplate> trainingTemplateList = new LinkedList<>();
            statement.setInt(1, PAGE_SIZE);
            statement.setInt(2, page - 1);
            ResultSet rs = statement.executeQuery();
            TrainingTemplateMapper trainingTemplateMapper = new TrainingTemplateMapper();
            while (rs.next()){
                trainingTemplateList.add(trainingTemplateMapper.getEntity(rs));
            }
            return trainingTemplateList;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
