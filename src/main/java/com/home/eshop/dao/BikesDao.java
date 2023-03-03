package com.home.eshop.dao;

import com.home.eshop.model.Bike;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BikesDao {
    private ArrayList<Bike> bikes = new ArrayList<Bike>();
    private File file;
    static int maxIdBike = 0;

    public BikesDao(String path) {
        this.file = new File(path);
    }


    private Bike addId(Bike bike) {
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
        int id = Integer.parseInt(tokens[3]);
        Bike nextBike = new Bike(title, price, number, id);
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
        if (id <= maxIdBike) {
            for (Bike bike : bikes) {
                if (bike.getId() == id) {
                    bikes.remove(bikes.indexOf(bike));
                    break;
                }
            }
        } else {
            System.out.println("id not found");
        }
    }

    public int save(Bike bike) {
        bikes.add(addId(bike));
        return bike.getId();
    }

    public Bike findOne(int id) {
        if (id <= maxIdBike) {
            for (Bike bike : bikes) {
                if (bike.getId() == id) {
                    return bikes.get(bikes.indexOf(bike));
                }
            }
        } else {
            System.out.println("id not found");
        }
        return null;
    }

    public List<Bike> findAll() {
        List<Bike> bikesList = bikes;
        return bikesList;
    }
}
