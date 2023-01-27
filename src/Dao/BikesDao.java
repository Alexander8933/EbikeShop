package Dao;

import Type.Bike;

import java.io.*;
import java.util.ArrayList;

class BikesDao {

    private ArrayList<Bike> bikes = new ArrayList<Bike>();
    private File file = new File("BikeBase.txt");

    void loadData() {
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
    void saveAll() {
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

    void delete(int id) {
        bikes.remove(id);
    }

    void update(int id, Bike bike) {
        bikes.set(id, bike);
    }

    int save(Bike bike) {
        int id = bikes.size();
        bikes.add(id, bike);
        return id;
    }

    Bike findOne(int id) {
        return bikes.get(id);
    }

    int bikesSize() {
        return bikes.size();
    }
}
