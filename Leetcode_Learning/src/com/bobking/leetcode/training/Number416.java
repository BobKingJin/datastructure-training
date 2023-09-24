package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2021-03-25 23:10
 */
public class Number416 {

    // 参考：https://leetcode-cn.com/problems/partition-equal-subset-sum/solution/fen-ge-deng-he-zi-ji-by-leetcode-solution/
    public boolean canPartition1(int[] nums) {

        if (nums == null || nums.length < 2)
            return false;

        int sum = 0;
        for (int num : nums)
            sum += num;
        // 累计和为奇数直接返回 false
        if ((sum & 1) == 1)
            return false;

        int target = sum / 2;

        // dp[i][j] 表示从数组的 [0, i]下标范围内选取若干个正整数（可以是 0 个）
        // 是否存在一种选取方案使得被选取的正整数的和等于 j
        boolean[][] dp = new boolean[nums.length][target + 1];

        if (nums[0] <= target)
            dp[0][nums[0]] = true;

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j <= target; j++) {

                // 如果 j < nums[i]，则在选取的数字的和等于 j 的情况下无法选取当前的数字 nums[i]
                // 因此有 dp[i][j] = dp[i − 1][j]
                dp[i][j] = dp[i - 1][j];

                if (nums[i] == j) {
                    dp[i][j] = true;
                    continue;
                }
                // 如果 j >= nums[i]，则对于当前的数字 nums[i]，可以选取也可以不选取
                // 两种情况只要有一个为 true，就有 dp[i][j] = true
                // 如果不选取 nums[i]，则 dp[i][j] = dp[i − 1][j]
                // 如果选取 nums[i]，则 dp[i][j] = dp[i − 1][j − nums[i]]
                if (j > nums[i])
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
            }
        }

        return dp[nums.length - 1][target];
    }

    // 参考：https://leetcode-cn.com/problems/partition-equal-subset-sum/solution/0-1-bei-bao-wen-ti-xiang-jie-zhen-dui-ben-ti-de-yo/
    public boolean canPartition2(int[] nums) {

        if (nums == null || nums.length < 2)
            return false;

        int sum = 0;
        for (int num : nums)
            sum += num;
        if ((sum & 1) == 1)
            return false;

        int target = sum / 2;
        boolean[][] dp = new boolean[nums.length][target + 1];

        // 初始化成为 true 虽然不符合状态定义，但是从状态转移来说是完全可以的
        dp[0][0] = true;

        if (nums[0] <= target)
            dp[0][nums[0]] = true;

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j <= target; j++) {
                dp[i][j] = dp[i - 1][j];
                if (nums[i] <= j)
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
            }
            // 由于状态转移方程的特殊性，提前结束，可以认为是剪枝操作
            if (dp[i][target])
                return true;
        }
        return dp[nums.length - 1][target];
    }

    // 参考：https://leetcode-cn.com/problems/partition-equal-subset-sum/solution/fen-ge-deng-he-zi-ji-by-leetcode-solution/
    public boolean canPartition3(int[] nums) {

        if (nums == null || nums.length < 2)
            return false;

        int sum = 0;
        for (int num : nums)
            sum += num;
        // 累计和为奇数直接返回false
        if ((sum & 1) == 1)
            return false;

        int target = sum / 2;
        // 每一行的 dp 值都只与上一行的 dp 值有关，因此只需要一个一维数组即可将空间复杂度降到O(target)
        boolean[] dp = new boolean[target + 1];

        dp[0] = true;

        if (nums[0] <= target)
            dp[nums[0]] = true;

        for (int i = 1; i < nums.length; i++) {
            for (int j = target; nums[i] <= j; j--) {

                if (dp[target])
                    return true;

                dp[j] = dp[j - nums[i]] || dp[j];
            }
        }

        return dp[target];
    }
}
