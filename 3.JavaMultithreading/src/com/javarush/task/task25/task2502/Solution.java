package com.javarush.task.task25.task2502;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* 
Машину на СТО не повезем!
*/

public class Solution {
    public static enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {

        protected List<Wheel> wheels;

        public Car() {

            wheels = new ArrayList<>();

                String[] strings = loadWheelNamesFromDB();

                if (strings.length != 4){
                    throw new NullPointerException();
                }

                for (int i = 0; i < strings.length; i++) {
                    Wheel wheel = Wheel.valueOf(strings[i]);
                    wheels.add(wheel);
                }


            //init wheels here
        }

        protected String[] loadWheelNamesFromDB() {
            //this method returns mock data
            return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT"};
        }

    }

    public static void main(String[] args) {

        Car car = new Car();
        for (Wheel wheel : car.wheels){
            System.out.println(wheel);
        }

    }
}
