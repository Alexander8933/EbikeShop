package com.home.eshop.dao;

import com.home.eshop.model.Bike;

import java.sql.SQLException;
import java.util.List;

public interface Dao {

    public int create(Bike bike) throws SQLException;

    public Bike read(int id) throws SQLException;

    public int update(Bike bike) throws SQLException;

    public int delete(int id) throws SQLException;

    public List<Bike> findAll() throws SQLException;
}
