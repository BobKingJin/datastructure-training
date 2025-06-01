package com.bobking.leetcode.training;

/**
 * @Date: 2025/6/1 11:55
 * @Author: BobKing
 * @Description:
 */
public class Number3171 {

    // 参考: https://leetcode.cn/problems/find-subarray-with-bitwise-or-closest-to-k/solutions/2798206/li-yong-and-de-xing-zhi-pythonjavacgo-by-gg4d/
    public int minimumDifference1(int[] nums, int k) {
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            // 单个元素也算子数组
            ans = Math.min(ans, Math.abs(x - k));
            for (int j = i - 1; j >= 0; j--) {
                // 现在 nums[j] = 原数组 nums[j] 到 nums[i] 的 OR
                nums[j] |= x;
                ans = Math.min(ans, Math.abs(nums[j] - k));
            }
        }
        return ans;
    }

    // 参考: https://leetcode.cn/problems/find-subarray-with-bitwise-or-closest-to-k/solutions/2798206/li-yong-and-de-xing-zhi-pythonjavacgo-by-gg4d/
    public int minimumDifference2(int[] nums, int k) {
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            ans = Math.min(ans, Math.abs(x - k));
            // 如果 x 是 nums[j] 的子集，就退出循环
            for (int j = i - 1; j >= 0 && (nums[j] | x) != nums[j]; j--) {
                nums[j] |= x;
                ans = Math.min(ans, Math.abs(nums[j] - k));
            }
        }
        return ans;
    }

}
