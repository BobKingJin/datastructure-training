package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.Map;

/**
 * @author BobKing
 * @create 2023-04-18 22:30
 */
public class Number2364 {

    // 参考：https://leetcode.cn/problems/count-number-of-bad-pairs/solution/by-endlesscheng-uam3/
    public long countBadPairs(int[] nums) {

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int t = nums[i] - i;
            map.put(t, map.getOrDefault(t, 0) + 1);
        }

        long ans = 0;
        for (int i : map.keySet())
            ans += (long) map.getOrDefault(i, 0) * (n - map.getOrDefault(i, 0));

        return ans / 2;
    }
}
