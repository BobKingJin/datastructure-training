package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-08-05 12:37
 */
public class Number2029 {

    // 参考：https://leetcode.cn/problems/stone-game-ix/solution/gong-shui-san-xie-fen-qing-kuang-tao-lun-h1oa/
    public boolean stoneGameIX(int[] stones) {

        // 不失一般性考虑，某个回合开始前，已移除的石子总和状态为 x（共三种，分别为除以 3 余数为 0、1 和 2
        // 其中当状态为 0，且非首个回合时，说明凑成 3 的倍数，游戏结束），剩余石子价值除以 3 的余数 s 分别为 0、1 和 2
        // 首先如果当前 x = 1 时，不能选择 s = 2 的石子，否则会导致凑成总和为 3 的倍数而失败
        // 同理 x = 2 时，不能选择 s = 1 的石子
        // 而选择 s = 0 的数字，不会改变 x 的状态，可看做换手操作

        int[] cnts = new int[3];

        for (int i : stones)
            cnts[i % 3]++;

        return cnts[0] % 2 == 0 ? !(cnts[1] == 0 || cnts[2] == 0) : !(Math.abs(cnts[1] - cnts[2]) <= 2);
    }
}
