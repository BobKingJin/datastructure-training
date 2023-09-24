package com.bobking.leetcode.training;

public class Number746 {

    public int minCostClimbingStairs(int[] cost) {

        if(cost == null || cost.length == 0)
            return 0;

        // dp[i] 表示到达第 i 级台阶的最小花费
        int[] dp = new int[cost.length];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < cost.length; i++)
            dp[i] = Math.min(dp[i - 2], dp[i - 1]) + cost[i];

        return Math.min(dp[cost.length - 2], dp[cost.length - 1]);
    }
}
