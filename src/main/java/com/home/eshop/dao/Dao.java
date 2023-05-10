package com.home.eshop.dao;

import com.home.eshop.model.Bike;

import java.util.List;

public interface Dao {

    public int create(Bike bike);

    public Bike read(int id);

    public int update(Bike bike);

    public int delete(int id);

    public List<Bike> findAll();
}
