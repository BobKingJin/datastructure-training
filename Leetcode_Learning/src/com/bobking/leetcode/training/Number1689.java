package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-05-26 7:23
 */
public class Number1689 {

    public int minPartitions(String n) {

        // 输入的数字 n 是由 x 个十-二进制数组成的
        // 找到数字 n 中的最大值就代表最大需要消耗 x 个十-二进制数，其他小于的数都能直接用 1、0 来补全

        char[] cs = n.toCharArray();

        int ans = 0;

        for (int c : cs)
            ans = Math.max(ans, c - '0');

        return ans;
    }
}
