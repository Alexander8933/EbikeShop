public class Bike {
     String title;
     int price;
     int number;

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

    @Override
    public String toString() {
        return "Bike{" +
                "title='" + title + '\n' +
                ", price=" + price + '\n'+
                ", number=" + number +
                '}';
    }
}
