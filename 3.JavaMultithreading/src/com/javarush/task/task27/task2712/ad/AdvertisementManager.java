package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;
import com.javarush.task.task27.task2712.kitchen.Order;

import java.util.List;
import java.util.logging.Level;

public class AdvertisementManager {

    final AdvertisementStorage storage = AdvertisementStorage.getInstance();

    private int timeSeconds;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() {

        if (storage.list().isEmpty()) {
            throw new NoVideoAvailableException();
        }



    }

}

