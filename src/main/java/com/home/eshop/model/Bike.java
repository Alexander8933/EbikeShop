package com.home.eshop.model;

public class Bike {
    private String title;
    private int price;
    private int number;
    private int id;

    public Bike(String title, int price, int number) {
        this.title = title;
        this.price = price;
        this.number = number;
        this.id = 0;
    }

    public Bike(String title, int price, int number, int id) {
        this.title = title;
        this.price = price;
        this.number = number;
        this.id = id;
    }

    public String getTextBikePresentation() {
        return "Title : " + title + " Price : " + price + " Number : " + number + " Id : " + id;
    }

    public String getValueBikeInSaveTxt() {
        return title + "#" + price + "#" + number + "#" + id + "#";
    }

    public void setTitle(String title) {
        if (!(title.isEmpty())) {
            this.title = title;
        }
    }

    public void setPrice(int price) {
        if (price >= 0) {
            this.price = price;
        }
    }

    public void setNumber(int number) {
        if (number >= 0) {
            this.number = number;
        }
    }

    public void setId(int id) {
        if (id >= 0) {
            this.id = id;
        }
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

    public int getId() {
        return id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bike bike = (Bike) o;

        if (price != bike.price) return false;
        if (number != bike.number) return false;
        if (id != bike.id) return false;
        return title.equals(bike.title);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + price;
        result = 31 * result + number;
        result = 31 * result + id;
        return result;
    }
}