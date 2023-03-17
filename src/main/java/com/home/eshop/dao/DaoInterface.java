package com.home.eshop.dao;

import com.home.eshop.model.Bike;

import java.util.List;

public interface DaoInterface {

    public int change(Bike bike);

    public void delete(int id);

    public int save(Bike bike);

    public Bike findOne(int id);

    public List<Bike> findAll();
}
