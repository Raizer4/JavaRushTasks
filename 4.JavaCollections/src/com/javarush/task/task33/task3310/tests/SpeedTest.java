package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SpeedTest {

    @Test
    public void testHashMapStorage(){
        HashMapStorageStrategy shortener1 = new HashMapStorageStrategy();
        HashBiMapStorageStrategy shortener2 = new HashBiMapStorageStrategy();

        Set<String> origStrings  = new HashSet<>();
        HashSet<Long> ids = new HashSet<>();

        for (int i = 0; i < 10000; i++){
            origStrings.add(Helper.generateRandomString());
        }

        long timeTaken1 = getTimeToGetIds(new Shortener(shortener1), origStrings, ids);
        long timeTaken2 = getTimeToGetIds(new Shortener(shortener2), origStrings, ids);

        Assert.assertTrue(timeTaken1 > timeTaken2);

        long timeStringTaken1 = getTimeToGetStrings(new Shortener(shortener1), ids, origStrings);
        long timeStringTaken2 = getTimeToGetStrings(new Shortener(shortener2), ids, origStrings);

        Assert.assertEquals(timeStringTaken1,timeStringTaken2,30);
    }

    public long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids){
        Date startTime = new Date();
        for (String temp : strings){
            ids.add(shortener.getId(temp));
        }
        Date endTime = new Date();
        return endTime.getTime() - startTime.getTime();
    }

    public long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings){
        Date startTime = new Date();
        for (Long temp : ids){
           strings.add(shortener.getString(temp));
        }
        Date endTime = new Date();
        return endTime.getTime() - startTime.getTime();
    }


}
