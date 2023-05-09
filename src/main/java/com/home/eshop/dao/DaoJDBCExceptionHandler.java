package com.home.eshop.dao;

import com.home.eshop.model.Bike;

import java.sql.SQLException;
import java.util.List;

public class DaoJDBCExceptionHandler implements Dao {
    private final Dao dao;

    DaoJDBCExceptionHandler(DaoJDBC daoJDBC) {
        this.dao = daoJDBC;
    }

    @Override
    public int create(Bike bike) {
        try {
            return dao.create(bike);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to Create bike", e);
        }
    }

    @Override
    public Bike read(int id) {
        try {
            return dao.read(id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to Read bike", e);
        }
    }

    @Override
    public int update(Bike bike) {
        try {
            return dao.update(bike);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to Update bike", e);
        }
    }

    @Override
    public int delete(int id) {
        try {
            return dao.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to Delete bike", e);
        }
    }

    @Override
    public List<Bike> findAll() {
        try {
            return dao.findAll();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to FindAll bikes", e);
        }
    }
}