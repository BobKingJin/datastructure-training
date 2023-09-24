package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-10-15 10:07
 */
public class Interview08_11 {

    // 参考：https://leetcode.cn/problems/coin-lcci/solution/dong-tai-gui-hua-wan-quan-bei-bao-wen-ti-by-eddiev/
    public int waysToChange1(int n) {


        // 错误！！！错误！！！错误！！！

        int[] dp = new int[n + 1];

        int[] coins = new int[]{1, 5, 10, 25};
        // dp[0] 的含义是：组成 0 的硬币种类数为 1，（没有硬币也是一种情况）
        dp[0] = 1;

        // dp方程：dp[i] += dp[i - coin];
        for (int i = 1; i <= n; i++) {
            for (int coin : coins) {
                if (i - coin < 0)
                    break;
                dp[i] = (dp[i] + dp[i - coin]) % 1000000007;
            }
        }

        return dp[n];
    }

    // 参考：https://leetcode.cn/problems/coin-lcci/solution/dong-tai-gui-hua-wan-quan-bei-bao-wen-ti-by-eddiev/
    public int waysToChange2(int n) {

        int[] dp = new int[n + 1];

        int[] coins = new int[]{1, 5, 10, 25};

        dp[0] = 1;

        // 先遍历硬币，保证在考虑一枚硬币的情况时，没有较大的硬币影响，这样，最终每种组合情况，都是以硬币的面额大小非递减组合
        // 保证了同样的情况，调换顺序后重复计算的情况
        for (int coin : coins) {
            for (int i = coin; i <= n; i++)
                dp[i] = (dp[i] + dp[i - coin]) % 1000000007;
        }

        return dp[n];
    }

    // 参考：https://leetcode.cn/problems/coin-lcci/solution/java-wan-quan-bei-bao-xiang-xi-ti-jie-yu-yi-bu-bu-/
    public int waysToChange3(int n) {

        int[] coins = new int[]{1, 5, 10, 25};
        int[][] dp = new int[5][n + 1];

        for (int i = 1; i <= 4; i++)
            dp[i][0] = 1;

        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= n; j++) {
                if (j - coins[i - 1] < 0) {
                    dp[i][j] = dp[i - 1][j] % 1000000007;
                } else {
                    dp[i][j] = (dp[i - 1][j] + dp[i][j - coins[i - 1]]) % 1000000007;
                }
            }
        }
        return dp[4][n];
    }


}
