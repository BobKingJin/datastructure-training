package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2023-05-21 9:53
 */
public class Number2517 {

    // 参考：https://leetcode.cn/problems/maximum-tastiness-of-candy-basket/solution/er-fen-da-an-by-endlesscheng-r418/
    public int maximumTastiness(int[] price, int k) {

        Arrays.sort(price);
        int n = price.length;
        int l = Integer.MAX_VALUE;
        int r = (price[n - 1] - price[0]) / (k - 1);

        for (int i = 1; i < n; i++)
            l = Math.min(l, price[i] - price[i - 1]);

        while (l < r) {
            int mid = ((r - l + 1) >> 1) + l;
            if (k > getCnt(price, mid)) {
                r = mid - 1;
            } else {
                l = mid;
            }
        }
        return r;
    }

    private int getCnt(int[] price, int x) {
        int cnt = 1;
        for (int l = 0, r = 1; r < price.length; r++)
            if (x <= price[r] - price[l]) {
                cnt++;
                l = r;
            }
        return cnt;
    }
}
