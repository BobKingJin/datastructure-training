package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-06-23 8:28
 */
public class Number2496 {

    public int maximumValue1(String[] strs) {

        int ans = 0;
        int x;
        for (String s : strs) {
            try {
                x = Integer.parseInt(s);
            } catch (Exception e) {
                x = s.length();
            }
            ans = Math.max(ans, x);
        }
        return ans;
    }

    public int maximumValue2(String[] strs) {

        int ans = 0;

        for (String str : strs) {

            boolean flag = false;

            for (char c : str.toCharArray()) {
                if (!(c >= '0' && c <= '9')) {
                    flag = true;
                    break;
                }
            }

            if (flag) {
                ans = Math.max(ans, str.length());
            } else {
                ans = Math.max(ans, Integer.parseInt(str));
            }
        }

        return ans;
    }
}
