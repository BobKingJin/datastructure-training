package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-07-09 10:38
 */
public class Number1422 {

    public int maxScore(String s) {

        int res = 0;
        // cnt1 统计右边 1 的个数，同理 cnt0 左边 0 的个数
        int cnt1 = 0;
        int cnt0 = 0;

        for (int i = 0; i < s.length(); i++)
            // 先统计 1 的个数
            cnt1 += s.charAt(i) - '0';

        // 由于左右区域的数至少为 1，所以 i 不能等于 len - 1
        // 点 i 分为左右两个区域
        for (int i = 0; i < s.length() - 1; i++) {
            //遇到 01 就统计，动态更新左右区域 01 个数
            if (s.charAt(i) == '0') {
                cnt0++;
            } else {
                cnt1--;
            }
            res = Math.max(res, cnt0 + cnt1);
        }

        return res;
    }
}
