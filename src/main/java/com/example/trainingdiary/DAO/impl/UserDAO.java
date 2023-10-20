package com.example.trainingdiary.DAO.impl;

import com.example.trainingdiary.DAO.DAO;
import com.example.trainingdiary.connectors.JDBCConnection;
import com.example.trainingdiary.mapper.Mapper;
import com.example.trainingdiary.mapper.impl.UserMapper;
import com.example.trainingdiary.models.User;
import com.example.trainingdiary.utils.Helper;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class UserDAO implements DAO<User> {
    @Override
    public void create(User obj) throws SQLException {


        PreparedStatement statement = JDBCConnection.getConn().prepareCall(
                "INSERT INTO \"User\" (name, surname, login, password, birthday, is_active) " +
                        "VALUES (?, ?, ?, ?, ?, ?);"
        );

        statement.setString(1, obj.getName());
        statement.setString(2, obj.getSurname());
        statement.setString(3, obj.getLogin());
        statement.setString(4, obj.getPassword());
        statement.setDate(5, obj.getBirthday());
        statement.setBoolean(6, obj.isActive());

        statement.executeUpdate();
    }

    public User getByLogin(String login){
        try {
            PreparedStatement statement = JDBCConnection.getConn().prepareStatement(
                    "SELECT * FROM \"User\" WHERE login=?"
            );
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                return (new UserMapper()).getEntity(rs);
            }else{
                return null;
            }
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public User get(int id) {
        try {
            PreparedStatement statement = JDBCConnection.getConn().prepareStatement(
                    "SELECT * FROM \"User\" WHERE id=?"
            );
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                return (new UserMapper()).getEntity(rs);
            }else{
                return null;
            }
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public void update(User obj) throws SQLException {
        PreparedStatement statement = JDBCConnection.getConn().prepareCall(
                "UPDATE \"User\" " +
                        "SET name=?, surname=?, birthday=? " +
                        "WHERE id=?;"
        );
        statement.setString(1, obj.getName());
        statement.setString(2, obj.getSurname());
        statement.setDate(3, obj.getBirthday());
        statement.setInt(4, obj.getId());

        statement.executeUpdate();
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
