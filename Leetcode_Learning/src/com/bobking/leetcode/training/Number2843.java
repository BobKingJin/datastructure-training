package com.bobking.leetcode.training;

/**
 * @Date: 2025/5/25 11:37
 * @Author: BobKing
 * @Description:
 */
public class Number2843 {

    public int countSymmetricIntegers1(int low, int high) {
        int ans = 0;
        for (int i = low; i <= high; i++) {
            char[] s = Integer.toString(i).toCharArray();
            int n = s.length;
            if (n % 2 > 0) {
                continue;
            }
            int diff = 0;
            for (int j = 0; j < n / 2; j++) {
                diff += s[j];
            }
            for (int j = n / 2; j < n; j++) {
                diff -= s[j];
            }
            if (diff == 0) {
                ans++;
            }
        }
        return ans;
    }

}
