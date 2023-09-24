package com.bobking.leetcode.training;

public class Number674 {

    // 参考：https://leetcode.cn/problems/longest-continuous-increasing-subsequence/solution/zui-chang-lian-xu-di-zeng-xu-lie-by-leet-dmb8/
    public int findLengthOfLCIS(int[] nums) {

        int ans = 0;
        int n = nums.length;

        // 假设数组 nums 的长度是 n，对于 0 < l ≤ r < n − 1，如果下标范围 [l, r] 的连续子序列是递增序列
        // 则考虑 nums[l − 1] 和 nums[r + 1]
        // 如果 nums[l − 1] < nums[l]，则将 nums[l − 1] 加到 nums[l] 的前面，可以得到更长的连续递增序列
        // 如果 nums[r + 1] > nums[r]，则将 nums[r + 1] 加到 nums[r] 的后面，可以得到更长的连续递增序列

        // 使用记录当前连续递增序列的开始下标和结束下标，遍历数组的过程中每次比较相邻元素
        // 根据相邻元素的大小关系决定是否需要更新连续递增序列的开始下标
        // 令 start 表示连续递增序列的开始下标，初始时 start = 0，然后遍历数组 nums，进行如下操作
        // 如果下标 i > 0 且 nums[i] ≤ nums[i − 1]，则说明当前元素小于或等于上一个元素，因此 nums[i − 1] 和 nums[i] 不可能属于同一个连续递增序列，必须从下标 i 处开始一个新的连续递增序列，因此令 start = i
        // 如果下标 i = 0 或 nums[i] > nums[i − 1]，则不更新 start 的值
        // 此时下标范围 [start, i] 的连续子序列是递增序列，其长度为 i − start + 1，使用当前连续递增序列的长度更新最长连续递增序列的长度
        int start = 0;

        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] <= nums[i - 1])
                start = i;
            ans = Math.max(ans, i - start + 1);
        }

        return ans;
    }
}
