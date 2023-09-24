package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-10-24 11:30
 */
public class Number915 {

    // 参考：https://leetcode.cn/problems/partition-array-into-disjoint-intervals/solution/by-ac_oier-yyen/
    public int partitionDisjoint(int[] nums) {

        // 先通过一次遍历（从后往前）统计出所有后缀的最小值 min
        // 其中 min[i] = x 含义为下标范围在 [i, n - 1] 的 nums[i] 的最小值为 x
        // 然后再通过第二次遍历（从前往后）统计每个前缀的最大值（使用单变量进行维护）
        // 找到第一个符合条件的分割点即是答案

        int n = nums.length;
        int[] min = new int[n + 10];
        min[n - 1] = nums[n - 1];

        for (int i = n - 2; i >= 0; i--)
            min[i] = Math.min(min[i + 1], nums[i]);

        for (int i = 0, max = 0; i < n - 1; i++) {
            max = Math.max(max, nums[i]);
            if (max <= min[i + 1])
                return i + 1;
        }

        return -1;
    }
}
