
import java.io.*;
import java.util.ArrayList;

public class Shop {
    ArrayList<Bike> bikeArrayList = new ArrayList<Bike>();

    public void getBike() {

        try {
            File file = new File("BikeBase.txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {

            }

        } catch (FileNotFoundException e) {
            System.out.println("File BikeBase.txt Not Found ");
            e.printStackTrace();
        } catch (IOException ex){
            System.out.println("File BikeBase.txt May be damaged");
            ex.printStackTrace();
        }


    }
}