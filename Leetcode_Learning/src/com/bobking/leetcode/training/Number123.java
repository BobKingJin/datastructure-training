package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2021-07-26 10:34
 */
public class Number123 {

    // 参考：https://leetcode-cn.com/circle/article/qiAgHn/
    public int maxProfit1(int[] prices) {

        if (prices == null || prices.length < 2)
            return 0;

        int[][][] dp = new int[prices.length][3][2];
        dp[0][1][0] = 0;
        dp[0][1][1] = -prices[0];
        dp[0][2][0] = 0;
        dp[0][2][1] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            dp[i][1][0] = Math.max(dp[i - 1][1][0], dp[i - 1][1][1] + prices[i]);
            dp[i][1][1] = Math.max(dp[i - 1][1][1], -prices[i]);
            dp[i][2][0] = Math.max(dp[i - 1][2][0], dp[i - 1][2][1] + prices[i]);
            dp[i][2][1] = Math.max(dp[i - 1][2][1], dp[i - 1][1][0] - prices[i]);
        }

        return dp[prices.length - 1][2][0];
    }

    // 参考：https://leetcode-cn.com/circle/article/qiAgHn/
    public int maxProfit2(int[] prices) {

        if (prices == null || prices.length < 2)
            return 0;

        int profitone0 = 0;
        int profitone1 = -prices[0];
        int profittwo0 = 0;
        int profittwo1 = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            profitone0 = Math.max(profitone0, profitone1 + prices[i]);
            profitone1 = Math.max(profitone1, -prices[i]);
            profittwo0 = Math.max(profittwo0, profittwo1 + prices[i]);
            profittwo1 = Math.max(profittwo1, profitone0 - prices[i]);
        }

        return profittwo0;
    }
}
