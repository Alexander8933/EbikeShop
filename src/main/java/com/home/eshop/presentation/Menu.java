package com.home.eshop.presentation;

import com.home.eshop.dao.BikesCache;
import com.home.eshop.dao.BikesDao;
import com.home.eshop.dao.Dao;
import com.home.eshop.model.Bike;
import com.home.eshop.utils.InputTxt;

import java.util.List;
import java.util.Objects;

public class Menu {
    public Dao bikesDao = new BikesCache("BikeBase.txt");
    private InputTxt inputTxt = new InputTxt();

    public void show() {
        boolean runMenu = true;
        do {
            showTitle();
            String choice = inputTxt.get();
            switch (choice) {
                case "a":
                    showAllBikes();
                    break;
                case "n":
                    addNewBike();
                    break;
                case "c":
                    showAllBikes();
                    bikesDao.update(changeValue(bikesDao.findOne(choiceBike())));
                    break;
                case "d":
                    showAllBikes();
                    bikesDao.delete(choiceBike());
                    break;
                case "e":
                    runMenu = false;
                    break;
            }
        } while (runMenu);
    }

    private void showTitle() {
        System.out.println("Show All Bikes Enter : a");
        System.out.println("Add New Bike Enter : n");
        System.out.println("Exit Enter : e");
        System.out.println("Change Bike Enter : c");
        System.out.println("Delete Bike Enter : d");
    }

    private void showAllBikes() {
        bikesDao.findAll()
                .stream()
                .filter(Objects::nonNull)
                .forEach(bike -> System.out.println(bike.getTextBikePresentation()));
    }

    private void addNewBike() {
        bikesDao.save(new Bike("newBike", 0, 0));
    }

    private Bike changeValue(Bike bike) {
        System.out.println("Change : Title Enter t , Price Enter p , Number Enter n");
        String choice = inputTxt.get();
        switch (choice) {
            case "t":
                System.out.println("Old " + bike.getTitle());
                bike.setTitle(inputTxt.get());
                break;
            case "p":
                System.out.println("Old " + bike.getPrice());
                bike.setPrice(Integer.parseInt(inputTxt.get()));
                break;
            case "n":
                System.out.println("Old " + bike.getNumber());
                bike.setNumber(Integer.parseInt(inputTxt.get()));
                break;
        }
        return bike;
    }

    private int choiceBike() {
        System.out.println("Choice Bike Enter Id : ");
        int choice = Integer.parseInt(inputTxt.get());
        return choice;
    }
}
