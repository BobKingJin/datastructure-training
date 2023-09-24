package com.bobking.leetcode.training;

import java.util.HashMap;

public class Number325 {

    // 参考：程序猿代码指南P384
    public int maxSubArrayLen(int[] nums, int k) {

        if (nums == null || nums.length == 0)
            return 0;

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, -1);
        int len = 0;
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {

            sum += nums[i];
            if (map.containsKey(sum - k))
                len = Math.max(i - map.get(sum - k), len);
            if (!map.containsKey(sum))
                map.put(sum, i);
        }

        return len;
    }

}
