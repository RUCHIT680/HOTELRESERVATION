package com.cg.hotelreservation;

import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class HotelReservationTest {

    @Test
    public void inputDate() throws ParseException {
        String date="13sep2014";
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMMyyyy");
        Date date1=dateFormat.parse(date);
        System.out.println(date1);
    public void givenDate_shouldReturnLowestRate_whenPassedThroughInput() {
        UserInput userInput = new UserInput();
        Date start = userInput.returnStartDate("12sep2020");
        Date end = userInput.returnEndDate("13sep2020");
        int minRate = userInput.uc1(start,end);
        Assert.assertEquals(220,minRate);
    }
}