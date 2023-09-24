package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-05-28 0:11
 */
public class Number67 {

    public String addBinary(String a, String b) {

        StringBuilder res = new StringBuilder();
        int ca = 0;
        for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i--, j--) {
            int sum = ca;
            sum += i >= 0 ? a.charAt(i) - '0' : 0;
            sum += j >= 0 ? b.charAt(j) - '0' : 0;
            res.append(sum % 2);
            ca = sum / 2;
        }
        res.append(ca == 1 ? ca : "");
        return res.reverse().toString();
    }
}
