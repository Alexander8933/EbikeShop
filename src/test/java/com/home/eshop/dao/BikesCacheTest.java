package com.home.eshop.dao;

import com.home.eshop.model.Bike;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BikesCacheTest {
    String path = getClass().getClassLoader().getResource("BikeBaseTestOne.txt").getPath();

    Dao bikesCache = new BikesCache(path);

    Bike bikeOne = new Bike("testOne", 1, 1, 1);
    Bike bikeTwo = new Bike("testTwo", 2, 2, 2);
    Bike bikeOneChange = new Bike("testOneChange", 3, 3, 1);

    Bike bikeActual;

    @BeforeEach
    void prepare() {
        bikesCache.save(bikeOne);
    }

    @AfterEach
    void complete() {
        bikesCache.delete(bikeOne.getId());
        bikesCache.delete(bikeTwo.getId());
    }

    @Test
    void update() {
        bikesCache.update(bikeOneChange);
        bikeActual = bikesCache.findOne(bikeOneChange.getId());
        assertEquals(bikeOneChange, bikeActual);
    }

    @Test
    void delete() {
        bikesCache.delete(bikeOne.getId());
        bikeActual = bikesCache.findOne(bikeOne.getId());
        assertNull(bikeActual);
    }

    @Test
    void save() {
        bikesCache.save(bikeTwo);
        bikeActual = bikesCache.findOne(bikeTwo.getId());
        assertEquals(bikeTwo, bikeActual);
    }

    @Test
    void findOne() {
        bikeActual = bikesCache.findOne(bikeOne.getId());
        assertEquals(bikeOne, bikeActual);
    }

    @Test
    void findAll() {
        bikesCache.save(bikeTwo);
        List<Bike> actualBikes = bikesCache.findAll();

        assertThat(actualBikes)
                .isNotEmpty()
                .contains(bikeOne, bikeTwo);
    }
}