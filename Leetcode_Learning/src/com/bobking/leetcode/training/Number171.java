package com.bobking.leetcode.training;

public class Number171 {

    public int titleToNumber(String columnTitle) {

        int res = 0;

        for (int i = 0; i < columnTitle.length(); i++) {
            int num = columnTitle.charAt(i) - 'A' + 1;
            res = res * 26 + num;
        }

        return res;
    }
}
