package com.home.eshop.dao;

import com.home.eshop.model.Bike;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BikesDaoTest {
    BikesDao bikesDaoTest = new BikesDao("BikeBase.txt");

    Bike bikeOne = new Bike("testOne", 1, 1);
    Bike bikeTwo = new Bike("testTwo", 2, 2);
    Bike bikeActual;

    @BeforeEach
    void prepare() {
        bikesDaoTest.save(bikeOne);
    }

    @AfterEach
    void complete() {
        bikesDaoTest.deleteTest(bikeOne);
        bikesDaoTest.deleteTest(bikeTwo);
    }

    @Test
    void loadAndSaveData() {
        bikesDaoTest.saveData();
        bikesDaoTest.loadData();
        List<Bike> actualBikes = bikesDaoTest.findAll();

        assertThat(actualBikes)
                .isNotEmpty()
                .contains(bikeOne);
    }

    @Test
    void delete() {
        bikesDaoTest.deleteTest(bikeOne);
        bikeActual = bikesDaoTest.findOneTest(bikeOne);
        assertNull(bikeActual);
    }

    @Test
    void save() {
        bikesDaoTest.save(bikeTwo);
        bikeActual = bikesDaoTest.findOneTest(bikeTwo);
        assertEquals(bikeTwo, bikeActual);
    }

    @Test
    void findOne() {
        assertEquals(bikesDaoTest.findOneTest(bikeOne), bikeOne);
    }

    @Test
    void findAll() {
        bikesDaoTest.save(bikeTwo);
        List<Bike> actualBikes = bikesDaoTest.findAll();

        assertThat(actualBikes)
                .isNotEmpty()
                .contains(bikeOne, bikeTwo);
    }

}