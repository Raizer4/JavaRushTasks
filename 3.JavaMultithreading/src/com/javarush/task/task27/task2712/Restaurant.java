package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.Waiter;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {

    private static final int ORDER_CREATING_INTERVAL = 100;
    private final static LinkedBlockingQueue<Order> ORDER_QUEUE = new LinkedBlockingQueue<>();

    public static void main(String[] args) {

        List<Tablet> list = new ArrayList<>();

        for (int i = 0; i < 5; i++){
            Tablet tablet = new Tablet(i);
            tablet.setQueue(ORDER_QUEUE);
            list.add(tablet);
        }

        Cook firstCook = new Cook("Amigo");
        firstCook.setQueue(ORDER_QUEUE);
        Cook secondCook = new Cook("Sanji");
        secondCook.setQueue(ORDER_QUEUE);


        Waiter waiter = new Waiter();


        RandomOrderGeneratorTask random = new RandomOrderGeneratorTask(list,ORDER_CREATING_INTERVAL);
        Thread thread = new Thread(random);
        thread.start();
        thread.interrupt();

        firstCook.addObserver(waiter);
        secondCook.addObserver(waiter);


        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
    }

}
