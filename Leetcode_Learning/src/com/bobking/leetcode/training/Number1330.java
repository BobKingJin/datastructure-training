package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-05-14 7:41
 */
public class Number1330 {

    // 参考：https://leetcode.cn/problems/reverse-subarray-to-maximize-array-value/solution/bu-hui-hua-jian-qing-kan-zhe-pythonjavac-c2s6/
    public int maxValueAfterReverse(int[] nums) {

        int base = 0;
        int d = 0;
        int n = nums.length;

        int mx = Integer.MIN_VALUE;
        int mn = Integer.MAX_VALUE;

        for (int i = 1; i < n; i++) {
            int a = nums[i - 1];
            int b = nums[i];
            int dab = Math.abs(a - b);
            base += dab;
            mx = Math.max(mx, Math.min(a, b));
            mn = Math.min(mn, Math.max(a, b));
            d = Math.max(d, Math.max(Math.abs(nums[0] - b) - dab, // i = 0
                    Math.abs(nums[n - 1] - a) - dab)); // j = n - 1
        }
        return base + Math.max(d, 2 * (mx - mn));
    }
}
