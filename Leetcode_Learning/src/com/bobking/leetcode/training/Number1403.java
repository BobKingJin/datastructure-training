package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Number1403 {

    public List<Integer> minSubsequence(int[] nums) {

        Arrays.sort(nums);
        int sum = 0;
        int cur = 0;
        int idx = nums.length - 1;

        for (int i : nums)
            sum += i;

        List<Integer> ans = new ArrayList<Integer>();
        while (cur <= sum) {
            sum -= nums[idx];
            cur += nums[idx];
            ans.add(nums[idx--]);
        }

        return ans;
    }
}
