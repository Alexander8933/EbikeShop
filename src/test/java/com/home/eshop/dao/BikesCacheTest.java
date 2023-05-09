package com.home.eshop.dao;

import com.home.eshop.model.Bike;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;

//@ExtendWith(MockitoExtension.class)
//class BikesCacheTest {
//
//    @Mock
//    BikesDao bikesDao;
//    @InjectMocks
//    DaoCache bikesCache;
//
//    Bike oneBike = new Bike("One", 1, 1, 1);
//    Bike updateBike = new Bike("Changed", 1, 1, 1);
//    Bike twoBike = new Bike("Two", 2, 2, 2);
//
//    @Test
//    void update() {
//        Mockito.doReturn(null).when(bikesDao).findOne(anyInt());
//
//        Mockito.doReturn(oneBike).when(bikesDao).idProcessing(oneBike);
//        Mockito.doReturn(oneBike.getId()).when(bikesDao).save(oneBike);
//        bikesCache.save(oneBike);
//
//        int id = updateBike.getId();
//        Mockito.doReturn(id).when(bikesDao).update(updateBike);
//
//        bikesCache.update(updateBike);
//        assertEquals(updateBike, bikesCache.findOne(id));
//
//        Mockito.verify(bikesDao, times(1)).update(updateBike);
//    }
//
//    @Test
//    void delete() {
//        Mockito.doReturn(null).when(bikesDao).findOne(anyInt());
//
//        Mockito.doReturn(oneBike).when(bikesDao).idProcessing(oneBike);
//        Mockito.doReturn(oneBike.getId()).when(bikesDao).save(oneBike);
//        bikesCache.save(oneBike);
//
//        int id = oneBike.getId();
//        Mockito.doNothing().when(bikesDao).delete(id);
//
//        assertEquals(oneBike, bikesCache.findOne(id));
//
//        bikesCache.delete(id);
//        assertNull(bikesCache.findOne(id));
//
//        Mockito.verify(bikesDao, times(1)).delete(id);
//    }
//
//    @Test
//    void save() {
//        Mockito.doReturn(null).when(bikesDao).findOne(anyInt());
//
//        int id = twoBike.getId();
//        Mockito.when(bikesDao.idProcessing(twoBike)).thenReturn(twoBike);
//        Mockito.doReturn(twoBike.getId()).when(bikesDao).save(twoBike);
//
//        bikesCache.save(twoBike);
//        assertEquals(twoBike, bikesCache.findOne(id));
//
//        Mockito.verify(bikesDao, times(1)).save(twoBike);
//        Mockito.verify(bikesDao, times(1)).idProcessing(twoBike);
//    }
//
//    @Test
//    void findOne() {
//        Mockito.doReturn(null).when(bikesDao).findOne(anyInt());
//
//        Mockito.doReturn(oneBike).when(bikesDao).idProcessing(oneBike);
//        Mockito.doReturn(oneBike.getId()).when(bikesDao).save(oneBike);
//        bikesCache.save(oneBike);
//
//        int id = oneBike.getId();
//
//        assertEquals(oneBike, bikesCache.findOne(id));
//
//        Mockito.verify(bikesDao, times(1)).findOne(id);
//    }
//
//    @Test
//    void findAll() {
//        Mockito.doReturn(oneBike).when(bikesDao).idProcessing(oneBike);
//        Mockito.doReturn(oneBike.getId()).when(bikesDao).save(oneBike);
//        bikesCache.save(oneBike);
//        Mockito.doReturn(twoBike).when(bikesDao).idProcessing(twoBike);
//        Mockito.doReturn(twoBike.getId()).when(bikesDao).save(twoBike);
//        bikesCache.save(twoBike);
//
//        List<Bike> bikes = bikesCache.findAll();
//
//        assertThat(bikes)
//                .isNotEmpty()
//                .contains(oneBike,twoBike);
//    }
//}