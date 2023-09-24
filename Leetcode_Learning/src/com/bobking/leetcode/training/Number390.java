package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-06-12 11:00
 */
public class Number390 {

    // 参考：https://leetcode.cn/problems/elimination-game/solution/gong-shui-san-xie-yue-se-fu-huan-yun-yon-x60m/
    public int lastRemaining(int n) {

        return n == 1 ? 1 : 2 * (n / 2 + 1 - lastRemaining(n / 2));
    }
}
