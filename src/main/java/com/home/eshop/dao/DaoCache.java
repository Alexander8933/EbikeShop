package com.home.eshop.dao;

import com.home.eshop.model.Bike;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DaoCache implements Dao {
    private Map<Integer, Bike> mapBikes;
    private BikesDao bikesDao;

    public DaoCache(String path) {
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

    private static void bikeNotFound() {
        System.out.println("Bike not found");
    }

    @Override
    public int change(Bike bike) {
        Optional<Bike> optionalBike = Optional.ofNullable(mapBikes.get(bike.getId()));
        optionalBike.ifPresent(bikeChang -> {
            mapBikes.remove(bikeChang.getId());
            mapBikes.put(bikeChang.getId(), bikeChang);
        });
        optionalBike.orElse(bikesDao.findOne(bike.getId()));
        optionalBike.ifPresentOrElse(bikeChang -> bikesDao.change(bikeChang), DaoCache::bikeNotFound);
        return bike.getId();
    }

    @Override
    public void delete(int id) {
        Optional<Bike> optionalBike = Optional.ofNullable(mapBikes.get(id));
        optionalBike.ifPresent(bike -> {
            mapBikes.remove(bike.getId());
        });
        optionalBike.orElse(bikesDao.findOne(id));
        optionalBike.ifPresentOrElse(bike -> bikesDao.delete(bike.getId()), DaoCache::bikeNotFound);
    }

    @Override
    public int save(Bike bike) {
        Optional<Bike> optionalBike = Optional.of(bikesDao.addId(bike));
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
        if (optionalBike.isPresent()) {
            return optionalBike.get();
        } else {
            System.out.println("Bike not found");
        }
        return null;
    }

    @Override
    public List<Bike> findAll() {
        return mapBikes
                .values()
                .stream()
                .toList();
    }
}
