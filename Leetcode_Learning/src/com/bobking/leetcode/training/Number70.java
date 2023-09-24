package com.bobking.leetcode.training;

public class Number70 {

    // 参考：程序猿代码指南P179
    public int climbStairs1(int n) {

        if (n <= 0)
            return 0;

        if (n == 1 || n == 2)
            return n;

        return climbStairs1(n - 1) + climbStairs1(n - 2);
    }

    // 参考：程序猿代码指南P179
    public int climbStairs2(int n) {

        if (n <= 0)
            return 0;

        if (n == 1 || n == 2)
            return n;

        int res = 2;
        int pre = 1;
        int temp = 0;
        for (int i = 3; i <= n; i++) {
            temp = res;
            res += pre;
            pre = temp;
        }

        return res;
    }

    // 参考：程序猿代码指南P179
    public int climbStairs3(int n) {

        if (n < 1)
            return 0;

        if (n == 1 || n == 2)
            return n;

        int[][] base = {{1, 1}, {1, 0}};
        int[][] res = matrixPower(base, n - 2);
        return 2 * res[0][0] + res[1][0];
    }

    private int[][] matrixPower(int[][] m, int p) {

        int[][] res = new int[m.length][m[0].length];
        //先将res设置为单位矩阵
        for (int i = 0; i < res.length; i++)
            res[i][i] = 1;

        int[][] tmp = m;
        for (; p != 0; p >>= 1) {
            if ((p & 1) == 1) {
                res = multiMatrix(res, tmp);
            }
            tmp = multiMatrix(tmp, tmp);
        }

        return res;
    }

    private int[][] multiMatrix(int[][] m1, int[][] m2) {

        int[][] res = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2.length; k++) {
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }

        return res;
    }
}
