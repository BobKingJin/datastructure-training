package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-06-27 7:12
 */
public class Number858 {

    // 参考：https://leetcode.cn/problems/mirror-reflection/solution/guang-hui-fan-she-ma-na-wo-men-jia-she-guang-bu-fa/
    public int mirrorReflection(int p, int q) {

        // 只要确保分子分母不都是偶数就行了
        // 同时是偶数的话就回去最开始的点了
        while ((q & 1) == 0 && (p & 1) == 0) {
            q >>= 1;
            p >>= 1;
        }
        // p 为偶数
        if ((p & 1) == 0)
            return 2;
        // q 为偶数
        if ((q & 1) == 0)
            return 0;
        // p, q 都是奇数
        return 1;
    }
}
