package com.bobking.leetcode.training;

/**
 * @Date: 2024/3/17 9:29
 * @Author: BobKing
 * @Description:
 */
public class Number1881 {

    public String maxValue(String n, int x) {

        int y = '0' + x;
        int i = 0;

        if (n.charAt(0) != '-') {
            for (; i < n.length() && n.charAt(i) >= y; i++) {}
        } else {
            for (; i < n.length() && n.charAt(i) <= y; i++) {}
        }
        return n.substring(0, i) + x + n.substring(i, n.length());
    }
}
