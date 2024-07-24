package com.javarush.task.task34.task3403;

/* 
Разложение на множители с помощью рекурсии
*/

public class Solution {

    public void recurse(int n) {
        if (n == 1) {
            return;
        }

        int delitel = 2;
        while (n % delitel != 0) {
            delitel++;
        }

        System.out.println(delitel);
        recurse(n / delitel);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.recurse(30);
    }
}
