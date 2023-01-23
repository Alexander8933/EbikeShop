package Menu;

import Dao.BikesDao;
import UserInput.InputTxt;

public class ShopMenu {

    BikesDao bikesDao = new BikesDao();
    InputTxt inputTxt = new InputTxt();

    public void showMenu() {
        bikesDao.findAll();
        boolean runMenu = true;
        do {
            showTitle();
            String choice = inputTxt.get();
            switch (choice) {
                case "a":
                    bikesDao.showAllBikes();
                    break;
                case "n":
                    bikesDao.addNewBike();
                    break;
                case "c":
                    bikesDao.showAllBikes();
                    choiceValueBike(choiceBike());
                    bikesDao.saveAll();
                    break;
                case "d":
                    bikesDao.showAllBikes();
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
    private int choiceBike() {
        System.out.println("Choice Bike Enter Id : ");
        int choice = Integer.parseInt(inputTxt.get());
        return choice;
    }
    private void choiceValueBike(int idBike) {
        System.out.println("Change : Title Enter t , Price Enter p , Number Enter n");
        String choice = inputTxt.get();
        if (choice.equals("t")) {
            bikesDao.changeBike(idBike, "title");
        }
        if (choice.equals("p")) {
            bikesDao.changeBike(idBike, "price");
        }
        if (choice.equals("n")) {
            bikesDao.changeBike(idBike, "number");
        }
    }

}
