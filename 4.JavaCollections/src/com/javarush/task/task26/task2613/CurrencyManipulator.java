package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;

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

    public boolean hasMoney() {
        return !denominations.isEmpty();
    }

    public boolean isAmountAvailable(int expectedAmount){
        return getTotalAmount() >= expectedAmount;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        Map<Integer, Integer> result = new HashMap<>();
        Map<Integer, Integer> tempDenominations = new HashMap<>(denominations);

        List<Integer> sortedDenominations = new ArrayList<>(tempDenominations.keySet());
        Collections.sort(sortedDenominations, Collections.reverseOrder());

        for (Integer denomination : sortedDenominations) {
            int count = 0;

            while (expectedAmount >= denomination && tempDenominations.get(denomination) > 0) {
                expectedAmount -= denomination;
                tempDenominations.put(denomination, tempDenominations.get(denomination) - 1);
                count++;
            }

            if (count > 0) {
                result.put(denomination, count);
            }
        }


        if (expectedAmount > 0) {
            throw new NotEnoughMoneyException();
        }


        for (Map.Entry<Integer, Integer> entry : result.entrySet()) {
            denominations.put(entry.getKey(), denominations.get(entry.getKey()) - entry.getValue());
        }

        return result;
    }

}
