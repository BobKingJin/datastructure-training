package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BobKing
 * @create 2023-11-04 8:03
 */
public class Number401 {

    public List<String> readBinaryWatch(int turnedOn) {

        List<String> res = new ArrayList<String>();

        // 直接遍历  0:00 -> 12:00   每个时间有多少 1
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 60; j++) {
                if (countOfNumber1(i) + countOfNumber1(j) == turnedOn)
                    res.add(i + ":" + (j < 10 ? "0" + j : j));
            }
        }
        return res;
    }

    // 计算二进制中 1 的个数
    private int countOfNumber1(int n) {

        int res = 0;
        while (n != 0) {
            n = n & (n - 1);
            res++;
        }
        return res;
    }
}
