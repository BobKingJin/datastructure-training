package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2022-06-26 14:35
 */
public class Number1748 {

    public int sumOfUnique1(int[] nums) {

        Arrays.sort(nums);
        int n = nums.length;
        int ans = 0;

        for (int i = 0; i < n; ) {
            int j = i;
            while (j < n && nums[j] == nums[i])
                j++;
            if (j - i == 1)
                ans += nums[i];
            i = j;
        }

        return ans;
    }

    int[] cnt = new int[110];

    public int sumOfUnique2(int[] nums) {

        for (int i : nums)
            cnt[i]++;

        int ans = 0;

        for (int i = 1; i <= 100; i++) {
            if (cnt[i] == 1)
                ans += i;
        }

        return ans;
    }

}
