package com.bobking.leetcode.training;

/**
 * @Date: 2025/6/14 11:51
 * @Author: BobKing
 * @Description:
 */
public class Number2566 {

    public int minMaxDifference(int num) {
        String s = String.valueOf(num);
        int mx = num;
        for (char c : s.toCharArray()) {
            if (c != '9') {
                mx = Integer.parseInt(s.replace(c, '9'));
                break;
            }
        }
        // 第一个不等于 0 的字符, 替换成 0
        int mn = Integer.parseInt(s.replace(s.charAt(0), '0'));
        return mx - mn;
    }

}
