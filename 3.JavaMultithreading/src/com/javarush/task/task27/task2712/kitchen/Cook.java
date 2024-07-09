package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Cook extends Observable implements Observer {

    public String name;

    public Cook(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable tablet, Object order) {

        Tablet tabletBase = (Tablet) tablet;
        Order orderBase = (Order) order;

        ConsoleHelper.writeMessage("Start cooking - " + order);

        setChanged();
        notifyObservers(order);

        CookedOrderEventDataRow cookedOrder = new CookedOrderEventDataRow(tabletBase.toString(),name,orderBase.getTotalCookingTime() * 60,orderBase.getDishes());
        StatisticManager.getInstance().register(cookedOrder);


    }


    @Override
    public String toString() {
        return name;
    }


}
