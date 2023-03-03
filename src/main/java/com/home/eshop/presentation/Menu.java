package com.home.eshop.presentation;

import com.home.eshop.dao.BikesDao;
import com.home.eshop.model.Bike;
import com.home.eshop.utils.InputTxt;

import java.util.List;

public class Menu {
    private BikesDao bikesDao = new BikesDao("BikeBase.txt");
    private InputTxt inputTxt = new InputTxt();

    public void show() {
        bikesDao.loadData();
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
                    bikesDao.saveData();
                    break;
                case "c":
                    showAllBikes();
                    changeValue(bikesDao.findOne(choiceBike()));
                    bikesDao.saveData();
                    break;
                case "d":
                    showAllBikes();
                    bikesDao.delete(choiceBike());
                    bikesDao.saveData();
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
        List<Bike> bikesList = bikesDao.findAll();
        for (Bike bike : bikesList) {
            System.out.println(bike.getTextBikePresentation());
        }
    }

    private void addNewBike() {
        Bike bike = new Bike("newBike", 0, 0);
        bikesDao.save(bike);
    }

    private void changeValue(Bike bike) {
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
    }

    private int choiceBike() {
        System.out.println("Choice Bike Enter Id : ");
        int choice = Integer.parseInt(inputTxt.get());
        return choice;
    }
}
