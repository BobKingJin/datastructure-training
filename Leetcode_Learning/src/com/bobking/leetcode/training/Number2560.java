package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-05-20 16:29
 */
public class Number2560 {

    // 参考：https://leetcode.cn/problems/house-robber-iv/solution/er-fen-da-an-dp-by-endlesscheng-m558/
    public int minCapability1(int[] nums, int k) {

        int left = 0;
        int right = (int) 1e9;

        while (left + 1 < right) {

            int mid = (left + right) >>> 1;
            int f0 = 0;
            int f1 = 0;

            for (int x : nums) {
                if (x > mid) {
                    f0 = f1;
                } else {
                    int tmp = f1;
                    f1 = Math.max(f1, f0 + 1);
                    f0 = tmp;
                }
            }

            if (f1 >= k) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }

    public int minCapability2(int[] nums, int k) {

        int n = nums.length;
        if (n == 1)
            return nums[0];

        int left = 1;
        int right = (int) 1e9;
        int ans = right;

        while (left <= right) {

            int mid = (left + right) >> 1;
            int[] dp = new int[n];

            if (nums[0] <= mid)
                dp[0] = 1;

            dp[1] = Math.min(nums[0], nums[1]) <= mid ? 1 : 0;
            for (int i = 2; i < n; i++) {
                if (nums[i] > mid) {
                    // 此房屋的金额 nums[i] 大于窃取能力 mid，无法窃取，只能顺延 dp[i - 1] 保证尽量大
                    dp[i] = dp[i - 1];
                } else {
                    // 此房屋可以窃取, 可选择窃取（dp[i - 2] + 1）和不窃取（dp[i - 1]）的最优值
                    dp[i] = Math.max(dp[i - 1], dp[i - 2] + 1);
                }
            }
            if (dp[n - 1] >= k) {
                // 只要窃取的房屋数量 >= k即可成功，保存答案，继续寻找更小的符合条件的窃取能力
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
}
