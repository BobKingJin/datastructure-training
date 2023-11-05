package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-11-03 7:45
 */
public class Number566 {

    public int[][] matrixReshape(int[][] mat, int r, int c) {

        int m = mat.length;
        int n = mat[0].length;
        if (m * n != r * c)
            return mat;

        int[][] ans = new int[r][c];
        for (int x = 0; x < m * n; ++x)
            ans[x / c][x % c] = mat[x / n][x % n];

        return ans;
    }
}
