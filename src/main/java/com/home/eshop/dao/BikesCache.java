package com.home.eshop.dao;

import com.home.eshop.model.Bike;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BikesCache implements Dao {
    private Map<Integer, Bike> mapBikes;
    private BikesDao bikesDao;

    public BikesCache(String path) {
        addBikesMap(path);
    }

    private void addBikesMap(String path) {
        if (bikesDao == null) {
            bikesDao = new BikesDao(path);
            mapBikes = bikesDao
                    .findAll()
                    .stream()
                    .filter(Objects::nonNull)
                    .collect(Collectors.toMap(Bike::getId, Function.identity()));
        }
    }

    @Override
    public int update(Bike bike) {
        Optional<Bike> optionalBike = Optional.ofNullable(mapBikes.get(bike.getId()));
        optionalBike.ifPresent(bikeChang -> mapBikes.put(bikeChang.getId(), bike));
        optionalBike.orElse(bikesDao.findOne(bike.getId()));
        optionalBike.ifPresent(bikeChang -> bikesDao.update(bike));
        return bike.getId();
    }

    @Override
    public void delete(int id) {
        Optional<Bike> optionalBike = Optional.ofNullable(mapBikes.get(id));
        optionalBike.ifPresent(bike -> {
            mapBikes.remove(bike.getId());
        });
        optionalBike.orElse(bikesDao.findOne(id));
        optionalBike.ifPresent(bike -> bikesDao.delete(bike.getId()));
    }

    @Override
    public int save(Bike bike) {
        Optional<Bike> optionalBike = Optional.of(bikesDao.idProcessing(bike));
        optionalBike.ifPresent(bikeSave -> {
            mapBikes.put(bikeSave.getId(), bikeSave);
            bikesDao.save(bikeSave);
        });
        return optionalBike.map(Bike::getId).get();
    }

    @Override
    public Bike findOne(int id) {
        Optional<Bike> optionalBike = Optional.ofNullable(mapBikes.get(id));
        optionalBike.orElse(bikesDao.findOne(id));
        return optionalBike.orElse(null);
    }

    @Override
    public List<Bike> findAll() {
        return mapBikes
                .values()
                .stream()
                .toList();
    }
}
