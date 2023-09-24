package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-10-30 12:16
 */
public class Number1446 {

    public int maxPower(String s) {

        int n = s.length();
        int ans = 1;

        for (int i = 0; i < n; ) {
            int j = i;
            while (j < n && s.charAt(j) == s.charAt(i))
                j++;
            ans = Math.max(ans, j - i);
            i = j;
        }
        return ans;
    }
}
