package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2021-07-26 10:37
 */
public class Number714 {

    // 参考：https://leetcode-cn.com/circle/article/qiAgHn/
    public int maxProfit1(int[] prices, int fee) {

        if (prices == null || prices.length < 1)
            return 0;

        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0] - fee;

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i] - fee);
        }

        return dp[prices.length - 1][0];
    }

    public int maxProfit2(int[] prices, int fee) {

        if (prices == null || prices.length < 1)
            return 0;

        int profit0 = 0;
        int profit1 = -prices[0] - fee;

        for (int i = 1; i < prices.length; i++) {
            int newProfit0 = Math.max(profit0, profit1 + prices[i]);
            int newProfit1 = Math.max(profit1, profit0 - prices[i] - fee);

            profit0 = newProfit0;
            profit1 = newProfit1;
        }

        return profit0;
    }
}
