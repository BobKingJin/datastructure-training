package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2022-07-16 10:30
 */
public class Number1833 {

    // 参考：https://leetcode.cn/problems/maximum-ice-cream-bars/solution/gong-shui-san-xie-noxiang-xin-ke-xue-xi-yrhjx/
    public int maxIceCream(int[] costs, int coins) {

        int n = costs.length;
        Arrays.sort(costs);
        int ans = 0;

        for (int i = 0; i < n; i++) {
            if (coins >= costs[i]) {
                ans++;
                coins -= costs[i];
            }
        }
        return ans;
    }
}
