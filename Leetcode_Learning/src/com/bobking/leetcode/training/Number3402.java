package com.bobking.leetcode.training;

/**
 * @Date: 2025/6/15 16:51
 * @Author: BobKing
 * @Description:
 */
public class Number3402 {

    public int minimumOperations(int[][] grid) {
        int ans = 0;
        for (int j = 0; j < grid[0].length; j++) {
            int pre = -1;
            for (int[] row : grid) {
                int x = row[j];
                ans += Math.max(pre + 1 - x, 0);
                pre = Math.max(pre + 1, x);
            }
        }
        return ans;
    }

}
