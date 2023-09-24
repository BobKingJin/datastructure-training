package com.bobking.leetcode.training;

import java.util.HashSet;
import java.util.Set;

/**
 * @author BobKing
 * @create 2023-03-19 11:31
 */
public class Number2367 {

    public int arithmeticTriplets1(int[] nums, int diff) {

        int n = nums.length;
        Set<Integer> set = new HashSet<Integer>();
        int ans = 0;

        for (int i = 0; i < n; i++) {
            if (set.contains(nums[i] - 2 * diff) && set.contains(nums[i] - diff))
                ++ans;
            set.add(nums[i]);
        }
        return ans;
    }

    public int arithmeticTriplets2(int[] nums, int diff) {

        int n = nums.length;
        int cnt = 0;

        for (int i = 0, j = i + 1, k = j + 1; i < n; ++i) {
            while(j + 1 < n && nums[j] - nums[i] < diff)
                ++j;
            k = Math.max(k, j + 1);
            while(k < n && nums[k] - nums[j] < diff)
                ++k;
            if(k < n && nums[k] - nums[j] == diff && nums[j] - nums[i] == diff)
                ++cnt;
        }
        return cnt;
    }
}
