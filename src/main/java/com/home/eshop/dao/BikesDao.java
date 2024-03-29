package com.home.eshop.dao;

import com.home.eshop.model.Bike;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class BikesDao implements Dao {
    private File file;
    static int maxIdBike = 0;

    public BikesDao(String path) {
        this.file = new File(path);
    }

    Bike idProcessing(Bike bike) {
        int id = bike.getId();
        if (maxIdBike < id) {
            maxIdBike = id;
        }
        if (id == 0) {
            maxIdBike++;
            bike.setId(maxIdBike);
        }
        return bike;
    }

    public int update(Bike bike) {
        File temp = new File(file.getParent(), "temp");
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(temp)));
            bufferedReader
                    .lines()
                    .filter(Objects::nonNull)
                    .peek(bikeString -> {
                        if (getIdToBikeString(bikeString) != bike.getId()) {
                            printWriter.println(bikeString);
                        }
                    })
                    .filter(bikeString -> getIdToBikeString(bikeString) == bike.getId())
                    .forEach(bikeString -> printWriter.println(bike.getValueBikeInSaveTxt()));

            printWriter.flush();
            printWriter.close();
            bufferedReader.close();
            file.delete();
            temp.renameTo(file);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return bike.getId();
    }

    public void delete(int id) {
        File temp = new File(file.getParent(), "temp.txt");
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(temp)));
            bufferedReader
                    .lines()
                    .filter(Objects::nonNull)
                    .filter(bikeString -> getIdToBikeString(bikeString) != id)
                    .forEach(printWriter::println);

            printWriter.flush();
            printWriter.close();
            bufferedReader.close();
            file.delete();
            temp.renameTo(file);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int save(Bike bike) {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(file, true))) {
            printWriter.println(idProcessing(bike).getValueBikeInSaveTxt());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bike.getId();
    }

    public Bike findOne(int id) {
        Optional<Bike> optionalBike = Optional.empty();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            optionalBike = bufferedReader
                    .lines()
                    .filter(Objects::nonNull)
                    .filter(bikeString -> getIdToBikeString(bikeString) == id)
                    .map(this::addBike)
                    .findFirst();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return optionalBike.orElse(null);
    }

    public List<Bike> findAll() {
        List<Bike> bikes = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            bikes = bufferedReader
                    .lines()
                    .filter(Objects::nonNull)
                    .map(this::addBike)
                    .collect(toList());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bikes;
    }

    private Bike addBike(String bikeString) {
        String[] tokens = bikeString.split("#");
        String title = tokens[0];
        int price = Integer.parseInt(tokens[1]);
        int number = Integer.parseInt(tokens[2]);
        int id = Integer.parseInt(tokens[3]);
        Bike bike = new Bike(title, price, number, id);
        return idProcessing(bike);
    }

    private int getIdToBikeString(String bikeString) {
        String[] tokens = bikeString.split("#");
        return Integer.parseInt(tokens[3]);
    }
}
