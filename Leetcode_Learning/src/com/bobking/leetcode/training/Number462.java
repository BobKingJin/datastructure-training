package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2022-09-11 17:07
 */
public class Number462 {

    // 参考：https://leetcode.cn/problems/minimum-moves-to-equal-array-elements-ii/solution/by-fuxuemingzhu-13z3/
    public int minMoves2(int[] nums) {

        Arrays.sort(nums);
        int N = nums.length;
        int mid = nums[N / 2];
        int res = 0;

        for (int n : nums)
            res += Math.abs(n - mid);

        return res;
    }
}
