package com.javarush.task.task39.task3904;

import java.util.Arrays;

/* 
Лестница
*/

public class Solution {
    private static int n = 70;

    public static void main(String[] args) {
        System.out.println("The number of possible ascents for " + n + " steps is: " + numberOfPossibleAscents(n));
    }

    public static long numberOfPossibleAscents(int n) {

        if (n < 0) {
            return 0;
        }

        if (n == 0) {
            return 1;
        }

        if (n == 1) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }

        if (n == 3) {
            return 4;
        }

        int a = 1;
        int b = 2;
        int c = 4;

        int current = 0;

        for (int i = 4; i <= n; i++) {
            current = a + b + c;

            a = b;
            b = c;
            c = current;
        }

        return current;
    }

}

