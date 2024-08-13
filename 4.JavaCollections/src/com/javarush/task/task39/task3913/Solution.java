package com.javarush.task.task39.task3913;

import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

public class Solution {

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Date after = formatter.parse("13.09.2011 5:04:50");
        Date before = formatter.parse("31.12.2022 23:59:59");

        LogParser logParser = new LogParser(Paths.get("C:\\Java Coding\\JavaRushTasks\\4.JavaCollections\\src\\com\\javarush\\task\\task39\\task3913\\logs"));

        System.out.println(logParser.getAllSolvedTasksAndTheirNumber(after,before));
    }

}