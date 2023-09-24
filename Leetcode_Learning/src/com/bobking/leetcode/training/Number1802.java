package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-01-05 14:54
 */
public class Number1802 {

    // 参考：https://leetcode.cn/problems/maximum-value-at-a-given-index-in-a-bounded-array/solution/by-lcbin-4vp4/
    public int maxValue(int n, int index, int maxSum) {

        int left = 1;
        int right = maxSum;

        while (left < right) {
            int mid = (left + right + 1) >>> 1;
            if (sum(mid - 1, index) + sum(mid, n - index) <= maxSum) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private long sum(long x, int cnt) {
        return x >= cnt ? (x + x - cnt + 1) * cnt / 2 : (x + 1) * x / 2 + cnt - x;
    }
}
