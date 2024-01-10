package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @Date: 2023/12/18 8:13
 * @Author: BobKing
 * @Description:
 */
public class Number2500 {

    public int deleteGreatestValue(int[][] grid) {

        // 等价于把每行排序之后, 求每列的最大值

        for (int[] row : grid)
            Arrays.sort(row);

        int ans = 0;
        int n = grid[0].length;

        for (int j = 0; j < n; j++) {
            int mx = 0;
            for (int[] row : grid)
                mx = Math.max(mx, row[j]);
            ans += mx;
        }
        return ans;
    }

}
