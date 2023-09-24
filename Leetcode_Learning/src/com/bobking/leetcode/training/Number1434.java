package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BobKing
 * @create 2022-10-28 9:39
 */
public class Number1434 {

    // 参考：https://leetcode.cn/problems/number-of-ways-to-wear-different-hats-to-each-other/solution/mei-ge-ren-dai-bu-tong-mao-zi-de-fang-an-d4kd/
    public int numberWays(List<List<Integer>> hats) {

        final int MOD = 1000000007;
        int n = hats.size();
        // 找到帽子编号的最大值，这样只需要求出 f[maxHatId][2^n - 1] 作为答案
        int maxHatId = 0;
        for (int i = 0; i < n; ++i) {
            List<Integer> list = hats.get(i);
            for (int h: list)
                maxHatId = Math.max(maxHatId, h);
        }

        // 对于每一顶帽子 h，hatToPerson[h] 中存储了喜欢这顶帽子的所有人，方便进行动态规划
        List<List<Integer>> hatToPerson = new ArrayList<List<Integer>>();
        for (int i = 0; i <= maxHatId; i++)
            hatToPerson.add(new ArrayList<Integer>());

        for (int i = 0; i < n; ++i) {
            List<Integer> list = hats.get(i);
            for (int h: list)
                hatToPerson.get(h).add(i);
        }

        // 用 f[i][mask] 表示处理了前 i 顶帽子，并且已经被分配帽子的人的状态为 mask 时的方案数
        int[][] f = new int[maxHatId + 1][1 << n];
        // 边界条件
        f[0][0] = 1;
        for (int i = 1; i <= maxHatId; ++i) {
            for (int mask = 0; mask < (1 << n); ++mask) {
                f[i][mask] = f[i - 1][mask];
                List<Integer> list = hatToPerson.get(i);
                for (int j : list) {
                    if ((mask & (1 << j)) != 0) {
                        f[i][mask] += f[i - 1][mask ^ (1 << j)];
                        f[i][mask] %= MOD;
                    }
                }
            }
        }

        return f[maxHatId][(1 << n) - 1];
    }
}
