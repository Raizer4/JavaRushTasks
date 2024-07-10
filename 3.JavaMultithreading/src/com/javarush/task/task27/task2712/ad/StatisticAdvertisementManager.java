package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticAdvertisementManager {

    private static final StatisticAdvertisementManager ourInstance = new StatisticAdvertisementManager();
    private StatisticAdvertisementManager(){}

    public static StatisticAdvertisementManager getInstance(){
        return StatisticAdvertisementManager.ourInstance;
    }

    private AdvertisementStorage storage = AdvertisementStorage.getInstance();


  /*  public List<Advertisement> getActiveVideoSet() {
        List<Advertisement> result = new ArrayList<>();
        List<Advertisement> list = storage.list();
        for (Advertisement temp : list) {
            if (temp.getHits() > 0) {
                result.add(temp);
            }
        }
        return result;
    }

    public List<Advertisement> getArchivedVideoSet(){
        List<Advertisement> result = new ArrayList<>();
        List<Advertisement> list = storage.list();
        for (Advertisement temp : list) {
            if (temp.getHits() == 0) {
                result.add(temp);
            }
        }
        return result;
    }*/

    public List<Advertisement> getVideoSet(boolean isActive) {
        List<Advertisement> result = new ArrayList<>();
        for (Advertisement advertisement : storage.list()) {
            if (!isActive ^ advertisement.isActive()) {
                result.add(advertisement);
            }
        }
        return result;
    }

}
