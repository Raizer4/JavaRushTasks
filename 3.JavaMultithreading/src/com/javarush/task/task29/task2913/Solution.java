package com.javarush.task.task29.task2913;

import java.util.Random;

/* 
Замена рекурсии
*/

public class Solution {
    private static int numberA;
    private static int numberB;

    public static String getAllNumbersBetween(int a, int b) {
        String temp = "";

        if (a > b) {
            for (int i = b; i <= a; i++) {
                temp += i;
                if (i != a) {
                    temp += " ";
                }
            }
        } else {
            for (int i = b; i >= a; i--) {
                temp += i;
                if (i != a) {
                    temp += " ";
                }
            }
        }

        return temp;
    }

    public static void main(String[] args) {
        Random random = new Random();
        numberA = random.nextInt(1000);
        numberB = random.nextInt(1000);
        System.out.println(getAllNumbersBetween(numberA, numberB));
        System.out.println(getAllNumbersBetween(numberB, numberA));
    }
}

