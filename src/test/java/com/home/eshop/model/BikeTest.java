package com.home.eshop.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BikeTest {

    private Bike bikeTest;

    @BeforeEach
    void prepare() {
        bikeTest = new Bike("test", 1, 1);
    }

    @AfterEach
    void after() {
        bikeTest = new Bike("test", 1, 1);
    }

    @Test
    void getTextBikePresentation() {
        String bike1 = "Title : " + "test" + " Price : " + "1" + " Number : " + "1";
        String bike2 = bikeTest.getTextBikePresentation();
        assertTrue(bike1.equals(bike2));
    }

    @Test
    void getValueBikeInSaveTxt() {
        String bike1 = "test" + "#" + "1" + "#" + "1" + "#";
        String bike2 = bikeTest.getValueBikeInSaveTxt();
        assertTrue(bike1.equals(bike2));
    }

    @Test
    void setTitle() {
        String title1 = "testTitle";
        bikeTest.setTitle(title1);
        String title2 = bikeTest.getTitle();
        assertTrue(title1.equals(title2));
    }

    @Test
    void setPrice() {
        int price1 = 11;
        bikeTest.setPrice(price1);
        int price2 = bikeTest.getPrice();
        assertEquals(price1, price2);
    }

    @Test
    void setNumber() {
        int number1 = 11;
        bikeTest.setNumber(number1);
        int number2 = bikeTest.getNumber();
        assertEquals(number1, number2);
    }

    @Test
    void getTitle() {
        String title1 = "test";
        String title2 = bikeTest.getTitle();
        assertTrue(title1.equals(title2));
    }

    @Test
    void getNumber() {
        int number1 = 1;
        int number2 = bikeTest.getNumber();
        assertEquals(number1, number2);
    }

    @Test
    void getPrice() {
        int price1 = 1;
        int price2 = bikeTest.getPrice();
        assertEquals(price1, price2);
    }
}