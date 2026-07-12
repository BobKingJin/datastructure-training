package com.bobking.leetcode.training;

/**
 * @Date: 2026/6/25 9:32
 * @Author: BobKing
 * @Description:
 */
public class Jianzhi44 {

    public int findNthDigit(int n) {
        // 记录 n 是几位数
        int digit = 1;
        // 记录当前位数区间的起始数字: 1, 10, 100...
        long start = 1;
        // 记录当前区间之前总共有多少位数字
        long sum = 9;
        // 将 n 定位在某个位数的区间中
        while (n > sum) {
            n -= sum;
            start *= 10;
            digit++;
            // 该区间的总共位数
            sum = 9 * start * digit;
        }
        // 定位 n 在哪个数字上
        String num = "" + (start + (n - 1) / digit);
        // 定位 n 在数字的哪一位上
        int index = (n - 1) % digit;
        return (int) (num.charAt(index)) - (int) ('0');
    }

}
