
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
        //addNewBike();




    }

    public void menuShop(){
        int start = 0 ;
        while (start ==0){
        System.out.println("Show menu :\nEnter : y");
        System.out.println("Exit\nEnter : e");
        System.out.println("Add New Bike\nEnter : n");
        String choice = jobMenu();
        if(choice.equals("n")){addNewBike();}
        if(choice.equals("e")){++start;}
        if(choice.equals("y")){
            for(Bike temp : bikeArrayList){
                int id = bikeArrayList.indexOf(temp);
                System.out.println("Id : "+id);
                System.out.println( "Title : "+temp.title+" Price : "+temp.price+" Number : "+temp.number);

            }
            System.out.println("Change Bike Enter Id : ");
            int idEnter =Integer.parseInt(jobMenu());
            if(idEnter<bikeArrayList.size()){
                Bike temp = bikeArrayList.get(idEnter);
                System.out.println("Title : "+temp.title+" Price : "+temp.price+" Number : "+temp.number);

                System.out.println("Change : Title Enter t , Price Enter p , Number Enter n \nDelet Bike Enter d");
                String tPN = jobMenu();
                if(tPN.equals("d")){deletOldBike(idEnter);}
                if(tPN.equals("t")){changeBike(idEnter,"title");}
                if(tPN.equals("p")){changeBike(idEnter,"price");}
                if(tPN.equals("n")){changeBike(idEnter,"number");}
            }

        }

    }}

    public String jobMenu(){

        String line = null;

        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
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
        if(titlePriceNumber.equals("title")){
            System.out.println("Old title : "+temp.title+"\nEnter new title :");temp.title = jobMenu();}
        if(titlePriceNumber.equals("price")){
            System.out.println("Old price : "+temp.price+"\nEnter new price :");temp.price = Integer.parseInt(jobMenu());}
        if(titlePriceNumber.equals("number")){
            System.out.println("Old number : "+temp.number+"\nEnter new number :");temp.number = Integer.parseInt(jobMenu());}
        bikeArrayList.set(id,temp);
        saveBikeInTxt();
    }
    public void addNewBike(){
        int addNew = bikeArrayList.size();
        bikeArrayList.add(new Bike("newBike",0,0));
        changeBike(addNew,"title");
        changeBike(addNew,"price");
        changeBike(addNew,"number");
        System.out.println("New Bike Created");

    }
    public void deletOldBike(int id){

        if(id<bikeArrayList.size()&&bikeArrayList.size()!=0){
            System.out.println("Old Bike : "+bikeArrayList.get(id));
            System.out.println("Delet : Enter y or n");
            String yesNo = jobMenu();
            if(yesNo.equals("y")){bikeArrayList.remove(id);saveBikeInTxt();}
            if(yesNo.equals("n")){ return;}
        }
        if(bikeArrayList.size()==0){
            System.out.println("In the base must beat one Bike");
            return;
        }

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