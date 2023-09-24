package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-08-06 10:29
 */
public class Number376 {

    // 参考：https://leetcode.cn/problems/wiggle-subsequence/solution/bai-dong-xu-lie-by-leetcode-solution-yh2m/
    public int wiggleMaxLength1(int[] nums) {

        // 状态转移方程：
        // up[i] = up[i − 1] nums[i] <= nums[i − 1]
        // up[i] = max(up[i − 1], down(i - 1) + 1) nums[i] > nums[i − 1]
        // down[i] = down[i − 1] nums[i] >= nums[i − 1]
        // down[i] = max(down[i − 1], up(i - 1) + 1) nums[i] < nums[i − 1]

        int n = nums.length;
        if (n < 2)
            return n;

        int[] up = new int[n];
        int[] down = new int[n];
        up[0] = 1;
        down[0] = 1;

        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                up[i] = Math.max(up[i - 1], down[i - 1] + 1);
                down[i] = down[i - 1];
            } else if (nums[i] < nums[i - 1]) {
                up[i] = up[i - 1];
                down[i] = Math.max(up[i - 1] + 1, down[i - 1]);
            } else {
                up[i] = up[i - 1];
                down[i] = down[i - 1];
            }
        }

        return Math.max(up[n - 1], down[n - 1]);
    }

    // 参考：https://leetcode.cn/problems/wiggle-subsequence/solution/tan-xin-si-lu-qing-xi-er-zheng-que-de-ti-jie-by-lg/
    public int wiggleMaxLength2(int[] nums) {

        int down = 1;
        int up = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                up = down + 1;
            } else if (nums[i] < nums[i - 1]) {
                down = up + 1;
            }
        }

        return nums.length == 0 ? 0 : Math.max(down, up);
    }
}
