
import java.io.*;
import java.util.ArrayList;

public class Shop {
    ArrayList<Bike> bikeArrayList = new ArrayList<Bike>();
    public static void main(String[] args){
        new Shop().go();

    }
    public void go(){
        getBikeFromTxt();
        System.out.println(bikeArrayList);

        menuShop();

        saveBikeInTxt();


    }

    public void menuShop(){
        System.out.println("Show menu :\nYes - enter y \n No - enter n");
        if(jobMenu().equals("y")){
            for(Bike temp : bikeArrayList){
                int id = bikeArrayList.indexOf(temp);
                System.out.println("Id : "+id);
                System.out.println( "Title : "+temp.title+" Price : "+temp.price+" Number : "+temp.number);

            }
            System.out.println("Change Bike Enter Id : ");
            if(){}
        }
    }

    public String jobMenu(){

        String line = null;

        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))){
           line = bufferedReader.readLine();
            System.out.println(line);
           if((line.length())==0) return null;

        }catch (Exception e){
            e.printStackTrace();
        }
        return line;
    }
    public void changeBike (int id,String titlePriceNumber){
        Bike temp = bikeArrayList.get(id);
        if(titlePriceNumber.equals("title")){temp.title = jobMenu();}
        if(titlePriceNumber.equals("price")){temp.price = Integer.parseInt(jobMenu());}
        if(titlePriceNumber.equals("number")){temp.number = Integer.parseInt(jobMenu());}

        bikeArrayList.set(id,temp);
    }

    public void getBikeFromTxt() {
        File file = new File("src", "BikeBase.txt");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                addBikeArrayList(line);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File BikeBase.txt Not Found ");
            e.printStackTrace();
        } catch (IOException ex){
            System.out.println("File BikeBase.txt May be damaged");
            ex.printStackTrace();
        }
    }
    public void saveBikeInTxt (){
        File file = new File("src", "BikeBase.txt");
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))){
            for(Bike temp : bikeArrayList){
                bufferedWriter.write(String.valueOf(temp.title+"#"+temp.price+"#"+temp.number+"#"));
                bufferedWriter.newLine();
            }

        } catch (IOException ex){
            System.out.println("File BikeBase.txt May be damaged");
            ex.printStackTrace();
        }
    }
    public void addBikeArrayList (String bikeLine){
        String[] tokens=bikeLine.split("#");
        String title = tokens[0];
        int price = Integer.parseInt(tokens[1]);
        int number = Integer.parseInt(tokens[2]);

        Bike nextBike = new Bike(title,price,number);
        bikeArrayList.add(nextBike);

    }

}