package com.javarush.task.task27.task2712.statistic;


import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.text.SimpleDateFormat;
import java.util.*;

public class StatisticManager {

    private StatisticManager() {
    }

    private static StatisticManager ourInstance = new StatisticManager();

    private StatisticStorage statisticStorage = new StatisticStorage();

    public static StatisticManager getInstance() {
        return ourInstance;
    }




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

        private List<EventDataRow> get(EventType type){

            if (!this.storage.containsKey(type)){
                throw new UnsupportedOperationException();
            }

            return this.storage.get(type);
        }

    }


    public void register(EventDataRow data) {
        this.statisticStorage.put(data);
    }


    public Map<String, Long> getProfitMap() {

        Map<String, Long> res = new HashMap();
        List<EventDataRow> eventDataRows = statisticStorage.get(EventType.SELECTED_VIDEOS);
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        long total = 0L;


        for (EventDataRow row : eventDataRows) {
            VideoSelectedEventDataRow dataRow = (VideoSelectedEventDataRow) row;

            String date = format.format(dataRow.getDate());

            if (!res.containsKey(date)) {
                res.put(date, 0l);
            }

            total += dataRow.getAmount();

            res.put(date, res.get(date) + dataRow.getAmount());

        }

        res.put("Total", total);

        return res;
    }

    public Map<String, Map<String,Integer>> getCookWorkloadingMap(){

        Map<String,Map<String,Integer>> result = new HashMap<>();
        List<EventDataRow> eventDataRows = statisticStorage.get(EventType.COOKED_ORDER);
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);


        for (EventDataRow temp : eventDataRows){

            CookedOrderEventDataRow cookOrder = (CookedOrderEventDataRow) temp;

            String date = format.format(cookOrder.getDate());

            if (!result.containsKey(date)) {
                result.put(date, new HashMap<String, Integer>());
            }

            Map<String,Integer> cookMap = result.get(date);
            String cookName = cookOrder.getCookName();

            if (!cookMap.containsKey(cookName)) {
                cookMap.put(cookName, 0);
            }

            Integer totalTime = cookMap.get(cookName);

            cookMap.put(cookName, totalTime + temp.getTime());
        }

        return result;

    }

}
