package com.javarush.task.task40.task4012;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.GregorianCalendar;

/* 
Полезные методы DateTime API
*/

public class Solution {

    public static void main(String[] args) {

    }

    public static boolean isLeap(LocalDate date) {
        return date.isLeapYear();
    }

    public static boolean isBefore(LocalDateTime dateTime) {
        return dateTime.isBefore(dateTime);
    }

    public static LocalTime addTime(LocalTime time, int n, ChronoUnit chronoUnit) {
        return time.plus(n,chronoUnit);
    }

    public static Period getPeriodBetween(LocalDate firstDate, LocalDate secondDate) {

        if (firstDate.isBefore(secondDate)){
            return Period.between(firstDate,secondDate);
        }else {
            return Period.between(secondDate,firstDate);
        }


    }

}
