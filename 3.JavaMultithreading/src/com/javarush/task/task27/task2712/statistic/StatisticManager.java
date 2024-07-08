package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.ad.AdvertisementStorage;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;

public class StatisticManager {

    private static class InstanceHolder {
        private static final StatisticManager ourInstance = new StatisticManager();
    }

    private StatisticManager() {
    }

    public static StatisticManager getInstance(){
        return StatisticManager.InstanceHolder.ourInstance;
    }

    public void register(EventDataRow data) {

    }


}
