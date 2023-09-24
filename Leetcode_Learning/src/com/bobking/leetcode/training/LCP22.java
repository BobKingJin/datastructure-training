package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-05-02 7:52
 */
public class LCP22 {

    public int paintingPlan(int n, int k) {

        int res = 0;

        if (k == 0)
            return 1;

        if (k == n * n)
            return 1;

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if ((i * n) + (j * n) - (i * j) == k)
                    res += permutation(i, n) * permutation(j, n);
            }
        }
        return res;
    }

    private int permutation(int x, int y) {

        if (x == 0)
            return 1;

        int n = 1;

        for (int i = 0; i < x; i++)
            n *= (y - i);

        for (int i = 1; i <= x; i++)
            n /= i;

        return n;
    }
}
