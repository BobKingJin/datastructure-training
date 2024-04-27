package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @Date: 2024/3/27 23:05
 * @Author: BobKing
 * @Description:
 */
public class Number2580 {

    public int countWays(int[][] ranges) {

        Arrays.sort(ranges, (a, b) -> a[0] - b[0]);

        int ans = 1;
        int maxR = -1;

        for (int[] p : ranges) {
            if (p[0] > maxR)
                ans = ans * 2 % 1_000_000_007;
            maxR = Math.max(maxR, p[1]);
        }
        return ans;
    }
}
