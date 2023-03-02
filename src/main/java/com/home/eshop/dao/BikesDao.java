package com.home.eshop.dao;

import com.home.eshop.model.Bike;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BikesDao {

    public BikesDao(String path) {
        this.file = new File(path);
    }

    private ArrayList<Bike> bikes = new ArrayList<Bike>();
    private File file;
    private int idBike = 0;
    private Bike addId(Bike bike){
        bike.setId(idBike);
        idBike++;
        return bike;
    }

    public void loadData() {
        if (bikes.isEmpty()) {
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
    }

    private void addBike(String bikeString) {
        String[] tokens = bikeString.split("#");
        String title = tokens[0];
        int price = Integer.parseInt(tokens[1]);
        int number = Integer.parseInt(tokens[2]);
        Bike nextBike = new Bike(title, price, number);
        bikes.add(addId(nextBike));
    }

    public void saveData() {
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

    public void delete(int id) {
        if (id < bikes.size()) {
            bikes.remove(id);
        } else {
            System.out.println("id not found");
        }
    }

    public void deleteTest(Bike bike) {
        bikes.remove(bike);
    }

    public int save(Bike bike) {
       bikes.add(addId(bike));
        return bike.getId();
    }

    public Bike findOne(int id) {
        return bikes.get(id);
    }

    public Bike findOneTest(Bike bike) {
        if (bikes.indexOf(bike) != (-1)) {
            Bike bikeOne = bikes.get(bikes.indexOf(bike));
            return bikeOne;
        } else {
            System.out.println("Bike not found");
        }
        return null;
    }

    public List<Bike> findAll() {
        List<Bike> bikesList = bikes;
        return bikesList;
    }

    public int bikesSize() {
        return bikes.size();
    }
}
