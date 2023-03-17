package com.home.eshop.dao;

import com.home.eshop.model.Bike;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BikesDao implements Dao {
    private File file;
    static int maxIdBike = 0;

    public BikesDao(String path) {
        this.file = new File(path);
    }

    Bike addId(Bike bike) {
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

    public int change(Bike bike) {
        File temp = new File(file.getParent(), "temp");
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(temp)));
            while (bufferedReader.ready()) {
                String bikeString = bufferedReader.readLine();
                String[] tokens = bikeString.split("#");
                if (Integer.parseInt(tokens[3]) != bike.getId()) {
                    printWriter.println(bikeString);
                }
                if (Integer.parseInt(tokens[3]) == bike.getId()) {
                    printWriter.println(bike.getValueBikeInSaveTxt());
                }
            }
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
            while (bufferedReader.ready()) {
                String bikeString = bufferedReader.readLine();
                String[] tokens = bikeString.split("#");
                if (Integer.parseInt(tokens[3]) != id) {
                    printWriter.println(bikeString);
                }
            }
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
            printWriter.println(addId(bike).getValueBikeInSaveTxt());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bike.getId();
    }

    public Bike findOne(int id) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            while (bufferedReader.ready()) {
                String bikeString = (bufferedReader.readLine());
                String[] tokens = bikeString.split("#");
                if (Integer.parseInt(tokens[3]) == id) {
                    return addBike(bikeString);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Bike> findAll() {
        List<Bike> bikes = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            while (bufferedReader.ready()) {
                bikes.add(addBike(bufferedReader.readLine()));
            }
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
        return addId(bike);
    }
}
