package com.javarush.task.task26.task2613;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer,Integer> denominations = new HashMap<>();

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count){
        if (denominations.containsKey(denomination)){
            Integer amount = denominations.get(denomination);
            int newValue = amount + count;
            denominations.put(denomination,newValue);
        }else {
            denominations.put(denomination,count);
        }
    }

    public int getTotalAmount(){
        Set<Integer> arr = denominations.keySet();

        int count = 0;

        for (Integer temp : arr){
            int newInt = temp * denominations.get(temp);
            count += newInt;
        }

        return count;
    }

}
