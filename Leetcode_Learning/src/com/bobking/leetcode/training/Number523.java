package com.bobking.leetcode.training;

import java.util.HashSet;
import java.util.Set;

/**
 * @author BobKing
 * @create 2022-06-25 10:04
 */
public class Number523 {

    // 参考：https://leetcode.cn/problems/continuous-subarray-sum/solution/gong-shui-san-xie-tuo-zhan-wei-qiu-fang-1juse/
    public boolean checkSubarraySum(int[] nums, int k) {

        int n = nums.length;
        int[] sum = new int[n + 1];
        // 前缀和
        for (int i = 1; i <= n; i++)
            sum[i] = sum[i - 1] + nums[i - 1];

        Set<Integer> set = new HashSet<Integer>();
        // 长度最小为 2，因此 i 从 2 开始
        for (int i = 2; i <= n; i++) {
            set.add(sum[i - 2] % k);
            if (set.contains(sum[i] % k))
                return true;
        }

        return false;
    }
}
