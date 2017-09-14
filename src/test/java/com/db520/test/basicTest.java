package com.db520.test;

import org.junit.Test;

import java.util.Date;

public class basicTest {

    @Test
    public void stringSplit() {
        String strTest = "tag1-tag2-tag3";
        String strTest2 = "tag1";
        String strTest3 = "tag1-";
        String strTest4 = "";
        String[] strings = strTest.split("\\-");
        String[] strings2 = strTest2.split("\\-");
        String[] strings3 = strTest3.split("\\-");
        String[] strings4 = strTest4.split("\\-");
        System.out.println(strings.length);
        System.out.println(strings2.length);
        System.out.println(strings3.length);
        System.out.println(strings4.length);
    }

    @Test
    public void dateConverte() {
        Date dateNow = new Date();
        System.out.println(dateNow);
        Date convertedDate = new Date(new java.sql.Date(dateNow.getTime()).getTime());
        System.out.println(convertedDate);
    }
}
