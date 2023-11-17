package com.example.trainingdiary.DAO.impl;

import com.example.trainingdiary.DAO.DAO;
import com.example.trainingdiary.connectors.JDBCConnection;
import com.example.trainingdiary.mapper.impl.RememberMapper;
import com.example.trainingdiary.mapper.impl.UserMapper;
import com.example.trainingdiary.models.Remember;
import com.example.trainingdiary.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RememberDAO implements DAO<Remember> {
    @Override
    public void create(Remember obj) throws SQLException {
        PreparedStatement statement = JDBCConnection.getConn().prepareCall(
                "INSERT INTO \"Remember\" (uuid, user_id) " +
                        "VALUES (?, ?);"
        );

        statement.setString(1, obj.getUuid());
        statement.setInt(2, obj.getUserId());

        statement.executeUpdate();
    }

    @Override
    public Remember get(int id) {
        return null;
    }

    @Override
    public void update(Remember obj) {

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
    public List<Remember> getAll() {
        return null;
    }

    public Remember getByUser(User user){
        try {
            PreparedStatement statement = JDBCConnection.getConn().prepareStatement(
                    "SELECT * FROM Remember WHERE user_id=?"
            );
            statement.setInt(1, user.getId());
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                return (new RememberMapper()).getEntity(rs);
            }else{
                return null;
            }
        } catch (SQLException e) {
            return null;
        }
    }

    public Remember getByUUID(String UUID){
        try {
            PreparedStatement statement = JDBCConnection.getConn().prepareStatement(
                    "SELECT * FROM \"Remember\" WHERE uuid=?"
            );
            statement.setString(1, UUID);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                return (new RememberMapper()).getEntity(rs);
            }else{
                return null;
            }
        } catch (SQLException e) {
            return null;
        }
    }

    public void deleteByUUID(String UUID) throws SQLException {
        PreparedStatement statement = JDBCConnection.getConn().prepareCall(
                "DELETE FROM\"Remember\" " +
                        "WHERE uuid=?;"
        );

        statement.setString(1, UUID);

        statement.executeUpdate();
    }
}
