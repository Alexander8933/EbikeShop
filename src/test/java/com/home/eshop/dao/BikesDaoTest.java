package com.home.eshop.dao;

import com.home.eshop.model.Bike;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BikesDaoTest {
    ArrayList<Bike> bikes = new ArrayList<Bike>();
    File file = new File("BikeBaseTest.txt");
    BikesDao bikesDaoTest = new BikesDao(bikes, file);

    Bike bikeOne = new Bike("testOne", 1, 1);
    Bike bikeTwo = new Bike("testTwo", 2, 2);
    Bike bikeActual;

    @BeforeEach
    void prepare() {
        bikes.add(0, bikeOne);
        bikes.add(1, bikeTwo);
    }

    @AfterEach
    void complete() {
        bikes.clear();
    }

    @Test
    void loadData() {
        bikesDaoTest.saveAll();
        bikes.clear();
        bikesDaoTest.loadData(bikes);
        bikeActual = bikes.get(0);
        assertEquals(bikeOne.getTextBikePresentation(), bikeActual.getTextBikePresentation());
    }

    @Test
    void saveAll() {
        bikesDaoTest.saveAll();
        bikes.clear();
        bikesDaoTest.loadData(bikes);
        bikeActual = bikes.get(0);
        assertEquals(bikeOne.getTextBikePresentation(), bikeActual.getTextBikePresentation());
    }

    @Test
    void delete() {
        bikesDaoTest.delete(0);
        bikeActual = bikes.get(0);
        assertNotEquals(bikeOne.getTextBikePresentation(), bikeActual.getTextBikePresentation());
    }

    @Test
    void save() {
        bikeActual = bikes.get(bikesDaoTest.save(bikeOne));
        assertEquals(bikeOne, bikeActual);
    }

    @Test
    void findOne() {
        bikeActual = bikesDaoTest.findOne(0);
        assertEquals(bikeOne, bikeActual);
    }

    @Test
    void bikesSize() {
        assertEquals(bikes.size(), bikesDaoTest.bikesSize());
    }
}