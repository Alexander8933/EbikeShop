package com.home.eshop.dao;

import com.home.eshop.model.Bike;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;

class BikesCacheTest {

    @Mock
    private BikesDao bikesDao;
    private final BikesCache bikesCache;
    @Spy
    Bike bikeOne = new Bike("One", 1, 1, 1);
    Bike bikeUpdate = new Bike("Changed", 1, 1, 1);
    Bike bikeTwo = new Bike("Two", 2, 2, 2);

    public BikesCacheTest() {
        MockitoAnnotations.initMocks(this);
        this.bikesCache = new BikesCache(bikesDao);
    }

    @BeforeEach
    void prepare() {
        Mockito.doReturn(null).when(bikesDao).findOne(anyInt());

        Mockito.doReturn(bikeOne).when(bikesDao).idProcessing(bikeOne);
        Mockito.doReturn(bikeOne.getId()).when(bikesDao).save(bikeOne);
        bikesCache.save(bikeOne);
    }

    @Test
    void update() {
        int id = bikeUpdate.getId();
        Mockito.doReturn(id).when(bikesDao).update(bikeUpdate);

        bikesCache.update(bikeUpdate);
        assertEquals(bikeUpdate, bikesCache.findOne(id));

        Mockito.verify(bikesDao, times(1)).update(bikeUpdate);
    }

    @Test
    void delete() {
        int id = bikeOne.getId();
        Mockito.doNothing().when(bikesDao).delete(id);

        assertEquals(bikeOne, bikesCache.findOne(id));

        bikesCache.delete(id);
        assertNull(bikesCache.findOne(id));

        Mockito.verify(bikesDao, times(1)).delete(id);
    }

    @Test
    void save() {
        int id = bikeTwo.getId();
        Mockito.when(bikesDao.idProcessing(bikeTwo)).thenReturn(bikeTwo);
        Mockito.doReturn(bikeTwo.getId()).when(bikesDao).save(bikeTwo);

        bikesCache.save(bikeTwo);
        assertEquals(bikeTwo, bikesCache.findOne(id));

        Mockito.verify(bikesDao, times(1)).save(bikeTwo);
        Mockito.verify(bikesDao, times(1)).idProcessing(bikeTwo);
    }

    @Test
    void findOne() {
        int id = bikeOne.getId();

        assertEquals(bikeOne, bikesCache.findOne(id));

        Mockito.verify(bikesDao, times(1)).findOne(id);
    }

    @Test
    void findAll() {
        List<Bike> bikes = bikesCache.findAll();

        assertThat(bikes)
                .isNotEmpty()
                .contains(bikeOne);
    }
}