package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-09-20 8:01
 */
public class Number1572 {

    public int diagonalSum(int[][] mat) {

        int n = mat.length;
        int sum = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == j || i + j == n - 1)
                    sum += mat[i][j];
            }
        }
        return sum;
    }
}
