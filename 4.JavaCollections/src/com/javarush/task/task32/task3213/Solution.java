package com.javarush.task.task32.task3213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

/* 
Шифр Цезаря
*/

public class Solution {


    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader("Khoor#Dpljr#&C,₷B'3");
        System.out.println(decode(reader, -3));  //Hello Amigo #@)₴?$0
    }

    public static String decode(StringReader reader, int key) throws IOException {
        if (reader == null) {
            return "";
        }

        StringBuilder decodedString = new StringBuilder();
        int len;
        while ((len = reader.read()) != -1) {
            decodedString.append((char) (len + key));
        }
        return decodedString.toString();
    }



}
