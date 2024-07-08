package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Waiter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Observable;

public class Restaurant {
    public static void main(String[] args) {

        Tablet tablet = new Tablet(5);
        Cook sanji = new Cook("Sanji");
        Waiter waiter = new Waiter();

        tablet.addObserver(sanji);
        sanji.addObserver(waiter);

        tablet.createOrder();
        tablet.createOrder();
        tablet.createOrder();
        tablet.createOrder();


    }

}
