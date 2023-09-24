package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2023-06-23 22:05
 */
public class Number410 {

    // 参考：https://leetcode.cn/problems/split-array-largest-sum/solution/er-fen-cha-zhao-by-liweiwei1419-4/
    public int splitArray1(int[] nums, int k) {

        int len = nums.length;
        int[] preSum = new int[len + 1];
        preSum[0] = 0;
        for (int i = 0; i < len; i++)
            preSum[i + 1] = preSum[i] + nums[i];

        // 区间 [i..j] 的和 preSum(j + 1) - preSum(i)

        int[][] dp = new int[len][k + 1];
        for (int i = 0; i < len; i++)
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        for (int i = 0; i < len; i++)
            dp[i][1] = preSum[i + 1];
        // dp[n][k] 表示：将前缀区间 [0, n] 被分成 k 段的各自和的最大值的最小值记为 dp[n][k]
        // 从分割数为 2 开始递推
        for (int n = 2; n <= k; n++) {
            // 还未计算出的 i 是从 j 的最小值的下一位开始，因此是 k - 1
            for (int i = n - 1; i < len; i++) {
                // j 表示第 k - 1 个区间的最后一个元素额下标，最小值为 k - 2，最大值为 len - 2（最后一个区间至少有 1 个元素）
                for (int j = n - 2; j < i; j++)
                    dp[i][n] = Math.min(dp[i][n], Math.max(dp[j][n - 1], preSum[i + 1] - preSum[j + 1]));
            }
        }
        return dp[len - 1][k];
    }

    // 参考：https://leetcode.cn/problems/split-array-largest-sum/solution/er-fen-cha-zhao-by-liweiwei1419-4/
    public int splitArray2(int[] nums, int k) {

        int max = 0;
        int sum = 0;

        // 计算「子数组各自的和的最大值」的上下界
        for (int num : nums) {
            max = Math.max(max, num);
            sum += num;
        }

        // 使用「二分查找」确定一个恰当的「子数组各自的和的最大值」，
        // 使得它对应的「子数组的分割数」恰好等于 m
        int left = max;
        int right = sum;
        while (left < right) {

            int mid = left + (right - left) / 2;

            int splits = split(nums, mid);
            if (splits > k) {
                // 如果分割数太多，说明「子数组各自的和的最大值」太小，此时需要将「子数组各自的和的最大值」调大
                // 下一轮搜索的区间是 [mid + 1, right]
                left = mid + 1;
            } else {
                // 下一轮搜索的区间是上一轮的反面区间 [left, mid]
                right = mid;
            }
        }
        return left;
    }

    private int split(int[] nums, int maxIntervalSum) {
        // 至少是一个分割
        int splits = 1;
        // 当前区间的和
        int curIntervalSum = 0;
        for (int num : nums) {
            // 尝试加上当前遍历的这个数，如果加上去超过了「子数组各自的和的最大值」，就不加这个数
            if (curIntervalSum + num > maxIntervalSum) {
                curIntervalSum = 0;
                splits++;
            }
            curIntervalSum += num;
        }
        return splits;
    }

}
