package com.bobking.leetcode.training;

public class Number1252 {

    public int oddCells1(int m, int n, int[][] indices) {

        int res = 0;
        int[][] matrix = new int[m][n];

        for (int[] index : indices) {
            for (int i = 0; i < n; i++)
                matrix[index[0]][i]++;
            for (int i = 0; i < m; i++)
                matrix[i][index[1]]++;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if ((matrix[i][j] & 1) != 0)
                    res++;
            }
        }

        return res;
    }

    public int oddCells2(int m, int n, int[][] indices) {

        int[] rows = new int[m];
        int[] cols = new int[n];

        for (int[] index : indices) {
            rows[index[0]]++;
            cols[index[1]]++;
        }

        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (((rows[i] + cols[j]) & 1) != 0)
                    res++;
            }
        }

        return res;
    }

    public int oddCells3(int m, int n, int[][] indices) {

        int[] rows = new int[m];
        int[] cols = new int[n];
        for (int[] index : indices) {
            rows[index[0]]++;
            cols[index[1]]++;
        }

        // 矩阵中位于 (x, y) 位置的数为奇数，当且仅当 rows[x] 和 cols[y] 中恰好有一个为奇数，一个为偶数
        // 设 rows 有 oddx​个奇数，cols 有 oddy​个奇数，因此对于 rows[x] 为偶数，那么在第 x 行有 oddy​
        // 个位置的数为奇数。对于 rows[x] 为奇数，那么在第 x 行有 oddy 个位置的数为偶数
        // 综上可以得到奇数的数目为
        // oddx​× (n − oddy) + (m − oddx) × oddy
        int oddx = 0;
        int oddy = 0;
        for (int i = 0; i < m; i++) {
            if ((rows[i] & 1) != 0)
                oddx++;
        }
        for (int i = 0; i < n; i++) {
            if ((cols[i] & 1) != 0)
                oddy++;
        }

        return oddx * (n - oddy) + (m - oddx) * oddy;
    }
}
