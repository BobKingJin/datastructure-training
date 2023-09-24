package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2022-12-10 10:06
 */
public class Number1691 {

    // 参考：https://leetcode.cn/problems/maximum-height-by-stacking-cuboids/solution/tu-jie-suan-fa-you-hua-xiang-xi-zheng-mi-b6fq/
    public int maxHeight(int[][] cuboids) {

        int n = cuboids.length;
        int ans = 0;

        for (int i = 0; i < n; i++)
            Arrays.sort(cuboids[i]);

        Arrays.sort(cuboids, (a, b) -> {
            if (a[0] != b[0]) {
                return b[0] - a[0];
            } else if (a[1] != b[1]) {
                return b[1] - a[1];
            } else {
                return b[2] - a[2];
            }
        });

        int[] dp = new int[n];
        dp[0] = cuboids[0][2];
        ans = Math.max(ans, dp[0]);

        // 最长递增子序列
        for (int i = 1; i < n; i++) {
            dp[i] = cuboids[i][2];
            for (int j = 0; j < i; j++) {
                if (cuboids[j][0] >= cuboids[i][0] && cuboids[j][1] >= cuboids[i][1] && cuboids[j][2] >= cuboids[i][2])
                    dp[i] = Math.max(dp[i], dp[j] + cuboids[i][2]);
            }
            ans = Math.max(dp[i], ans);
        }
        return ans;
    }
}
