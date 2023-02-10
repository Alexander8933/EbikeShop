package com.home.eshop.model;

public class Bike {
    private String title;
    private int price;
    private int number;

    public Bike(String title, int price, int number) {
        this.title = title;
        this.price = price;
        this.number = number;
    }

    public String getTextBikePresentation() {
        return "Title : " + title + " Price : " + price + " Number : " + number;
    }

    public String getValueBikeInSaveTxt() {
        return title + "#" + price + "#" + number + "#";
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

    public String getTitle() {
        return title;
    }

    public int getNumber() {
        return number;
    }

    public int getPrice() {
        return price;
    }


}
