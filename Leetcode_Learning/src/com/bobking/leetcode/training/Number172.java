package com.bobking.leetcode.training;

public class Number172 {

    // 参考：https://leetcode-cn.com/problems/factorial-trailing-zeroes/solution/xiang-xi-tong-su-de-si-lu-fen-xi-by-windliang-3/
    public int trailingZeroes(int n) {

        int count = 0;

        for (int i = 1; i <= n; i++) {
            int N = i;
            while (N > 0) {
                if (N % 5 == 0) {
                    count++;
                    N /= 5;
                } else {
                    break;
                }
            }
        }
        return count;
    }
}
