package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-05-27 22:42
 */
public class Number292 {

    // 参考：https://leetcode.cn/problems/nim-game/solution/gong-shui-san-xie-noxiang-xin-ke-xue-xi-wmz2t/
    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }
}
