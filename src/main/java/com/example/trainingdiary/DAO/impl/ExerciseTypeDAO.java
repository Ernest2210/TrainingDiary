package com.example.trainingdiary.DAO.impl;

import com.example.trainingdiary.DAO.DAO;
import com.example.trainingdiary.connectors.JDBCConnection;
import com.example.trainingdiary.mapper.impl.ExerciseTypeMapper;
import com.example.trainingdiary.mapper.impl.MusculeMapper;
import com.example.trainingdiary.models.ExerciseComplexity;
import com.example.trainingdiary.models.ExerciseType;
import com.example.trainingdiary.models.Muscule;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ExerciseTypeDAO implements DAO<ExerciseType> {
    @Override
    public void create(ExerciseType obj) throws SQLException {

    }

    @Override
    public ExerciseType get(int id) {
        try (PreparedStatement statement = JDBCConnection.getConn().prepareStatement(
                "SELECT \"ExerciseType\".id, \"ExerciseType\".title, photo_path, description, technique, " +
                        "\"ExerciseType\".complexity AS complexity_id, value AS complexity FROM \"ExerciseType\"\n" +
                        "LEFT JOIN \"ExerciseComplexity\" ON complexity = \"ExerciseComplexity\".id\n" +
                        "WHERE \"ExerciseType\".id=?;"
        )) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            ExerciseTypeMapper exerciseTypeMapper = new ExerciseTypeMapper();
            ExerciseType exerciseType = null;
            if (rs.next()){
                exerciseType = exerciseTypeMapper.getEntity(rs);
            }
            return exerciseType;
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    @Override
    public void update(ExerciseType obj) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<ExerciseType> getAll() {
        return null;
    }

    public List<ExerciseType> getAllBYMuscule(int muscule_id){
        List<ExerciseType> exerciseTypes = new LinkedList<>();
        try (PreparedStatement statement = JDBCConnection.getConn().prepareStatement(
                "SELECT \"ExerciseType\".id, title, photo_path, description, technique, \"ExerciseType\".complexity AS complexity_id, value AS complexity FROM \"ExerciseType\"\n" +
                        "JOIN \"ExerciseMuscule\" on id = exercise_id\n" +
                        "LEFT JOIN \"ExerciseComplexity\" ON complexity = \"ExerciseComplexity\".id\n" +
                        "WHERE muscule_id=?;"
        )) {
            statement.setInt(1, muscule_id);
            ResultSet rs = statement.executeQuery();
            ExerciseTypeMapper exerciseTypeMapper = new ExerciseTypeMapper();
            while (rs.next()){
                exerciseTypes.add(exerciseTypeMapper.getEntity(rs));
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException();
        }
        return exerciseTypes;
    }

    public List<ExerciseType> getByTitleLike(String title){
        try (PreparedStatement statement = JDBCConnection.getConn().prepareStatement(
                "SELECT id, title FROM \"ExerciseType\" WHERE LOWER(title) LIKE ?"
        )){
            List<ExerciseType> exerciseTypes = new LinkedList<>();

            statement.setString(1, "%"+title+"%");
            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                ExerciseType exerciseType = new ExerciseType();
                exerciseType.setId(rs.getInt(1));
                exerciseType.setTitle(rs.getString(2));
                exerciseTypes.add(exerciseType);
            }
            return exerciseTypes;
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public ExerciseType getIdByTitle(String title){
        try (PreparedStatement statement = JDBCConnection.getConn().prepareStatement(
                "SELECT id FROM \"ExerciseType\" WHERE LOWER(title) = ?"
        )){
            List<ExerciseType> exerciseTypes = new LinkedList<>();

            statement.setString(1, title.toLowerCase());
            ResultSet rs = statement.executeQuery();

            if (rs.next()){
                ExerciseType exerciseType = new ExerciseType();
                exerciseType.setId(rs.getInt(1));
                exerciseTypes.add(exerciseType);
                return exerciseType;
            }
            return null;
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
