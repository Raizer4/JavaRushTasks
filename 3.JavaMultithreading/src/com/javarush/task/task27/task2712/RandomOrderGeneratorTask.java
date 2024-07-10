package com.javarush.task.task27.task2712;

import java.util.ArrayList;
import java.util.List;

public class RandomOrderGeneratorTask implements Runnable {

    private List<Tablet> tablets;
    private int interval;

    public RandomOrderGeneratorTask(List<Tablet> tablets, int interval) {
        this.tablets = tablets;
        this.interval = interval;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int index = (int) (Math.random() * tablets.size());
                tablets.get(index).createOrder();
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
