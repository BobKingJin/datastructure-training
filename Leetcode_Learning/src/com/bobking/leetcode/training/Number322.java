package com.bobking.leetcode.training;

public class Number322 {

    // 参考：程序猿代码指南P189
    public int coinChange1(int[] coins, int amount) {

        if (coins == null || coins.length == 0 || amount < 0)
            return -1;

        return recursion(coins, 0, amount);
    }

    private int recursion(int[] coins, int i, int rest) {

        if (i == coins.length)
            return rest == 0 ? 0 : -1;

        int res = -1;
        // 注意：每种面值的货币可以用任意张
        for (int k = 0; k * coins[i] <= rest; k++) {
            int next = recursion(coins, i + 1, rest - k * coins[i]);
            if (next != -1)
                res = res == -1 ? next + k : Math.min(res, next + k);
        }
        return res;
    }

    // 参考：程序猿代码指南P189
    public int coinChange2(int[] coins, int amount) {

        if (coins == null || coins.length == 0 || amount < 0)
            return -1;

        int N = coins.length;
        // dp[i][j] 表示 coins[i ... (coins.length - 1)] 范围内组成 j 所需的最小硬币数
        int[][] dp = new int[N + 1][amount + 1];
        // 注意 col 是从 1 开始的 dp[N][0] = 0
        for (int col = 1; col <= amount; col++)
            dp[N][col] = -1;

        // 从下往上
        for (int i = N - 1; i >= 0; i--) {
            // 从左往右
            for (int rest = 0; rest <= amount; rest++) {
                dp[i][rest] = -1;
                // 不选 coins[i]
                if (dp[i + 1][rest] != -1)
                    dp[i][rest] = dp[i + 1][rest];
                // 选 coins[i]
                if (rest - coins[i] >= 0 && dp[i][rest - coins[i]] != -1) {
                    if (dp[i][rest] == -1) {
                        dp[i][rest] = dp[i][rest - coins[i]] + 1;
                    } else {
                        dp[i][rest] = Math.min(dp[i][rest], dp[i][rest - coins[i]] + 1);
                    }
                }
            }
        }

        return dp[0][amount];
    }

    public int coinChange3(int[] coins, int amount) {

        if (coins == null || coins.length == 0 || amount < 0)
            return -1;

        int N = coins.length;
        // dp[i][j] 表示 coins[0 ... i] 范围内组成 j 所需的最小硬币数
        int[][] dp = new int[N + 1][amount + 1];
        // 注意 col 是从 1 开始的 dp[0][0] = 0
        for (int col = 1; col <= amount; col++)
            dp[0][col] = -1;

        // 从上往下
        for (int i = 1; i <= N; i++) {
            // 从左往右
            for (int rest = 0; rest <= amount; rest++) {
                dp[i][rest] = -1;
                // 不选 coins[i]
                if (dp[i - 1][rest] != -1)
                    dp[i][rest] = dp[i - 1][rest];
                // 选 coins[i]
                if (rest - coins[i] >= 0 && dp[i - 1][rest - coins[i]] != -1) {
                    if (dp[i][rest] == -1) {
                        dp[i][rest] = dp[i - 1][rest - coins[i]] + 1;
                    } else {
                        dp[i][rest] = Math.min(dp[i][rest], dp[i][rest - coins[i]] + 1);
                    }
                }
            }
        }
        return dp[N][amount];
    }
}
