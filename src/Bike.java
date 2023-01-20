public class Bike {
    private String title;
     private  int price;
     private int number;


     public Bike(String title,int price,int number){
         this.title=title;
         this.price=price;
         this.number=number;
     }


     public void setTitle(String title){
         this.title=title;
     }
    public void setPrice(int price){
        this.price=price;
    }
    public void setNumber(int number){
        this.number=number;
    }

    public String getTitle() {
        return title;
    }

    public int getNumber() {
        return number;
    }

    public int getPrice() {
        return price;
    }


    public String toString() {
        return title +" "+price+" "+number;
    }
}
