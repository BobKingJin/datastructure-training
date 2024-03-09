package com.bobking.leetcode.training;

/**
 * @Date: 2024/3/8 22:05
 * @Author: BobKing
 * @Description:
 */
public class Number2834 {

    // 参考: https://leetcode.cn/problems/find-the-minimum-possible-sum-of-a-beautiful-array/solutions/2413304/o1-shu-xue-gong-shi-pythonjavacgo-by-end-xsxg/?envType=daily-question&envId=2024-03-08
    public int minimumPossibleSum(int n, int target) {
        long m = Math.min(target / 2, n);
        return (int) ((m * (m + 1) + (n - m - 1 + target * 2) * (n - m)) / 2 % 1_000_000_007);
    }
}
