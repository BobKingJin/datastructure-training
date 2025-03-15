package com.bobking.leetcode.training;

/**
 * @Date: 2025/3/15 10:53
 * @Author: BobKing
 * @Description:
 */
public class Number2264 {

    public String largestGoodInteger(String num) {
        char mx = 0;
        System.out.println(mx);
        for (int i = 0; i < num.length() - 2; i++) {
            char d = num.charAt(i);
            System.out.println(d);
            // 最大的优质整数
            if (d > mx && d == num.charAt(i + 1) && d == num.charAt(i + 2)) {
                mx = d;
            }
        }
        return mx > 0 ? String.valueOf(mx).repeat(3) : "";
    }

}
