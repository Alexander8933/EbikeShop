import java.io.*;
import java.util.ArrayList;

 class Shop {
    private ArrayList<Bike> bikes = new ArrayList<Bike>();

     public static void main(String[] args) {
        new Shop().launchShop();
    }

    private void launchShop() {
        getBikesFromTxt();
        showMenu();
    }
    //test

    private void showMenu() {
        boolean runMenu = true;
        do {
            showTitle();
            String choice = UserInput.get();
            switch (choice) {
                case "a":
                    showAllBikes();
                    break;
                case "n":
                    addNewBike();
                    break;
                case "c":
                    showAllBikes();
                    choiceValueBike(choiceBike());
                    saveBikesInTxt();
                    break;
                case "d":
                    showAllBikes();
                    deleteOldBike(choiceBike());
                    saveBikesInTxt();
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
        int bikeSize = bikes.size();
        for (int bikeId = 0; bikeId < bikeSize; bikeId++) {
            System.out.println("Id : " + bikeId);
            showBike(bikeId);
        }
    }

    private void showBike(int bikeId) {
        System.out.println(bikes.get(bikeId).getTextBikePresentation());
    }

    private int choiceBike() {
        System.out.println("Choice Bike Enter Id : ");
        int choice = Integer.parseInt(UserInput.get());
        return choice;
    }

    private void choiceValueBike(int idBike) {
        System.out.println("Change : Title Enter t , Price Enter p , Number Enter n");
        String choice = UserInput.get();
        if (choice.equals("t")) {
            changeBike(idBike, "title");
        }
        if (choice.equals("p")) {
            changeBike(idBike, "price");
        }
        if (choice.equals("n")) {
            changeBike(idBike, "number");
        }
    }

    private void changeBike(int idBike, String value) {
        Bike bike = bikes.get(idBike);
        if (value.equals("title")) {
            System.out.println("Old title : " + bike.getTitle() + "\nEnter new title :");
            bike.setTitle(UserInput.get());
        }
        if (value.equals("price")) {
            System.out.println("Old price : " + bike.getPrice() + "\nEnter new price :");
            bike.setPrice(Integer.parseInt(UserInput.get()));
        }
        if (value.equals("number")) {
            System.out.println("Old number : " + bike.getNumber() + "\nEnter new number :");
            bike.setNumber(Integer.parseInt(UserInput.get()));
        }
        bikes.set(idBike, bike);
    }

    private void addNewBike() {
        int addNew = bikes.size();
        bikes.add(new Bike("newBike", 0, 0));
        changeBike(addNew, "title");
        changeBike(addNew, "price");
        changeBike(addNew, "number");
        System.out.println("New Bike Created");
    }

    private void deleteOldBike(int id) {
        int bikeSize = bikes.size();
        if (id < bikeSize && bikeSize != 0) {
            System.out.println("Old Bike : " + bikes.get(id).getTextBikePresentation());
            System.out.println("Delet : Enter y or n");
            String choice = UserInput.get();
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

    private void getBikesFromTxt() {
        File file = new File("BikeBase.txt");
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                addBikeFromTxt(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File BikeBase.txt Not Found ");
            e.printStackTrace();
        } catch (IOException ex) {
            System.out.println("File BikeBase.txt May be damaged");
            ex.printStackTrace();
        }
    }

    private void saveBikesInTxt() {
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

    private void addBikeFromTxt(String bikeLine) {
        String[] tokens = bikeLine.split("#");
        String title = tokens[0];
        int price = Integer.parseInt(tokens[1]);
        int number = Integer.parseInt(tokens[2]);
        Bike nextBike = new Bike(title, price, number);
        bikes.add(nextBike);
    }
}