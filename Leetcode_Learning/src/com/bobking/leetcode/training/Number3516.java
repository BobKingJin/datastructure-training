package com.bobking.leetcode.training;

/**
 * @Date: 2025/9/14 14:00
 * @Author: BobKing
 * @Description:
 */
public class Number3516 {

    public int findClosest(int x, int y, int z) {
        int a = Math.abs(x - z);
        int b = Math.abs(y - z);
        return a == b ? 0 : a < b ? 1 : 2;
    }

}
