package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2021-05-01 12:57
 */
public class Number174 {

    // 参考：程序猿代码指南P236
    public int calculateMinimumHP1(int[][] dungeon) {

        if (dungeon == null || dungeon.length == 0 || dungeon[0] == null || dungeon[0].length == 0)
            return 1;

        int row = dungeon.length;
        int col = dungeon[0].length;

        // dp[i][j] 表示从 (i, j) 出发走到右下角所需的最少血量
        int[][] dp = new int[row][col];
        dp[row - 1][col - 1] = dungeon[row - 1][col - 1] > 0 ? 1 : -dungeon[row - 1][col - 1] + 1;
        // 最后一列
        for (int i = row - 2; i >= 0; i--)
            dp[i][col - 1] = Math.max(dp[i + 1][col - 1] - dungeon[i][col - 1], 1);
        // 最后一行
        for (int j = col - 2; j >= 0; j--)
            dp[row - 1][j] = Math.max(dp[row - 1][j + 1] - dungeon[row - 1][j], 1);

        // 从下往上
        for (int i = row - 2; i >= 0; i--) {
            // 从右往左
            for (int j = col - 2; j >= 0; j--) {

                int right = Math.max(dp[i][j + 1] - dungeon[i][j], 1);
                int down = Math.max(dp[i + 1][j] - dungeon[i][j], 1);
                dp[i][j] = Math.min(right, down);
            }
        }

        return dp[0][0];
    }

    // 参考：程序猿代码指南P236
    public int calculateMinimumHP2(int[][] dungeon) {

        if (dungeon == null || dungeon.length == 0 || dungeon[0] == null || dungeon[0].length == 0)
            return 1;

        int more = Math.max(dungeon.length, dungeon[0].length);
        int less = Math.min(dungeon.length, dungeon[0].length);
        boolean rowMore = more == dungeon.length;
        int[] dp = new int[less];

        dp[less - 1] = dungeon[dungeon.length - 1][dungeon[0].length - 1] > 0 ? 1 :
                -dungeon[dungeon.length - 1][dungeon[0].length - 1] + 1;
        int row = 0;
        int col = 0;
        for (int j = less - 2; j >= 0; j--) {

            row = rowMore ? more - 1 : j;
            col = rowMore ? j : more - 1;
            dp[j] = Math.max(dp[j + 1] - dungeon[row][col], 1);
        }

        int choosen1 = 0;
        int choosen2 = 0;
        for (int i = more - 2; i >= 0; i--) {

            row = rowMore ? i : less - 1;
            col = rowMore ? less - 1 : i;
            dp[less - 1] = Math.max(dp[less - 1] - dungeon[row][col], 1);
            for (int j = less - 2; j >= 0; j--) {

                row = rowMore ? i : j;
                col = rowMore ? j : i;
                choosen1 = Math.max(dp[j] - dungeon[row][col], 1);
                choosen2 = Math.max(dp[j + 1] - dungeon[row][col], 1);
                dp[j] = Math.min(choosen1, choosen2);
            }
        }

        return dp[0];
    }
}
