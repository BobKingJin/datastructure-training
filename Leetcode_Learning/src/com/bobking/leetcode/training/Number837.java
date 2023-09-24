package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-11-30 15:10
 */
public class Number837 {

    // 参考：https://leetcode.cn/problems/new-21-game/solution/huan-you-bi-zhe-geng-jian-dan-de-ti-jie-ma-tian-ge/
    // 参考：https://leetcode.cn/problems/new-21-game/solution/xin-21dian-by-leetcode-solution/
    public double new21Game(int n, int k, int maxPts) {

        if (k == 0)
            return 1.0;

        double[] dp = new double[k + maxPts];
        for (int i = k; i <= n && i < k + maxPts; i++)
            dp[i] = 1.0;

        for (int i = k - 1; i >= 0; i--) {
            for (int j = 1; j <= maxPts; j++)
                dp[i] += dp[i + j] / maxPts;
        }

        return dp[0];
    }
}
