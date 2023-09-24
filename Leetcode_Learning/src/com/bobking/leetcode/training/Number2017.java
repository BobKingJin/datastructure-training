package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-06-28 7:50
 */
public class Number2017 {

    // 参考：https://leetcode.cn/problems/grid-game/solution/javaqian-zhui-he-by-merickbao-2-nq78/
    public long gridGame(int[][] grid) {

        int n = grid[0].length;
        long[][] p = new long[2][n + 1];

        for(int i = 1; i <= n; i++) {
            p[0][i] = p[0][i - 1] + grid[0][i - 1];
            p[1][i] = p[1][i - 1] + grid[1][i - 1];
        }

        long ans = Long.MAX_VALUE;
        for(int i = 1; i <= n; i++)
            ans = Math.min(ans, Math.max(p[0][n] - p[0][i], p[1][i - 1]));

        return ans;
    }
}
