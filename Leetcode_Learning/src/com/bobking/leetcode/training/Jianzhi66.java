package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-06-18 12:29
 */
public class Jianzhi66 {

    // 参考：https://leetcode.cn/problems/gou-jian-cheng-ji-shu-zu-lcof/solution/mian-shi-ti-66-gou-jian-cheng-ji-shu-zu-biao-ge-fe/
    public int[] constructArr(int[] a) {

        int len = a.length;
        if(len == 0)
            return new int[0];

        int[] b = new int[len];
        b[0] = 1;
        int tmp = 1;
        for(int i = 1; i < len; i++)
            b[i] = b[i - 1] * a[i - 1];

        for(int i = len - 2; i >= 0; i--) {
            // 当遍历到 i = j 时，tmp = a[j] * a[j + 1] * ... * a[len - 1]
            tmp *= a[i + 1];
            b[i] *= tmp;
        }

        return b;
    }
}
