package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-12-20 10:37
 */
public class Number997 {

    public int findJudge(int n, int[][] trust) {

        int[] in = new int[n + 1];
        int[] out = new int[n + 1];

        for (int[] t : trust) {
            int a = t[0];
            int b = t[1];
            in[b]++;
            out[a]++;
        }

        for (int i = 1; i <= n; i++) {
            if (in[i] == n - 1 && out[i] == 0)
                return i;
        }

        return -1;
    }
}
