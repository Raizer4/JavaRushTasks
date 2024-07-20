package com.javarush.task.task32.task3204;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

/* 
Генератор паролей
*/

public class Solution {

    public static void main(String[] args)  {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword()  {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(8);
        String password = makePassword(8);
        try {
            outputStream.write(password.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return outputStream;
    }

    public static String makePassword(int length){
        String password = "";
        String randomDigit = randomCharacter("0123456789");

        for (int i = 0; i < length - 1; i++){
            password = password + randomCharacter("abcdefghijklmnopqrstuvwxyz");
        }


        password = insertUpperCase(password);
        password = insertAtRandom(password,randomDigit);

        return password;
    }



    private static String randomCharacter(String characters) {
        int r = (int) (characters.length() * Math.random());
        return characters.substring(r,r + 1);
    }

    private static String insertUpperCase(String password) {
        char[] array = password.toCharArray();
        Random random = new Random();
        int upperCaseCount = 0;

        while (upperCaseCount < 2) {
            int r = random.nextInt(password.length());
            if (Character.isLowerCase(array[r])) {
                array[r] = Character.toUpperCase(array[r]);
                upperCaseCount++;
            }
        }
        return new String(array);
    }

    private static String insertAtRandom(String password, String randomDigit) {
        int r = (int) ((password.length() + 1) * Math.random());
        return password.substring(0,r) + randomDigit + password.substring(r);
    }

}
