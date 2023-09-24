package com.bobking.leetcode.training;

public class Number918 {

    // 参考：https://leetcode.cn/problems/maximum-sum-circular-subarray/solution/java-dp-kan-bu-dong-wei-shi-yao-sum-min-x7q53/
    public int maxSubarraySumCircular(int[] nums) {

        // 环形子数组的最大和具有两种可能，一种是不使用环的情况，另一种是使用环的情况
        // 不使用环的情况时，直接通过 Number53 的思路，逐步求出整个数组中的最大子序和即可
        // 【重点】使用到了环，则必定包含 A[n - 1] 和 A[0] 两个元素且说明从 A[1] 到 A[n - 2] 这个子数组中必定包含负数
        // 【否则只通过一趟最大子序和就可以的 = 得出结果】
        // 因此只需要把 A[1] - A[n - 2] 间这些负数的最小和求出来
        // 用整个数组的和 sum 减掉这个负数最小和即可实现原环型数组的最大和
        // 最后再比较直接通过 Number53 思路求出无环子序列和用 sum - min 的有环子序列比较大小求出整个数组的最大值即可

        // dp[i] 用来记录以 nums[i] 结尾的最大子序列和
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
        int sum = dp[0];

        for (int i = 1; i < dp.length; i++) {
            sum += nums[i];
            dp[i] = nums[i] + Math.max(dp[i - 1], 0);
            max = Math.max(dp[i], max);
        }

        // 开始求 A[1] ~ A[n - 1] 上的最小子序列和
        int min = 0;
        for (int i = 1; i < dp.length - 1; i++) {
            // 复用了 dp[] 数组
            dp[i] = nums[i] + Math.min(0, dp[i - 1]);
            min = Math.min(dp[i], min);
        }

        // 在第一次遍历过程中 sum 表示整个数据的累加和，那么此时 sum 已经包含了最小和部分，如果最大值存在于环中
        // 那么应该把这部分负值去掉，即 sum - min = sum + abs(最小负值)
        // 例如：9 8 6 7 -5 -6 4  那么累加最小负值为 -5 + -6 = -11，整个数组最大累加值应该 4 9 8 6 7
        return Math.max(sum - min, max);
    }
}
