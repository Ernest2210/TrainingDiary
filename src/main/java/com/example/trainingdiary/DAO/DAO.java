package com.example.trainingdiary.DAO;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    void create(T obj) throws SQLException;
    T get(int id);
    void update(T obj);
    void delete(long id);
    List<T> getAll();
}
