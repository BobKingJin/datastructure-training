package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-07-24 10:07
 */
public class Number2310 {

    // 参考：https://leetcode.cn/problems/sum-of-numbers-with-units-digit-k/solution/mei-ju-da-an-by-endlesscheng-zh75/
    public int minimumNumbers(int num, int k) {

        // 把个位数单独拆开看，每个数可以表示成 10 的倍数加上 k 的 res 形式
        // 由于这 n 个数都以 k 结尾，那么 num − n * k 必须是 10 的倍数
        // 从小到大枚举 n，找到第一个满足 num − n * k 是 10 的倍数的 n
        // 由于 n 不会超过 num，至多枚举到 num 时停止

        if (num == 0)
            // num为 0 的情况单独讨论
            return 0;

        // num >= k 的情况：至少需要一个
        for (int i = 1; i <= num; i++) {
            int t = num - i * k;
            if (t >= 0 && t % 10 == 0)
                return i;
        }

        return -1;
    }
}
