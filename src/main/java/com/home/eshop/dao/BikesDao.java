package com.home.eshop.dao;

import com.home.eshop.model.Bike;

import java.io.*;
import java.util.ArrayList;

public class BikesDao {
    public BikesDao() {
        this.bikes = new ArrayList<Bike>();
        this.file = new File("BikeBase.txt");
        loadData(bikes);
    }

    public BikesDao(ArrayList<Bike> bikes, File file) {
        this.bikes = bikes;
        this.file = file;
    }

    private ArrayList<Bike> bikes;
    private File file;

    public void loadData(ArrayList<Bike> bikes) {
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
        bikes.add(nextBike);
    }

    public void saveAll() {
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

    public int save(Bike bike) {
        int id = bikes.size();
        bikes.add(id, bike);
        return id;
    }

    public Bike findOne(int id) {
        return bikes.get(id);
    }

    public int bikesSize() {
        return bikes.size();
    }
}
