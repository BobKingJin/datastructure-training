package com.bobking.leetcode.training;

/**
 * @Date: 2026/8/16 16:56
 * @Author: BobKing
 * @Description:
 */
public class Number161 {

    public boolean isOneEditDistance(String s, String t) {

        if (Math.abs(s.length() - t.length()) > 1) {
            return false;
        }

        if (s.length() < t.length()) {
            String tmp = s;
            s = t;
            t = tmp;
        }

        int m = s.length();
        int n = t.length();
        int diff = m - n;

        if (diff == 1) {
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) != t.charAt(i)) {
                    return s.substring(i + 1).equals(t.substring(i));
                }
            }
            return true;
        } else {
            int cnt = 0;
            for (int i = 0; i < m; i++) {
                if (s.charAt(i) != t.charAt(i)) {
                    cnt++;
                    if (cnt > 1) {
                        return false;
                    }
                }
            }
            return cnt == 1;
        }
    }

}
