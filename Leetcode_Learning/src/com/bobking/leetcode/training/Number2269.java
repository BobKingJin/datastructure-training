package com.bobking.leetcode.training;

/**
 * @Date: 2025/3/16 11:57
 * @Author: BobKing
 * @Description:
 */
public class Number2269 {

    public int divisorSubstrings(int num, int k) {
        String s = String.valueOf(num);
        int ans = 0;
        for (int i = k; i <= s.length(); i++) {
            int x = Integer.parseInt(s.substring(i - k, i));
            if (x > 0 && num % x == 0) {
                ans++;
            }
        }
        return ans;
    }

}
