package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.OurHashMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.StorageStrategy;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {

    public static void main(String[] args) {
        testStrategy(new OurHashMapStorageStrategy(),10000);
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings){
        Set<Long> result = new HashSet<>();

        for (String temp : strings){
            result.add(shortener.getId(temp));
        }

        return result;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys){
        Set<String> result = new HashSet<>();

        for (Long temp : keys){
            if (shortener.getString(temp) != null){
                result.add(shortener.getString(temp));
            }
        }

        return result;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName());

        Set<String> strings = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++) {
            strings.add(Helper.generateRandomString());
        }

        Shortener shortener = new Shortener(strategy);

        Date startTime = new Date();
        Set<Long> ids = getIds(shortener, strings);
        Date endTime = new Date();
        long idTime = endTime.getTime() - startTime.getTime();
        Helper.printMessage(String.valueOf(idTime));

        Date startTwoTime = new Date();
        Set<String> st1 = getStrings(shortener, ids);
        Date endTwoTime = new Date();
        long stTime = endTwoTime.getTime() - startTwoTime.getTime();
        Helper.printMessage(String.valueOf(stTime));

        if (ids.size() == st1.size()){
            Helper.printMessage("Тест пройден.");
        }else {
            Helper.printMessage("Тест не пройден.");
        }

    }




}
