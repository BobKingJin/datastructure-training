package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @Date: 2023/12/27 8:09
 * @Author: BobKing
 * @Description:
 */
public class Number2578 {

    // 参考: https://leetcode.cn/problems/split-with-minimum-sum/solutions/2147726/tan-xin-by-endlesscheng-x2o6/?envType=daily-question&envId=2023-12-27
    public int splitNum(int num) {

        char[] s = Integer.toString(num).toCharArray();
        Arrays.sort(s);

        int[] a = new int[2];

        for (int i = 0; i < s.length; i++)
            // 按照奇偶下标分组
            a[i % 2] = a[i % 2] * 10 + s[i] - '0';

        return a[0] + a[1];
    }
}
