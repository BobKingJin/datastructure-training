package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-02-22 10:47
 */
public class Number1140 {

    // 参考：https://leetcode.cn/problems/stone-game-ii/solution/java-dong-tai-gui-hua-qing-xi-yi-dong-17xing-by-lg/
    public int stoneGameII(int[] piles) {

        int len = piles.length;
        int sum = 0;
        // dp[i][j] 表示剩余 [i : len - 1]堆时，M = j的情况下，先取的人能获得的最多石子数
        int[][] dp = new int[len][len + 1];

        for (int i = len - 1; i >= 0; i--) {
            sum += piles[i];
            for (int M = 1; M <= len; M++) {
                if (i + 2 * M >= len) {
                    dp[i][M] = sum;
                } else {
                    for (int x = 1; x <= 2 * M; x++)
                        dp[i][M] = Math.max(dp[i][M], sum - dp[i + x][Math.max(M, x)]);
                }
            }
        }
        return dp[0][1];
    }
}
