package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-09-11 9:45
 */
public class Number526 {

    // 参考：https://leetcode.cn/problems/beautiful-arrangement/solution/gong-shui-san-xie-xiang-jie-liang-chong-vgsia/
    public int countArrangement(int n) {

        int mask = 1 << n;

        // 变量 state 存放了「当前数的使用情况」
        // 当需要检查值为 k 的数是否被使用时，可以使用位运算 a = (state >> k) & 1，来获取 state 中第 k 位的二进制表示
        // 如果 a 为 1 代表值为 k 的数字已被使用，如果为 0 则未被访问
        // 定义 f[i][state] 为考虑前 i 个数，且当前选择方案为 state 的所有方案数量
        // 一个显然的初始化条件为 f[0][0] = 1，代表不考虑任何数（i = 0）的情况下，一个数都不被选择（state = 0）为一种合法方案
        // 不失一般性的考虑 f[i][state] 该如何转移，由于本题是求方案数，转移方程必须做到「不重不漏」
        // 可以通过枚举当前位置 i 是选哪个数，假设位置 i 所选数值为 k，首先 k 值需要同时满足如下两个条件：
        // state 中的第 k 位为 1
        // 要么 k 能被 i 整除，要么 i 能被 k 整除
        // 那么根据状态定义，位置 i 选了数值 k，通过位运算可以直接得出决策位置 i 之前的状态是什么：state & ~(1 << (k − 1))
        // 代表将 state 的二进制表示中的第 k 位置 0
        // 最终的 f[i][state] 为当前位置 i 选择的是所有合法的 k 值的方案数之和：f[i][state] = sum(f[i - 1][state & ~(1 << (k − 1)])) 1 <= k <= n
        // 由于给定的数值范围为 [1, n]，但实现上为了方便，使用 state 从右往左的第 0 位表示数值 1 选择情况，第 1 位表示数值 2 的选择情况 ...
        // 即对选择数值 k 做一个 -1 的偏移

        int[][] f = new int[n + 1][mask];
        f[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            // 枚举所有的状态
            for (int state = 0; state < mask; state++) {
                // 枚举位置 i（最后一位）选的数值是 k
                for (int k = 1; k <= n; k++) {
                    // 首先 k 在 state 中必须是 1
                    if (((state >> (k - 1)) & 1) == 0)
                        continue;
                    // 数值 k 和位置 i 之间满足任一整除关系
                    if (k % i != 0 && i % k != 0)
                        continue;
                    // state & (~(1 << (k - 1))) 代表将 state 中数值 k 的位置置零
                    f[i][state] += f[i - 1][state & (~(1 << (k - 1)))];
                }
            }
        }

        return f[n][mask - 1];
    }
}
