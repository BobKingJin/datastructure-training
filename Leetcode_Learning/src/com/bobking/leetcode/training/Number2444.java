package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-02-08 10:53
 */
public class Number2444 {

    // 参考：https://leetcode.cn/problems/count-subarrays-with-fixed-bounds/solution/jian-ji-xie-fa-pythonjavacgo-by-endlessc-gag2/
    public long countSubarrays(int[] nums, int minK, int maxK) {

        long ans = 0L;
        int n = nums.length;
        int minI = -1;
        int maxI = -1;
        // 记录上一个在 [minK, maxK]范围之外的 nums[i] 的下标
        int i0 = -1;

        for (int i = 0; i < n; ++i) {

            int x = nums[i];
            if (x == minK)
                minI = i;
            if (x == maxK)
                maxI = i;
            if (x < minK || x > maxK)
                // 子数组不能包含 nums[i0]
                i0 = i;
            ans += Math.max(Math.min(minI, maxI) - i0, 0);
        }
        return ans;
    }
}
