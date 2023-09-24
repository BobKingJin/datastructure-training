package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2021-04-29 23:01
 */
public class Number518 {

    // 参考：程序猿代码指南P199
    // 暴力递归
    public int change1(int amount, int[] coins) {

        if (coins == null || coins.length == 0 || amount < 0)
            return 0;

        return recursion1(coins, 0, amount);
    }

    private int recursion1(int[] coins, int index, int amount) {

        int res = 0;
        // 递归结束条件
        if (index == coins.length) {
            res = amount == 0 ? 1 : 0;
        } else {
            for (int i = 0; i * coins[index] <= amount; i++)
                res += recursion1(coins, index + 1, amount - i * coins[index]);
        }

        return res;
    }

    // 参考：程序猿代码指南P200
    public int change2(int amount, int[] coins) {

        if (coins == null || coins.length == 0 || amount < 0)
            return 0;

        int[][] map = new int[coins.length + 1][amount + 1];
        return recursion2(coins, 0, amount, map);
    }

    private int recursion2(int[] coins, int index, int amount, int[][] map) {

        int res = 0;

        if (index == coins.length) {
            res = amount == 0 ? 1 : 0;
        } else {

            int mapValue = 0;
            for (int i = 0; i * coins[index] <= amount; i++) {
                mapValue = map[index + 1][amount - i * coins[index]];
                if (mapValue != 0) {
                    res += mapValue == -1 ? 0 : mapValue;
                } else {
                    res += recursion2(coins, index + 1, amount - i * coins[index], map);
                }
            }
            map[index][amount] = res == 0 ? -1 : res;
            return res;
        }

        return res;
    }

    // 参考：程序猿代码指南P201
    public int change3(int amount, int[] coins) {

        if (coins == null || coins.length == 0 || amount < 0)
            return 0;

        // dp[i][j] 表示 coins[0 ... i] 组成 j 的方法数
        int[][] dp = new int[coins.length][amount + 1];

        // 第一列的值全为 1
        for (int i = 0; i < dp.length; i++)
            dp[i][0] = 1;
        // 第一行的值
        for (int i = 0; i * coins[0] <= amount; i++)
            dp[0][i * coins[0]] = 1;

        for (int i = 1; i < coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                for (int k = 0; j - k * coins[i] >= 0; k++)
                    dp[i][j] += dp[i - 1][j - k * coins[i]];
            }
        }

        return dp[coins.length - 1][amount];
    }

    // 参考：程序猿代码指南P203
    public int change4(int amount, int[] coins) {

        if (coins == null || coins.length == 0 || amount < 0)
            return 0;

        int[][] dp = new int[coins.length][amount + 1];
        // 第一列的值全为 1
        for (int i = 0; i < dp.length; i++)
            dp[i][0] = 1;
        // 第一行的值
        for (int i = 0; i * coins[0] <= amount; i++)
            dp[0][i * coins[0]] = 1;

        for (int i = 1; i < coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                dp[i][j] = dp[i - 1][j];
                dp[i][j] += j - coins[i] >= 0 ? dp[i][j - coins[i]] : 0;
            }
        }

        return dp[coins.length - 1][amount];
    }

    // 参考：程序猿代码指南P203
    public int change5(int amount, int[] coins) {

        if (coins == null || coins.length == 0 || amount < 0)
            return 0;

        int[] dp = new int[amount + 1];
        for (int i = 0; i * coins[0] <= amount; i++)
            dp[i * coins[0]] = 1;

        for (int i = 1; i < coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                dp[j] += j - coins[i] >= 0 ? dp[j - coins[i]] : 0;
            }
        }

        return dp[amount];
    }
}
