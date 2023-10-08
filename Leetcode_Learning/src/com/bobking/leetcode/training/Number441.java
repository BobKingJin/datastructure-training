package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-10-07 9:03
 */
public class Number441 {

    // 参考: https://leetcode.cn/problems/arranging-coins/solutions/1039049/gong-shui-san-xie-yi-ti-shuang-jie-shu-x-sv9o/
    public int arrangeCoins1(int n) {
        return (int)((Math.sqrt(1 + 8.0 * n) - 1) / 2);
    }

    // 参考: https://leetcode.cn/problems/arranging-coins/solutions/1039049/gong-shui-san-xie-yi-ti-shuang-jie-shu-x-sv9o/
    public int arrangeCoins2(int n) {

        long l = 1;
        long r = n;

        while (l < r) {
            long mid = l + r + 1 >> 1;
            if (mid * (mid + 1) / 2 <= n) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return (int)r;
    }
}
