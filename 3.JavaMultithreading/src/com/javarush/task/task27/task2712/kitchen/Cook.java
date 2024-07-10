package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.LinkedBlockingQueue;

public class Cook  extends Observable implements Runnable{

    public String name;
    public boolean busy;

    private LinkedBlockingQueue<Order> queue = new LinkedBlockingQueue();

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    public boolean isBusy() {
        return busy;
    }

    public Cook(String name) {
        this.name = name;
    }

    public void startCookingOrder(Order order) throws InterruptedException {
        busy = true;
        ConsoleHelper.writeMessage("Start cooking - " + order);

        Thread.sleep(order.getTotalCookingTime() * 10);

        setChanged();
        notifyObservers(order);

        CookedOrderEventDataRow cookedOrder = new CookedOrderEventDataRow(order.getTablet().toString(),name,order.getTotalCookingTime() * 60,order.getDishes());
        StatisticManager.getInstance().register(cookedOrder);
        busy = false;
    }


    @Override
    public String toString() {
        return name;
    }


    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep(10);

                if (!queue.isEmpty()) {
                    if (!busy) {
                        startCookingOrder(queue.poll());
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
