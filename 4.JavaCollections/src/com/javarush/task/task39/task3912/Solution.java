package com.javarush.task.task39.task3912;

/* 
Максимальная площадь
*/

public class Solution {

    public static void main(String[] args) {

    }


    public static int maxSquare(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int maxSide = 0;

        int[][] dp = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                if (matrix[i][j] == 1) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }

            }
        }

        return maxSide * maxSide;
    }

}
