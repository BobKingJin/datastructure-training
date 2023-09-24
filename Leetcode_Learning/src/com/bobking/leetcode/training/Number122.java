package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2021-01-23 16:04
 */
public class Number122 {

    public int maxProfit1(int[] prices) {

        if (prices == null || prices.length < 2)
            return 0;

        return getMaxProfit1(prices, 0);
    }

    private int getMaxProfit1(int[] prices, int index) {

        int maxProfit = 0;

        for (int i = index; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] > prices[i]) {
                    int profit = prices[j] - prices[i] + getMaxProfit1(prices, j + 1);
                    maxProfit = Math.max(maxProfit, profit);
                }
            }
        }

        return maxProfit;
    }

    private int res;

    // 参考：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/solution/tan-xin-suan-fa-by-liweiwei1419-2/
    public int maxProfit2(int[] prices) {

        if (prices == null || prices.length < 2)
            return 0;

        res = 0;
        dfs(prices, 0, 0, 0);
        return res;
    }

    // status：0 表示不持有股票   1 表示持有股票
    private void dfs(int[] prices, int index, int status, int profit) {

        if (index == prices.length) {
            res = Math.max(res, profit);
            return;
        }

        dfs(prices, index + 1, status, profit);

        if (status == 0) {
            // 当 prices[index] 的 status 为 0 时，即 prices[index] 不持有股票，那么可以尝试 prices[index + 1] 的状态为持有股票
            // prices[index + 1]的状态为不持有股票在上面的递归中已经尝试过，因此这里是尝试持有股票，同时因为 prices[index + 1]
            // 的状态为持有股票所以当前利润为 profit - prices[index]，即当前所获得的利润减去持有的股票价格
            dfs(prices, index + 1, 1, profit - prices[index]);
        } else {
            // 同理
            dfs(prices, index + 1, 0, profit + prices[index]);
        }
    }

    // 参考：https://leetcode-cn.com/circle/article/qiAgHn/
    public int maxProfit3(int[] prices) {

        if (prices == null || prices.length < 2)
            return 0;

        // dp[i][j] i 表示在第 i 天，j 表示持有股票的状态，0：表示不持有 1：表示持有
        // 即在第 i 天后持有 0 份股票或者 1 份股票
        int[][] dp = new int[prices.length][2];

        dp[0][0] = 0;
        dp[0][1] = 0 - prices[0];

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }

        return dp[prices.length - 1][0];
    }

    // 参考：https://leetcode-cn.com/circle/article/qiAgHn/
    // 第 i 天的最大收益只和第 i - 1 天的最大收益相关，空间复杂度可以降到 O(1)
    // 即 dp[i][j] 只与 dp[i - 1][j] 有关
    public int maxProfit4(int[] prices) {

        if (prices == null || prices.length < 2)
            return 0;

        int profit0 = 0;
        int profit1 = 0 - prices[0];

        for (int i = 1; i < prices.length; i++) {
            int newProfit0 = Math.max(profit0, profit1 + prices[i]);
            int newProfit1 = Math.max(profit1, profit0 - prices[i]);
            profit0 = newProfit0;
            profit1 = newProfit1;
        }

        return profit0;
    }
}
