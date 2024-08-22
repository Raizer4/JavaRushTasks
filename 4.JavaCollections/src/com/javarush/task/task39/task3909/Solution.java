package com.javarush.task.task39.task3909;

/* 
Одно изменение
*/

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Solution {
    public static void main(String[] args) {
        System.out.println(isOneEditAway("neg", "negr"));  // true
        System.out.println(isOneEditAway("pale", "ple"));  // true
        System.out.println(isOneEditAway("pales", "pale"));  // true
        System.out.println(isOneEditAway("pale", "bale"));  // true
        System.out.println(isOneEditAway("pale", "bake"));  // false
        System.out.println(isOneEditAway("", ""));  // true
        System.out.println(isOneEditAway("", "a"));  // true
        System.out.println(isOneEditAway("a", ""));  // true
        System.out.println(isOneEditAway("ab", "a"));  // true
        System.out.println(isOneEditAway("ab", "abc"));  // true
        System.out.println(isOneEditAway("abc", "adc"));  // true
        System.out.println(isOneEditAway("abc", "abcde"));  // false
    }

    public static boolean isOneEditAway(String first, String second) {
        int abs = Math.abs(first.length() - second.length());

        if (first.equals(second)){
            return true;
        }

        if (abs > 1){
            return false;
        }

        if (first.isEmpty() && second.isEmpty()){
            return true;
        }

        int firstLength = first.length();
        int secondLength = second.length();

        if (firstLength == secondLength){
            int count = 0;

            List<Character> firstList = stringToCharList(first);
            List<Character> secondList = stringToCharList(second);

            for (int i = 0; i < firstList.size(); i++){
                if (firstList.get(i) == secondList.get(i)){
                    count++;
                }
            }

            if (count == firstLength - 1){
                return true;
            }else {
                return false;
            }

        }
        else {
            List<Character> firstList = stringToCharList(first);
            List<Character> secondList = stringToCharList(second);

            List<Character> tempList_1 = new ArrayList<>(firstList);
            List<Character> tempList_2 = new ArrayList<>(secondList);

            for (Character temp : tempList_1){
                if (tempList_2.contains(temp)){
                    firstList.remove(temp);
                    secondList.remove(temp);
                }
            }


            if (firstList.size() == 1 && secondList.size() == 0 || firstList.size() == 0 && secondList.size() == 1){
                return true;
            }else {
                return false;
            }

        }

    }

    public static List<Character> stringToCharList(String s) {
        List<Character> charList = new ArrayList<>();
        for (char c : s.toCharArray()) {
            charList.add(c);
        }
        return charList;
    }

}
