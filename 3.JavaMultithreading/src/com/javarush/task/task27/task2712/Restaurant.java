package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Waiter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Observable;

public class Restaurant {
    public static void main(String[] args) {
        Tablet tablet = new Tablet(5);
        Cook firstCook = new Cook("Amigo");
        Waiter waiter = new Waiter();
        tablet.addObserver(firstCook);
        tablet.createOrder();
        firstCook.addObserver(waiter);
        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
    }

}
