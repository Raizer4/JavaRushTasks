package com.javarush.task.task27.task2712.statistic;


import com.javarush.task.task27.task2712.ad.AdvertisementStorage;
import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;

import java.util.*;

public class StatisticManager {

    private static StatisticManager ourInstance = new StatisticManager();

    public static StatisticManager getInstance() {
        return ourInstance;
    }

    private StatisticStorage statisticStorage = new StatisticStorage();

    private StatisticManager() {
    }

    private Set<Cook> cooks = new HashSet<>();

    private class StatisticStorage {

        private Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        private StatisticStorage() {
            for (EventType temp : EventType.values()){
                this.storage.put(temp,new ArrayList<EventDataRow>());
            }
        }

        private void put(EventDataRow data){
            EventType type = data.getType();

            if (!this.storage.containsKey(type))
                throw new UnsupportedOperationException();

            this.storage.get(type).add(data);
        }

    }


    public void register(Cook cook){
        cooks.add(cook);
    }

    public void register(EventDataRow data) {
        this.statisticStorage.put(data);
    }


}
