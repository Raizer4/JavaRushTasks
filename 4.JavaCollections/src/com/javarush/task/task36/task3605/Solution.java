package com.javarush.task.task36.task3605;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;

/* 
Использование TreeSet
*/

public class Solution {

    public static void main(String[] args) throws IOException {
        TreeSet<String> set = new TreeSet<>();
        String path = args[0];

        try (FileReader reader = new FileReader(path)) {
            int line;
            while ((line = reader.read()) != -1) {
                char temp = Character.toLowerCase((char) line);
                if (isLatinLetter(temp)) {
                    set.add(String.valueOf((temp)));
                }
            }
        }


        while (set.size() > 5){
            set.pollLast();
        }

        for (String temp : set){
            System.out.print(temp);
        }
    }

    public static boolean isLatinLetter(char ch) {
        return Character.toString(ch).matches("[a-zA-Z]");
    }
}
