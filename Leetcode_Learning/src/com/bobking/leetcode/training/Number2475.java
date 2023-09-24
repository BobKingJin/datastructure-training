package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2023-03-16 1:24
 */
public class Number2475 {

    // 参考：https://leetcode.cn/problems/number-of-unequal-triplets-in-array/solution/fei-bao-li-zuo-fa-by-endlesscheng-9ekp/
    public int unequalTriplets(int[] nums) {

        int cnt = 0;
        Arrays.sort(nums);

        int n = nums.length;
        for (int l = 0, r = 1; r < n; ) {
            if (nums[l] != nums[r]) {
                cnt += (r - l) * l * (n - r);
                l = r;
            } else {
                r++;
            }
        }
        return cnt;
    }
}
