package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2021-07-26 10:37
 */
public class Number309 {

    // 参考：https://leetcode-cn.com/circle/article/qiAgHn/
    public int maxProfit1(int[] prices) {

        if (prices == null || prices.length < 1) {
            return 0;
        }

        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], (i >= 2 ? dp[i - 2][0] : 0) - prices[i]);
        }

        return dp[prices.length - 1][0];
    }

    // 参考：https://leetcode-cn.com/circle/article/qiAgHn/
    public int maxProfit2(int[] prices) {

        if (prices == null || prices.length == 0) {
            return 0;
        }

        int prevProfit0 = 0;
        int profit0 = 0;
        int profit1 = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            int nextProfit0 = Math.max(profit0, profit1 + prices[i]);
            int nextProfit1 = Math.max(profit1, prevProfit0 - prices[i]);
            prevProfit0 = profit0;
            profit0 = nextProfit0;
            profit1 = nextProfit1;
        }

        return profit0;
    }

}
