package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2021-07-26 10:17
 */
public class Number121 {

    // 参考：https://leetcode-cn.com/circle/article/qiAgHn/
    // 股票买卖系列
    // 参考：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/solution/bao-li-mei-ju-dong-tai-gui-hua-chai-fen-si-xiang-b/
    public int maxProfit1(int[] prices) {

        if (prices == null || prices.length < 2)
            return 0;

        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }

        return dp[prices.length - 1][0];
    }

    // 参考：https://leetcode-cn.com/circle/article/qiAgHn/
    public int maxProfit2(int[] prices) {

        if (prices == null || prices.length < 2)
            return 0;

        int profit0 = 0;
        int profit1 = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            int newProfit0 = Math.max(profit0, profit1 + prices[i]);
            int newProfit1 = Math.max(profit1, -prices[i]);
            profit0 = newProfit0;
            profit1 = newProfit1;
        }

        return profit0;
    }

}
