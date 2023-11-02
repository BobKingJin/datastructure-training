package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-10-26 8:12
 */
public class Number440 {

    public int findKthNumber(int n, int k) {

        int ans = 1;

        while (k > 1) {
            int cnt = getCnt(ans, n);
            if (cnt < k) {
                k -= cnt;
                ans++;
            } else {
                k--;
                ans *= 10;
            }
        }
        return ans;
    }

    private int getCnt(int x, int limit) {

        String a = String.valueOf(x);
        String b = String.valueOf(limit);
        int n = a.length();
        int m = b.length();
        int k = m - n;
        int ans = 0;
        int u = Integer.parseInt(b.substring(0, n));

        for (int i = 0; i < k; i++)
            ans += Math.pow(10, i);

        if (u > x) {
            ans += Math.pow(10, k);
        } else if (u == x) {
            ans += limit - x * Math.pow(10, k) + 1;
        }
        return ans;
    }

}
