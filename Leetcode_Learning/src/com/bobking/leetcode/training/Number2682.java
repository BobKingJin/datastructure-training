package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-09-20 8:22
 */
public class Number2682 {

    public int[] circularGameLosers(int n, int k) {

        boolean[] vis = new boolean[n];
        int m = n;

        for (int i = 0, d = k; !vis[i]; d += k, m--) {
            vis[i] = true;
            i = (i + d) % n;
        }

        int[] ans = new int[m];

        for (int i = 0, j = 0; i < n; i++) {
            if (!vis[i])
                ans[j++] = i + 1;
        }

        return ans;
    }
}
