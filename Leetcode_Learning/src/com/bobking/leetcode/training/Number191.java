package com.bobking.leetcode.training;

public class Number191 {

    // 参考：程序猿代码指南P355
    public int hammingWeight1(int n) {

        int res = 0;

        while (n != 0) {
            res += n & 1;
            n >>>= 1;
        }

        return res;
    }

    // 参考：程序猿代码指南P356
    public int hammingWeight2(int n) {

        int res = 0;

        while (n != 0) {
            n &= (n - 1);
            res++;
        }

        return res;
    }
}
