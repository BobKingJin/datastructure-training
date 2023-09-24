package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2021-07-26 10:36
 */
public class Number188 {

    // 参考：https://leetcode-cn.com/circle/article/qiAgHn/
    public int maxProfit1(int k, int[] prices) {

        if (prices == null || prices.length < 2)
            return 0;

        // 当 k 大于 N / 2 时可以获取到的最大利润
        if (k >= prices.length / 2)
            return maxProfit1(prices);

        int[][][] dp = new int[prices.length][k + 1][2];
        for (int i = 1; i <= k; i++) {
            dp[0][i][0] = 0;
            dp[0][i][1] = -prices[0];
        }

        for (int i = 1; i < prices.length; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }

        return dp[prices.length - 1][k][0];
    }

    // 当 k 大于 N / 2 时可以获取到的最大利润
    private int maxProfit1(int[] prices) {

        if (prices == null || prices.length < 2)
            return 0;

        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }

        return dp[prices.length - 1][0];
    }

    // 参考：https://leetcode-cn.com/circle/article/qiAgHn/
    public int maxProfit2(int k, int[] prices) {

        if (prices == null || prices.length == 0)
            return 0;

        if (k >= prices.length / 2)
            return maxProfit2(prices);

        int[][] dp = new int[k + 1][2];
        for (int i = 1; i <= k; i++) {
            dp[i][0] = 0;
            dp[i][1] = -prices[0];
        }

        for (int i = 1; i < prices.length; i++) {
            for (int j = k; j > 0; j--) {
                dp[j][0] = Math.max(dp[j][0], dp[j][1] + prices[i]);
                dp[j][1] = Math.max(dp[j][1], dp[j - 1][0] - prices[i]);
            }
        }

        return dp[k][0];
    }

    private int maxProfit2(int[] prices) {

        if (prices == null || prices.length == 0)
            return 0;

        int profit0 = 0;
        int profit1 = -prices[0];
        int length = prices.length;
        for (int i = 1; i < length; i++) {
            int newProfit0 = Math.max(profit0, profit1 + prices[i]);
            int newProfit1 = Math.max(profit1, profit0 - prices[i]);
            profit0 = newProfit0;
            profit1 = newProfit1;
        }

        return profit0;
    }


}
