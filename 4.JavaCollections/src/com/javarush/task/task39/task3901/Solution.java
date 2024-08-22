package com.javarush.task.task39.task3901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* 
Уникальные подстроки
*/

public class Solution {

    public static void main(String[] args) throws IOException {
      //  BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
       // System.out.println("Please enter your string: ");
      //  String s = bufferedReader.readLine();

     //   System.out.println("The longest unique substring length is: \n" + lengthOfLongestUniqueSubstring(s));
        System.out.println("empty string (0) - " + lengthOfLongestUniqueSubstring(""));
        System.out.println("null string (0) - " + lengthOfLongestUniqueSubstring(null));
        System.out.println("qwerty (6) - " + lengthOfLongestUniqueSubstring("qwerty"));
        System.out.println("a123bcbcqwe (6) - " + lengthOfLongestUniqueSubstring("a123bcbcqwe"));
        System.out.println("ttttwt (2) - " + lengthOfLongestUniqueSubstring("ttttwt"));
        System.out.println("q (1) - " + lengthOfLongestUniqueSubstring("q"));
        System.out.println("еееееее (1) - " + lengthOfLongestUniqueSubstring("еееееее"));
    }

    public static int lengthOfLongestUniqueSubstring(String s) {

        if (s == null || s.isEmpty()){
            return 0;
        }

        List<Character> list = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        int count = 0;

        for (Character temp : s.toCharArray()){
            if (!list.contains(Character.toLowerCase(temp))){
                list.add(Character.toLowerCase(temp));
                count++;
                result.add(count);
            }else {
                list.clear();
                result.add(count);
                count = 0;
            }
        }

        return Collections.max(result);
    }


}
