package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-05-05 6:15
 */
public class Number1545 {

    // 参考：https://leetcode.cn/problems/find-kth-bit-in-nth-binary-string/solution/zhao-chu-di-n-ge-er-jin-zhi-zi-fu-chuan-zhong-de-2/
    public char findKthBit(int n, int k) {

        if (k == 1)
            return '0';

        int mid = 1 << (n - 1);

        if (k == mid) {
            return '1';
        } else if (k < mid) {
            return findKthBit(n - 1, k);
        } else {
            k = mid * 2 - k;
            return invert(findKthBit(n - 1, k));
        }
    }

    private char invert(char bit) {
        return (char) ('0' + '1' - bit);
    }
}
