package com.bobking.leetcode.training;

/**
 * @Date: 2024/11/12 10:36
 * @Author: BobKing
 * @Description:
 */
public class LCR189 {

    public int mechanicalAccumulator(int target) {
        int sum = target;
        // sum > 0 这个判断是充当递归结束条件, 因为不能使用 if
        boolean flag = (sum > 0) && ((sum += mechanicalAccumulator(--target)) > 0);
        return sum;
    }
}
