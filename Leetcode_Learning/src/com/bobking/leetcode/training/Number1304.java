package com.bobking.leetcode.training;

/**
 * @Date: 2025/9/7 11:55
 * @Author: BobKing
 * @Description:
 */
public class Number1304 {

    public int[] sumZero(int n) {
        int[] ans = new int[n];
        int m = n / 2;
        for (int i = 0; i < m; i++) {
            ans[i] = i + 1;
            ans[i + m] = -i - 1;
        }
        return ans;
    }

}
