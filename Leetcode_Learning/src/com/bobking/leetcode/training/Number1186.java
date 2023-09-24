package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-05-02 8:58
 */
public class Number1186 {

    // 参考：https://leetcode.cn/problems/maximum-subarray-sum-with-one-deletion/solution/bi-jiao-tong-su-yi-dong-de-dp-by-xiaoxinganlingani/
    public int maximumSum(int[] arr) {

        int len = arr.length;
        // 定义 f(i) 和 g(i)
        // f(i) 表示不删除元素的情况下最大子数组和（以 arr[i] 结尾）
        // g(i) 表示删除元素的情况下的最大子数组和（以 arr[i] 结尾）
        int[] f = new int[len];
        int[] g = new int[len];
        int res = arr[0];
        f[0] = arr[0];
        g[0] = -200001;

        for (int i = 1; i < len; i++) {
            // 其实就是 f(i - 1) 是否 < 0
            f[i] = Math.max(f[i - 1] + arr[i], arr[i]);
            g[i] = Math.max(g[i - 1] + arr[i], f[i - 1]);
            res = Math.max(res, Math.max(f[i], g[i]));
        }

        return res;
    }
}
