package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.Map;

public class Number560 {

    // 参考：https://leetcode-cn.com/problems/subarray-sum-equals-k/solution/bao-li-jie-fa-qian-zhui-he-qian-zhui-he-you-hua-ja/
    // 前缀和
    public int subarraySum(int[] nums, int k) {

        if (nums == null || nums.length < 1) {
            return 0;
        }

        // key为前缀和
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        // 对于下标为 0 的元素，前缀和为 0，个数为 1
        map.put(0, 1);
        int sum = 0;
        int res = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                res += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return res;
    }
}
