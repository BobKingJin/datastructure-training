package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @Date: 2026/6/25 1:02
 * @Author: BobKing
 * @Description:
 */
public class LCR164 {

    public String crackPassword(int[] password) {
        String[] strs = new String[password.length];
        for (int i = 0; i < password.length; i++) {
            strs[i] = String.valueOf(password[i]);
        }

        Arrays.sort(strs, (x, y) -> (x + y).compareTo(y + x));
        StringBuilder res = new StringBuilder();
        for (String s : strs) {
            res.append(s);
        }
        return res.toString();
    }

}
