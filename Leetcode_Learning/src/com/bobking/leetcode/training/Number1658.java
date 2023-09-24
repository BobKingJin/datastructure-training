package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2023-01-07 10:13
 */
public class Number1658 {

    // 参考：https://leetcode.cn/problems/minimum-operations-to-reduce-x-to-zero/solution/shi-yong-hua-dong-chuang-kou-zhao-zhong-jian-zui-c/
    public int minOperations(int[] nums, int x) {

        // 把问题转换成 nums 中移除一个最长的子数组，使得剩余元素的和为 x
        // 换句话说，要从 nums 中找最长的子数组，其元素和等于 s - x，这里 s 为 nums 所有元素之和
        // 使用滑动窗口找中间最长的片段使得 sum(片段) = sum(nums) - x
        int maxPart = -1;
        int sum = Arrays.stream(nums).sum();
        int currentSum = 0;
        int left = 0;
        int right = 0;

        while (left < nums.length) {
            // 如果右边未到尽头，不断先向右探测片段，如果大于目标 sum - x则左边移动直到结束
            if (right < nums.length)
                currentSum += nums[right++];

            while (currentSum > sum - x && left < nums.length)
                currentSum -= nums[left++];

            if (currentSum == sum - x)
                maxPart = Math.max(maxPart, right - left);

            if (right == nums.length)
                left++;
        }
        return maxPart == -1 ? -1 : nums.length - maxPart;
    }
}
