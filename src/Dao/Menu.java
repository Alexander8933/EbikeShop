package Dao;

import Type.Bike;
import UserInput.InputTxt;

public class Menu {
    private BikesDao bikesDao = new BikesDao();
    private InputTxt inputTxt = new InputTxt();

    public void show() {
        bikesDao.findAll();
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
                    bikesDao.saveAll();
                    break;
                case "c":
                    showAllBikes();
                    changeValue(bikesDao.findOne(choiceBike()));
                    bikesDao.saveAll();
                    break;
                case "d":
                    showAllBikes();
                    bikesDao.delete(choiceBike());
                    bikesDao.saveAll();
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
        int end = bikesDao.bikesSize();
        for (int start = 0; start < end; start++) {
            Bike bike = bikesDao.findOne(start);
            System.out.println("Id " + start + "\n" + bike.getTextBikePresentation());
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
