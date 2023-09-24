package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-03-08 15:52
 */
public class Number1946 {

    public String maximumNumber(String num, int[] change) {

        StringBuilder res = new StringBuilder();

        int index = 0;
        int N = num.length();
        // 找第一个比 change 小的字母
        while (index < N) {
            char c = num.charAt(index++);
            if (change[c - '0'] > c - '0') {
                res.append(change[c - '0']);
                break;
            }
            res.append(c);
        }

        // 找比 change 大的字母
        while (index < N) {
            char c = num.charAt(index++);
            if (change[c - '0'] < c - '0') {
                res.append(c);
                break;
            }
            res.append(change[c - '0']);
        }

        // 添加剩余元素
        while (index < N) {
            char c = num.charAt(index++);
            res.append(c);
        }

        return res.toString();
    }
}
