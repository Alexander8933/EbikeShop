package com.home.eshop.dao;

import com.home.eshop.model.Bike;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BikesDaoTest {
    String path = getClass().getClassLoader().getResource("BikeBaseTestOne.txt").getPath();

    BikesDao bikesDaoTest = new BikesDao(path);

    Bike bikeOne = new Bike("testOne", 1, 1, 1);
    Bike bikeTwo = new Bike("testTwo", 2, 2, 2);
    Bike bikeOneChange = new Bike("testOneChange", 3, 3, 1);

    Bike bikeActual;

    @BeforeEach
    void prepare() {
        bikesDaoTest.save(bikeOne);
    }

    @AfterEach
    void complete() {
        bikesDaoTest.delete(bikeOne.getId());
        bikesDaoTest.delete(bikeTwo.getId());
    }

    @Test
    void change() {
        bikesDaoTest.change(bikeOneChange);
        bikeActual = bikesDaoTest.findOne(bikeOneChange.getId());
        assertEquals(bikeOneChange, bikeActual);
    }

    @Test
    void delete() {
        bikesDaoTest.delete(bikeOne.getId());
        bikeActual = bikesDaoTest.findOne(bikeOne.getId());
        assertNull(bikeActual);
    }

    @Test
    void save() {
        bikesDaoTest.save(bikeTwo);
        bikeActual = bikesDaoTest.findOne(bikeTwo.getId());
        assertEquals(bikeTwo, bikeActual);
    }

    @Test
    void findOne() {
        bikeActual = bikesDaoTest.findOne(bikeOne.getId());
        assertEquals(bikeOne, bikeActual);
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