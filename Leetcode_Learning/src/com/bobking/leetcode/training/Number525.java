package com.bobking.leetcode.training;

import java.util.HashMap;

public class Number525 {

    // 参考：程序猿代码指南P384
    public int findMaxLength(int[] nums) {

        if (nums == null || nums.length == 0)
            return 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0)
                nums[i] = -1;
        }

        return maxLength(nums, 0);
    }

    private int maxLength(int[] nums, int target) {

        Integer res = 0;
        int sum = 0;
        // 前缀和
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, -1);

        for (int i = 0; i < nums.length; i++) {

            sum += nums[i];
            if (map.containsKey(sum - target)) {
                res = Math.max(res, i - map.get(sum - target));
            } else {
                map.put(sum, i);
            }
        }

        return res;
    }
}
