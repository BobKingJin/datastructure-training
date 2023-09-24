package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-06-26 8:24
 */
public class Number2485 {

    public int pivotInteger1(int n) {
        int m = n * (n + 1) / 2;
        int x = (int) Math.sqrt(m);
        return x * x == m ? x : -1;
    }

    public int pivotInteger2(int n) {

        int sum = (1 + n) * n / 2;

        for (int i = 1, pre = 0; i <= n; i++) {
            pre += i;
            if (pre == sum - pre + i)
                return i;
        }
        return -1;
    }

    public int pivotInteger3(int n) {

        for (int l = 1, r = n, tmp = (n + 1) * n / 2; l <= r; ) {

            int mid = l + ((r - l) >> 1);
            int diff = mid * mid - tmp;

            if (diff == 0) {
                return mid;
            } else if (diff < 0) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }
}
