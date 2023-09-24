package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-06-24 7:49
 */
public class Number2429 {

    // 参考：https://leetcode.cn/problems/minimize-xor/solution/o1-kong-jian-fu-za-du-zuo-fa-by-endlessc-ywio/
    public int minimizeXor(int num1, int num2) {

        int c1 = Integer.bitCount(num1);
        int c2 = Integer.bitCount(num2);

        for (; c2 < c1; ++c2)
            // 最低的 1 变成 0
            num1 &= num1 - 1;
        for (; c2 > c1; --c2)
            // 最低的 0 变成 1
            num1 |= num1 + 1;
        return num1;
    }
}
