package com.home.eshop.dao;

import com.home.eshop.model.Bike;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DaoCache implements Dao {
    private Map<Integer, Bike> mapBikes = new HashMap<>();
    private DaoJDBC dao;

    public DaoCache() {
        createMapBikes();
    }

    private void createMapBikes() {
        if (dao == null) {
            dao = new DaoJDBC();
            mapBikes = dao
                    .findAll()
                    .stream()
                    .filter(Objects::nonNull)
                    .collect(Collectors.toMap(Bike::getId, Function.identity()));
        }
    }

    @Override
    public int create(Bike bike) {
        Optional<Bike> optionalBike = Optional.ofNullable(bike);
        return optionalBike.map(result -> {
            int id = dao.create(bike);
            mapBikes.put(id, bike);
            return id;
        }).orElse(0);
    }

    @Override
    public Bike read(int id) {
        Optional<Bike> optionalBike = Optional.ofNullable(mapBikes.get(id));
        return optionalBike.orElse(dao.read(id));
    }

    @Override
    public int update(Bike bike) {
        Optional<Bike> optionalBike = Optional.ofNullable(bike);
        optionalBike.map(availabilityBike -> mapBikes.get(availabilityBike.getId()));
        return optionalBike.map(result -> {
            mapBikes.put(bike.getId(), bike);
            return dao.update(bike);
        }).orElse(0);
    }

    @Override
    public int delete(int id) {
        Optional<Bike> optionalBike = Optional.ofNullable(mapBikes.get(id));
        return optionalBike.map(result -> {
            mapBikes.remove(id);
            return dao.delete(id);
        }).orElse(0);
    }

    @Override
    public List<Bike> findAll() {
        return mapBikes
                .values()
                .stream()
                .toList();
    }
}
