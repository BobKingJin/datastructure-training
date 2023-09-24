package com.bobking.leetcode.training;

public class Number300 {

    // 参考：https://leetcode-cn.com/problems/longest-increasing-subsequence/solution/dong-tai-gui-hua-er-fen-cha-zhao-tan-xin-suan-fa-p/
    public int lengthOfLIS1(int[] nums) {

        if (nums == null || nums.length < 1)
            return 0;
        if (nums.length == 1)
            return 1;
        // dp[i] 表示 从 0 - i 最长递增子序列
        int[] dp = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            // 初始化
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < dp.length; i++)
            max = Math.max(max, dp[i]);

        return max;
    }

    // 参考：https://leetcode-cn.com/problems/longest-increasing-subsequence/solution/dong-tai-gui-hua-er-fen-cha-zhao-tan-xin-suan-fa-p/
    // 参考：程序猿代码指南P212
    public int lengthOfLIS2(int[] nums) {

        if (nums == null || nums.length < 1)
            return 0;
        if (nums.length == 1)
            return 1;

        // tail 数组的定义：长度为 i + 1 的上升子序列的末尾最小是几
        // tail[i]表示：长度为 i + 1 的所有上升子序列的结尾的最小值
        int[] tail = new int[nums.length];
        // 遍历第 1 个数，直接放在有序数组 tail 的开头
        tail[0] = nums[0];
        // end 表示有序数组 tail 的最后一个已经赋值元素的索引
        int end = 0;

        for (int i = 1; i < nums.length; i++) {
            // 【逻辑 1】比 tail 数组实际有效的末尾的那个元素还大
            if (nums[i] > tail[end]) {
                // 直接添加在那个元素的后面，所以 end 先加 1
                end++;
                tail[end] = nums[i];
            } else {
                // 使用二分查找法，在有序数组 tail 中 找到第 1 个大于等于 nums[i] 的元素，尝试让那个元素更小
                int left = 0;
                int right = end;
                while (left < right) {
                    // 选左中位数不是偶然，而是有原因的，原因请见 LeetCode 第 35 题题解
                    // int mid = left + (right - left) / 2;
                    int mid = left + ((right - left) >>> 1);
                    if (tail[mid] < nums[i]) {
                        // 中位数肯定不是要找的数，把它写在分支的前面
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
                // 走到这里是因为 【逻辑 1】 的反面，因此一定能找到第 1 个大于等于 nums[i] 的元素
                // 因此，无需再单独判断
                tail[left] = nums[i];
            }
        }
        // 此时 end 是有序数组 tail 最后一个元素的索引
        // 题目要求返回的是长度，因此 +1 后返回
        end++;
        return end;
    }

}
