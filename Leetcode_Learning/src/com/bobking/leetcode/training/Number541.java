package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-10-25 8:02
 */
public class Number541 {

    public String reverseStr(String s, int k) {

        char[] cs = s.toCharArray();
        int n = s.length();

        for (int l = 0; l < n; l = l + 2 * k) {
            int r = l + k - 1;
            reverse(cs, l, Math.min(r, n - 1));
        }
        return String.valueOf(cs);

    }

    private void reverse(char[] cs, int l, int r) {
        while (l < r) {
            char c = cs[l];
            cs[l] = cs[r];
            cs[r] = c;
            l++;
            r--;
        }
    }
}
