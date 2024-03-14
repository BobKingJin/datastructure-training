package com.bobking.leetcode.training;

/**
 * @Date: 2024/3/14 0:50
 * @Author: BobKing
 * @Description:
 */
public class Number2864 {

    public String maximumOddBinaryNumber(String s) {
        int cnt1 = (int) s.chars().filter(c -> c == '1').count();
        return "1".repeat(cnt1 - 1) + "0".repeat(s.length() - cnt1) + "1";
    }
}
