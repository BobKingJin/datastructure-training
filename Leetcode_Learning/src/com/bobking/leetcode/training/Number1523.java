package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-01-08 10:28
 */
public class Number1523 {

    // 参考：https://leetcode.cn/problems/count-odd-numbers-in-an-interval-range/solution/zai-qu-jian-fan-wei-nei-tong-ji-qi-shu-shu-mu-by-l/
    public int countOdds(int low, int high) {
        return pre(high) - pre(low - 1);
    }

    private int pre(int x) {
        return (x + 1) >> 1;
    }
}
