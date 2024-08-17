package com.bobking.leetcode.training;

public class Number494 {

    private int res = 0;

    // 参考：https://leetcode-cn.com/problems/target-sum/solution/mu-biao-he-by-leetcode-solution-o0cp/
    public int findTargetSumWays1(int[] nums, int target) {

        if (nums == null || nums.length < 1)
            return 0;

        recursion(nums, 0, 0, target);
        return res;
    }

    private void recursion(int[] nums, int index, int sum, int target) {

        if (index == nums.length) {
            if (sum == target)
                res++;
        } else {
            // 每一个 nums[index] 可正可负
            // 注意每个位置都必须要选
            // 因为不涉及到记录路径，所以这里不需要回溯
            recursion(nums, index + 1, sum + nums[index], target);
            recursion(nums, index + 1, sum - nums[index], target);
        }
    }

    // 参考：https://leetcode-cn.com/problems/target-sum/solution/dong-tai-gui-hua-si-kao-quan-guo-cheng-by-keepal/
    public static int findTargetSumWays2(int[] nums, int target) {

        int sum = 0;
        for (int i = 0; i < nums.length; i++)
            sum += nums[i];

        // 绝对值范围超过了 sum 的绝对值范围则无法得到
        if (Math.abs(target) > Math.abs(sum))
            return 0;

        // - 0 +
        // 即 (-sum) - (+sum)
        int t = sum * 2 + 1;
        // dp[i][j] 定义为从数组 nums 中 0 - i 的元素进行加减可以得到 j 的方法数量
        // dp[i][j] = dp[i - 1][j - nums[i]] + dp[i - 1][j + nums[i]]
        int[][] dp = new int[nums.length][t];
        // 初始化
        // 为什么 nums[0] == 0 的时候是 dp[0][sum] = 2 而不是 dp[0][0] = 1
        // 这里的 sum 其实是 0 ~ 2 * sum + 1 这个范围里的第 sum + 1 个元素，只不过由于数组下标不能为负
        if (nums[0] == 0) {
            dp[0][sum] = 2;
        } else {
            // 因为数组角标不可能为负数，所以两个角标为 sum + nums[0]  sum - nums[0]
            dp[0][sum + nums[0]] = 1;
            dp[0][sum - nums[0]] = 1;
        }
        // 从上往下  从左往右
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < t; j++) {
                // 边界
                int l = (j - nums[i]) >= 0 ? j - nums[i] : 0;
                int r = (j + nums[i]) < t ? j + nums[i] : 0;
                dp[i][j] = dp[i - 1][l] + dp[i - 1][r];
            }
        }
        return dp[nums.length - 1][sum + target];
    }
}
