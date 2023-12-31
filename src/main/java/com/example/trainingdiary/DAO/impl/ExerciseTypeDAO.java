package com.example.trainingdiary.DAO.impl;

import com.example.trainingdiary.DAO.DAO;
import com.example.trainingdiary.connectors.JDBCConnection;
import com.example.trainingdiary.mapper.impl.ExerciseTypeMapper;
import com.example.trainingdiary.mapper.impl.MusculeMapper;
import com.example.trainingdiary.models.ExerciseComplexity;
import com.example.trainingdiary.models.ExerciseType;
import com.example.trainingdiary.models.Muscule;
import com.example.trainingdiary.models.TrainingTemplate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ExerciseTypeDAO implements DAO<ExerciseType> {
    @Override
    public void create(ExerciseType obj) throws SQLException {
        PreparedStatement statement = JDBCConnection.getConn().prepareCall(
                "INSERT INTO \"ExerciseType\" (title, photo_path, description, " +
                        "complexity, texhnique) " +
                        "VALUES (?, ?, ?, ?, ?);"
        );

        statement.setString(1, obj.getTitle());
        statement.setString(2, obj.getPhotoPath());
        statement.setString(3, obj.getDescription());
        statement.setInt(4, obj.getComplexity().getId());
        statement.setString(5, obj.getTechnique());

        statement.executeUpdate();
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
    public void update(ExerciseType obj) throws SQLException {
        PreparedStatement statement = JDBCConnection.getConn().prepareCall(
                "UPDATE \"ExerciseType\" " +
                        "SET title=?, photo_path=?, description=?, " +
                        "complexity=?, technique=?" +
                        "WHERE id=?;"
        );
        statement.setString(1, obj.getTitle());
        statement.setString(2, obj.getPhotoPath());
        statement.setString(3, obj.getDescription());
        statement.setInt(4, obj.getComplexity().getId());
        statement.setString(5, obj.getTechnique());
        statement.setInt(6, obj.getId());

        statement.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement statement = JDBCConnection.getConn().prepareCall(
                "DELETE FROM\"ExerciseType\" " +
                        "WHERE id=?;"
        );

        statement.setInt(1, id);

        statement.executeUpdate();
    }

    @Override
    public List<ExerciseType> getAll() {
        try (PreparedStatement statement = JDBCConnection.getConn().prepareStatement(
                "SELECT \"ExerciseType\".id, \"ExerciseType\".title, photo_path, description, technique, " +
                        "\"ExerciseType\".complexity AS complexity_id, value AS complexity FROM \"ExerciseType\"\n" +
                        "LEFT JOIN \"ExerciseComplexity\" ON complexity = \"ExerciseComplexity\".id\n;"
        )) {
            ResultSet rs = statement.executeQuery();
            ExerciseTypeMapper exerciseTypeMapper = new ExerciseTypeMapper();
            List<ExerciseType> typeList = new LinkedList<>();
            while (rs.next()){
                typeList.add(exerciseTypeMapper.getEntity(rs));
            }
            return typeList;
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException();
        }
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
            return new LinkedList<>();
        }
    }

    public ExerciseType getIdByTitle(String title){
        try (PreparedStatement statement = JDBCConnection.getConn().prepareStatement(
                "SELECT id FROM \"ExerciseType\" WHERE LOWER(title) = ?"
        )){
            statement.setString(1, title.toLowerCase());
            ResultSet rs = statement.executeQuery();

            if (rs.next()){
                ExerciseType exerciseType = new ExerciseType();
                exerciseType.setId(rs.getInt(1));
                return exerciseType;
            }
            return null;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public List<ExerciseType> getByTrainingTemplate(TrainingTemplate trainingTemplate){
        try (PreparedStatement statement = JDBCConnection.getConn().prepareStatement(
                "SELECT id, title FROM \"ExerciseType\", \"TrainingExercise\" " +
                        "WHERE training_id=? " +
                        "AND exercise_id=id;"
        )){
            List<ExerciseType> exerciseTypes = new LinkedList<>();

            statement.setInt(1, trainingTemplate.getId());
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
            return new LinkedList<>();
        }
    }
}
