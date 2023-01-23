package Dao;

import Type.Bike;
import UserInput.InputTxt;

import java.io.*;
import java.util.ArrayList;

public class BikesDao {
    ArrayList<Bike> bikes = new ArrayList<Bike>();

    InputTxt inputTxt = new InputTxt();

    public void findAll() {
        File file = new File("BikeBase.txt");
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                addBike(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File BikeBase.txt Not Found ");
            e.printStackTrace();
        } catch (IOException ex) {
            System.out.println("File BikeBase.txt May be damaged");
            ex.printStackTrace();
        }
    }
    private void addBike(String bikeString) {
        String[] tokens = bikeString.split("#");
        String title = tokens[0];
        int price = Integer.parseInt(tokens[1]);
        int number = Integer.parseInt(tokens[2]);
        Bike nextBike = new Bike(title, price, number);
        bikes.add(nextBike);
    }
    //private void saveAll(List bikes)
    public void saveAll() {
        File file = new File("BikeBase.txt");
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            for (Bike bike : bikes) {
                bufferedWriter.write(bike.getValueBikeInSaveTxt());
                bufferedWriter.newLine();
            }
        } catch (IOException ex) {
            System.out.println("File BikeBase.txt May be damaged");
            ex.printStackTrace();
        }
    }
   // int save(Bike  bike){return 0;}
  //  Bike findOne(int id){return Bike;}
    //void update(int id, Bike  bike){}





    //void delete(int id){}
    public void delete(int id) {
        int bikeSize = bikes.size();
        if (id < bikeSize && bikeSize != 0) {
            System.out.println("Old Bike : " + bikes.get(id).getTextBikePresentation());
            System.out.println("Delet : Enter y or n");
            String choice = inputTxt.get();
            if (choice.equals("y")) {
                bikes.remove(id);
            }
            if (choice.equals("n")) {
                return;
            }
        }
        if (bikeSize == 0) {
            System.out.println("Empty base");
        }
    }

    public void changeBike(int idBike, String value) {
        Bike bike = bikes.get(idBike);
        if (value.equals("title")) {
            System.out.println("Old title : " + bike.getTitle() + "\nEnter new title :");
            bike.setTitle(inputTxt.get());
        }
        if (value.equals("price")) {
            System.out.println("Old price : " + bike.getPrice() + "\nEnter new price :");
            bike.setPrice(Integer.parseInt(inputTxt.get()));
        }
        if (value.equals("number")) {
            System.out.println("Old number : " + bike.getNumber() + "\nEnter new number :");
            bike.setNumber(Integer.parseInt(inputTxt.get()));
        }
        //bikes.set(idBike, bike);
    }

    public void addNewBike() {
        int addNew = bikes.size();
        bikes.add(new Bike("newBike", 0, 0));
        changeBike(addNew, "title");
        changeBike(addNew, "price");
        changeBike(addNew, "number");
        System.out.println("New Bike Created");
    }

    public void showAllBikes() {
        int bikeSize = bikes.size();
        for (int bikeId = 0; bikeId < bikeSize; bikeId++) {
            System.out.println("Id : " + bikeId);
            showBike(bikeId);
        }
    }

    private void showBike(int bikeId) {
        System.out.println(bikes.get(bikeId).getTextBikePresentation());
    }
}
