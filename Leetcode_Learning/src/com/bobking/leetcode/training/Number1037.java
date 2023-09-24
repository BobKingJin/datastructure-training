package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-03-05 11:49
 */
public class Number1037 {

    // 参考：https://leetcode.cn/problems/valid-boomerang/solution/by-ac_oier-eory/
    public boolean isBoomerang(int[][] points) {
        return (points[1][0] - points[0][0]) * (points[2][1] - points[0][1]) != (points[2][0] - points[0][0]) * (points[1][1] - points[0][1]);
    }
}
