package com.home.eshop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//class BikeTest {
//
//    private Bike bikeTest = new Bike("test", 1, 1, 0);
//
//    @Test
//    void getTextBikePresentation() {
//        String exp = "Title : " + "test" + " Price : " + "1" + " Number : " + "1" + " Id : " + "0";
//        String act = bikeTest.getTextBikePresentation();
//        assertEquals(exp, act);
//    }
//
//    @Test
//    void getValueBikeInSaveTxt() {
//        String exp = "test" + "#" + "1" + "#" + "1" + "#" + "0" + "#";
//        String act = bikeTest.getValueBikeInSaveTxt();
//        assertEquals(exp, act);
//    }
//
//    @Test
//    void bikeValueSet() {
//        String titleSetExp = "testTitle";
//        bikeTest.setTitle(titleSetExp);
//        String titleSetAct = bikeTest.getTitle();
//        assertEquals(titleSetExp, titleSetAct);
//
//        int priceSetExp = 11;
//        bikeTest.setPrice(priceSetExp);
//        int priceSetAct = bikeTest.getPrice();
//        assertEquals(priceSetExp, priceSetAct);
//
//        int numberSetExp = 11;
//        bikeTest.setNumber(numberSetExp);
//        int numberSetAct = bikeTest.getNumber();
//        assertEquals(numberSetExp, numberSetAct);
//
//        int idSetExp = 11;
//        bikeTest.setId(numberSetExp);
//        int idSetAct = bikeTest.getId();
//        assertEquals(idSetExp, idSetAct);
//
//    }
//
//    @Test
//    void bikeValueGet() {
//        String titleGetExp = "test";
//        String titleGetAct = bikeTest.getTitle();
//        assertEquals(titleGetExp, titleGetAct);
//
//        int numberGetExp = 1;
//        int numberGetAct = bikeTest.getNumber();
//        assertEquals(numberGetExp, numberGetAct);
//
//        int priceGetExp = 1;
//        int priceGetAct = bikeTest.getPrice();
//        assertEquals(priceGetExp, priceGetAct);
//
//        int idGetExp = 0;
//        int idGetAct = bikeTest.getId();
//        assertEquals(idGetExp, idGetAct);
//    }
//}