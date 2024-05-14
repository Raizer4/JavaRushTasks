package com.javarush.task.task22.task2209;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/* 
Составить цепочку слов
*/

public class Solution {

    public static void main(String[] args) {
        String str = new Scanner(System.in).nextLine();
        StringBuilder result = getLine(str);
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words) {
        if (words.length == 0){
            return new StringBuilder();
        }

        StringBuilder resa = new StringBuilder();

        for (String temp : words){
            resa.append(temp);
        }

        String res = resa.toString();


        String[] s = res.split(" ");

        StringBuilder result = new StringBuilder();

        boolean flag = false;


        for (String temp : s){
            result.insert(0, temp + " ");
            char firstChar = temp.charAt(0);
            firstChar = Character.toLowerCase(firstChar);
            List<String> list = new ArrayList<>();
            String findString = null;
            for (String temp2 : s){
                if (temp.equalsIgnoreCase(temp2)){
                    continue;
                }
                char lastChar = temp2.charAt(temp2.length() - 1);
                lastChar = Character.toLowerCase(lastChar);
                if (lastChar == firstChar && flag == false){
                    flag = true;
                    findString = temp2;
                }else{
                    list.add(temp2);
                }
            }

            if (findString == null){
                list.clear();
            }else{
                for (String temp3 : list){
                    result.append(temp3 + " ");
                }
                result.append(findString + " ");
                return result;
            }

        }

        return null;
    }

}
