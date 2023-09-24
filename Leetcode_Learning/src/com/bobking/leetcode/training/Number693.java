package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-03-28 14:57
 */
public class Number693 {

    // 参考：https://leetcode-cn.com/problems/binary-number-with-alternating-bits/solution/gong-si-shui-by-ac_oier-zuw7/
    public boolean hasAlternatingBits(int n) {

        int cur = -1;
        while (n != 0) {
            int u = n & 1;
            // 因为 cur 初始化为 -1，而 u 要么为 1 要么为 0，因此一开始 cur ^ 1 != 0
            if ((cur ^ u) == 0)
                return false;
            cur = u;
            n >>= 1;
        }

        return true;
    }
}
