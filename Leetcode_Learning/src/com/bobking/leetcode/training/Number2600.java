package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-07-05 7:59
 */
public class Number2600 {

    // 参考：https://leetcode.cn/problems/k-items-with-the-maximum-sum/solution/tan-xin-jian-ji-xie-fa-by-endlesscheng-zjea/
    public int kItemsWithMaximumSum(int numOnes, int numZeros, int numNegOnes, int k) {

        if (k <= numOnes + numZeros)
            return Math.min(k, numOnes);
        return numOnes * 2 + numZeros - k;
    }
}
