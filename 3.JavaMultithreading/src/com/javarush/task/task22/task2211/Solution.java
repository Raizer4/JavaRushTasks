package com.javarush.task.task22.task2211;

import java.io.*;
import java.nio.charset.StandardCharsets;

/* 
Смена кодировки
*/

public class Solution {

    public static void main(String[] args) throws IOException {

        String inputFileName = args[0];
        String outputFileName = args[1];

        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFileName), "Windows-1251"));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFileName), StandardCharsets.UTF_8))
        ){
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
        }



    }

}
