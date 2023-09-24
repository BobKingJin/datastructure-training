package com.bobking.leetcode.training;

public class Number377 {

    // 参考：https://leetcode-cn.com/problems/combination-sum-iv/solution/gong-shui-san-xie-yu-wan-quan-bei-bao-we-x0kn/
    public int combinationSum4_1(int[] nums, int target) {

        // 因为 nums[i] 最小值为 1，因此构成答案的最大长度为 target
        int len = target;
        // f[i][j] 为组合长度为 i，凑成总和为 j 的方案数是多少
        int[][] f = new int[len + 1][target + 1];
        f[0][0] = 1;
        int res = 0;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j <= target; j++) {
                for (int u : nums) {
                    if (j >= u)
                        f[i][j] += f[i - 1][j - u];
                }
            }
            res += f[i][target];
        }

        return res;
    }

    // 参考：https://leetcode-cn.com/problems/combination-sum-iv/solution/xi-wang-yong-yi-chong-gui-lu-gao-ding-bei-bao-wen-/
    public int combinationSum4_2(int[] nums, int target) {

        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i >= nums[j])
                    dp[i] += dp[i - nums[j]];
            }
        }
        return dp[target];
    }
}
