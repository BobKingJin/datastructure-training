package com.bobking.leetcode.training;

/**
 * @Date: 2025/3/15 10:26
 * @Author: BobKing
 * @Description:
 */
public class Number3110 {

    public int scoreOfString(String s) {
        char[] ch = s.toCharArray();
        int ans = 0;
        for (int i = 1; i < ch.length; i++) {
            ans += Math.abs(ch[i] - ch[i - 1]);
        }
        return ans;
    }

}
