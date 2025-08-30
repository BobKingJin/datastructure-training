package com.bobking.leetcode.training;

/**
 * @Date: 2025/8/30 13:31
 * @Author: BobKing
 * @Description:
 */
public class Number3000 {

    public int areaOfMaxDiagonal(int[][] dimensions) {
        int ans = 0;
        int maxL = 0;
        for (int[] d : dimensions) {
            int x = d[0];
            int y = d[1];
            int l = x * x + y * y;
            if (l > maxL || (l == maxL && x * y > ans)) {
                maxL = l;
                ans = x * y;
            }
        }
        return ans;
    }

}
