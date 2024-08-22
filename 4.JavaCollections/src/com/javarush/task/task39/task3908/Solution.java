package com.javarush.task.task39.task3908;

/* 
Возможен ли палиндром?
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        System.out.println(isPalindromePermutation("алл"));
    }

    public static boolean isPalindromePermutation(String s) {
        String str = s.toLowerCase();
        char[] array = str.toCharArray();
        int count = 0;

        List<Character> unicodeChar = new ArrayList<>();
        List<Character> characterList = new ArrayList<>();

        for (char temp : array){
            characterList.add(temp);
        }

        for (char temp : array){
            if (!unicodeChar.contains(temp)){
                unicodeChar.add(temp);
            }
        }

        for (Character temp : unicodeChar) {
            int freq = Collections.frequency(characterList, temp);
            if (freq % 2 != 0) {
                count++;
            }
        }

        if (s.length() % 2 == 0 && count == 0){
            return true;
        } else if (s.length() % 2 == 1 && count == 1){
            return true;
        }else {
            return false;
        }

    }


}
