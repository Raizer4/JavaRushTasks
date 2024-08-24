package com.javarush.task.task26.task2613;



import java.util.*;

public class CurrencyManipulatorFactory {

    private CurrencyManipulatorFactory(){

    }

    private static Map<String,CurrencyManipulator> map = new HashMap<>();

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode){
        currencyCode = currencyCode.toUpperCase();
        if (!map.containsKey(currencyCode)){
            CurrencyManipulator currency = new CurrencyManipulator(currencyCode);
            map.put(currencyCode, currency);
        }
        return map.get(currencyCode);
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators(){
        Set<String> strings = map.keySet();
        List<CurrencyManipulator> result = new ArrayList<>();
        for (String temp : strings){
             result.add(map.get(temp));
        }
        return result;
    }


}
