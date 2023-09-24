package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2022-08-07 10:06
 */
public class Number698 {

    // 参考：https://leetcode.cn/problems/partition-to-k-equal-sum-subsets/solution/javahui-su-jian-zhi-shou-ba-shou-jiao-hu-0equ/
    public boolean canPartitionKSubsets1(int[] nums, int k) {

        int sum = 0;
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++)
            sum += nums[i];

        if (sum % k != 0)
            return false;

        int target = sum / k;
        if (nums[nums.length - 1] > target)
            return false;

        return dfs(nums, nums.length - 1, target, 0, k, used);
    }

    private boolean dfs(int[] nums, int begin, int target, int curSum, int k, boolean[] used) {

        if (k == 1)
            return true;

        if (curSum == target)
            return dfs(nums, nums.length - 1, target, 0, k - 1, used);

        for (int i = begin; i >= 0; i--) {

            if (used[i])
                continue;

            if (curSum + nums[i] > target)
                continue;

            used[i] = true;
            if (dfs(nums, i - 1, target, curSum + nums[i], k, used))
                return true;
            // 回溯
            used[i] = false;
            while (i > 0 && nums[i - 1] == nums[i])
                i--;
        }
        return false;
    }

    // 参考：https://leetcode.cn/problems/partition-to-k-equal-sum-subsets/solution/hua-fen-wei-kge-xiang-deng-de-zi-ji-by-leetcode/
    public boolean canPartitionKSubsets2(int[] nums, int k) {

        if (k == 1)
            return true;

        int len = nums.length;
        Arrays.sort(nums);
        int sum = 0;
        for (int num : nums)
            sum += num;

        if (sum % k != 0)
            return false;

        int target = sum / k;
        if (nums[len - 1] > target)
            return false;

        int size = 1 << len;
        boolean[] dp = new boolean[size];
        dp[0] = true;
        int[] currentSum = new int[size];
        for (int i = 0; i < size; i++) {
            // 总是基于 dp[i] = true 的前提下进行状态转移
            if (!dp[i])
                continue;

            // 基于当前状态，添加一个数以后
            for (int j = 0; j < len; j++) {
                if ((i & (1 << j)) != 0)
                    continue;
                int next = i | (1 << j);
                if (dp[next])
                    continue;
                if ((currentSum[i] % target) + nums[j] <= target) {
                    currentSum[next] = currentSum[i] + nums[j];
                    dp[next] = true;
                } else {
                    // 由于数组已经排好序，如果 (currentSum[i] % target) + nums[j] > target，剩下的数就没有必要枚举
                    break;
                }
            }
        }
        return dp[size - 1];
    }
}
