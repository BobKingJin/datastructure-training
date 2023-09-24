package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2023-05-01 9:49
 */
public class Number2195 {

    public long minimalKSum(int[] nums, int k) {

        Arrays.sort(nums);

        long ans = 0;
        int start = 1;

        for (int i = 0; i < nums.length && k > 0; i++) {
            // 存在未出现的数字
            if (start < nums[i]) {
                int cnt = (int) (nums[i] - start) > k ? k : (int) (nums[i] - start);
                // 不存在的数据累计
                ans += (2L * start + cnt - 1) * cnt / 2;
                k -= cnt;
            }
            start = nums[i] + 1;
        }

        // 不存在的数据累计
        if (k > 0)
            ans += (2L * start + k - 1) * k / 2;

        return ans;
    }
}
