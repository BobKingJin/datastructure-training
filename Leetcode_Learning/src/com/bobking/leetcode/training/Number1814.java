package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.Map;

/**
 * @author BobKing
 * @create 2022-12-13 10:52
 */
public class Number1814 {

    // 参考：https://leetcode.cn/problems/count-nice-pairs-in-an-array/solution/onjie-by-yxj33-mgj8/
    public int countNicePairs(int[] nums) {
        // nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])
        // nums[i] - rev(nums[i]) == nums[j] - rev(nums[j])
        int mod = (int) 1e9 + 7;
        Map<Integer, Integer> count = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            int key = nums[i] - rev(nums[i]);
            count.put(key, count.getOrDefault(key, 0) + 1);
        }
        long result = 0;
        for (int key : count.keySet()) {
            // long，因为 1 <= nums.length <= 10^5，int时 time * (time - 1) 会溢出
            long times = count.get(key);
            result = (result + (times) * (times - 1) / 2) % mod;
        }
        return (int) (result % mod);
    }

    private static int rev(int num) {
        int result = 0;
        while (num > 0) {
            int dig = num % 10;
            result = result * 10 + dig;
            num /= 10;
        }
        return result;
    }
}
