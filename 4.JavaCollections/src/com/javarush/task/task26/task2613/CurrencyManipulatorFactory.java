package com.javarush.task.task26.task2613;



import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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

}
