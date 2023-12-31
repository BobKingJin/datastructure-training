package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-08-28 11:37
 */
public class Number793 {

    // 参考：https://leetcode.cn/problems/preimage-size-of-factorial-zeroes-function/solution/by-ac_oier-pk9g/
    public int preimageSizeFZF(int k) {

        // n 的阶乘其最终结果尾部包含 0 的数量取决于其被累乘 10 的次数，而 10 可通过质因数 2 和 5 相乘而来
        // 因此假设对 n! 中的每一数进行阶乘分解，最终分解出 2^p 和 5^q 的话
        // 那么最终结果尾部包含 0 的个数为 q 个（可证明 p <= q 始终满足）
        // 因此原问题转化为：在非负整数中，有多少个数进行阶乘分解后，所含质因数 5 的个数为 k 个
        // 同时可知：随着 n 的增大，其所能分解出来的 5 的个数必然是递增的
        // 基于此，可以通过「二分 + 容斥原理」来得出分解 5 个数恰好为 k 的连续段长度
        // 假设存在函数 f(k) 可得到非负整数中分解 5 个数为小于等于 k 的个数，那么最终 f(k) - f(k - 1) 即答案
        // 在单调函数上求解小于等于 k 分割点，可通过「二分」来做，剩下的问题是，如何求得给定 x 时，其阶乘分解所包含 5 个数
        // 最后还要确定二分的值域，由于是求阶乘分解中 5 的个数，因此值域的上界为 5k，利用 k 的范围为 1e9，直接取成 1e10 即可

        if (k <= 1)
            return 5;

        return f(k) - f(k - 1);
    }

    private int f(int x) {

        long l = 0;
        long r = (long) 1e10;
        while (l < r) {
            long mid = l + r + 1 >> 1;
            if (getCnt(mid) <= x) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        return (int)r;
    }

    private long getCnt(long x) {

        long ans = 0;
        while (x != 0) {
            ans += x / 5;
            x /= 5;
        }

        return ans;
    }
}
