package com.bobking.leetcode.training;

/**
 * @Date: 2025/6/2 11:06
 * @Author: BobKing
 * @Description:
 */
public class Number2614 {

    public int diagonalPrime(int[][] nums) {
        int n = nums.length;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            int x = nums[i][i];
            if (x > ans && isPrime(x)) {
                ans = x;
            }
            x = nums[i][n - 1 - i];
            if (x > ans && isPrime(x)) {
                ans = x;
            }
        }
        return ans;
    }

    private boolean isPrime(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        // 1 不是质数
        return n >= 2;
    }

}
