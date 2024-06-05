package com.bobking.leetcode.training;

/**
 * @Date: 2024/6/1 17:12
 * @Author: BobKing
 * @Description:
 */
public class Number2928 {

    public int distributeCandies1(int n, int limit) {
        return c2(n + 2) - 3 * c2(n - limit + 1) + 3 * c2(n - 2 * limit) - c2(n - 3 * limit - 1);
    }

    private int c2(int n) {
        return n > 1 ? n * (n - 1) / 2 : 0;
    }

    public int distributeCandies2(int n, int limit) {
        int ans = 0;
        for (int i = 0; i <= Math.min(limit, n); i++) {
            if (n - i > 2 * limit)
                continue;
            ans += Math.min(n - i, limit) - Math.max(0, n - i - limit) + 1;
        }
        return ans;
    }


}
