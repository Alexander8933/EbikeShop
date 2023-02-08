package com.home.eshop.dao;

import com.home.eshop.model.Bike;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BikesDaoTest {
    private BikesDao bikesDaoTest;

    @BeforeEach
    void prepare() {
        bikesDaoTest = new BikesDao();
        bikesDaoTest.loadData();

    }

    @Test
    void loadData() {
        int size1 = bikesDaoTest.loadData();
        int size2 = bikesDaoTest.bikes.size();
        assertEquals(size2, size1);
    }

    @Test
    void saveAll() {
        int size1 = bikesDaoTest.saveAll();
        int size2 = bikesDaoTest.bikes.size();
        assertEquals(size2, size1);
    }

    @Test
    void delete() {
        int size2 = (bikesDaoTest.bikes.size()) - 1;
        bikesDaoTest.delete(0);
        int size1 = bikesDaoTest.bikes.size();
        assertEquals(size2, size1);
    }

    @Test
    void save() {
        Bike bike2 = new Bike("test", 1, 1);
        int id = (bikesDaoTest.save(bike2));
        Bike bike1 = bikesDaoTest.bikes.get(id);
        int size1 = ++id;
        int size2 = bikesDaoTest.bikes.size();
        assertEquals(size2, size1);
        assertSame(bike2, bike1);
    }

    @Test
    void findOne() {
        Bike bike1 = bikesDaoTest.findOne(0);
        Bike bike2 = bikesDaoTest.bikes.get(0);
        assertSame(bike2, bike1);
    }

    @Test
    void bikesSize() {
        int size1 = bikesDaoTest.bikesSize();
        int size2 = bikesDaoTest.bikes.size();
        assertEquals(size2, size1);
    }
}