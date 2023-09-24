package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.Map;

public class Number403 {

    Map<Integer, Integer> map1 = new HashMap<>();

    // 参考：https://leetcode-cn.com/problems/frog-jump/solution/gong-shui-san-xie-yi-ti-duo-jie-jiang-di-74fw/
    public boolean canCrostones1(int[] stones) {

        int n = stones.length;
        // 将石子信息存入哈希表
        // 为了快速判断是否存在某块石子，以及快速查找某块石子所在下标
        for (int i = 0; i < n; i++)
            map1.put(stones[i], i);

        // 根据题意，第一步是固定经过步长 1 到达第一块石子（下标为 1）
        if (!map1.containsKey(1))
            return false;

        return dfs1(stones, stones.length, 1, 1);
    }

    // 判定是否能够跳到最后一块石子
    // index  当前所在的石子的下标
    // k  上一次是经过多少步跳到当前位置的
    private boolean dfs1(int[] stones, int length, int index, int k) {

        if (index == length - 1)
            return true;

        for (int i = -1; i <= 1; i++) {
            // 如果是原地踏步的话，直接跳过
            if (k + i == 0)
                continue;
            // 下一步的石子理论编号
            int next = stones[index] + k + i;
            // 如果存在下一步的石子，则跳转到下一步石子
            if (map1.containsKey(next)) {
                boolean cur = dfs1(stones, length, map1.get(next), k + i);
                if (cur)
                    return true;
            }
        }

        return false;
    }

    Map<Integer, Integer> map2 = new HashMap<Integer, Integer>();
    Map<String, Boolean> cache = new HashMap<String, Boolean>();

    // 参考：https://leetcode-cn.com/problems/frog-jump/solution/gong-shui-san-xie-yi-ti-duo-jie-jiang-di-74fw/
    public boolean canCross2(int[] stones) {

        int n = stones.length;
        for (int i = 0; i < n; i++)
            map2.put(stones[i], i);

        if (!map2.containsKey(1))
            return false;

        return dfs2(stones, stones.length, 1, 1);
    }

    private boolean dfs2(int[] stones, int length, int index, int k) {

        String key = index + "_" + k;

        if (cache.containsKey(key))
            return cache.get(key);

        if (index == length - 1)
            return true;

        for (int i = -1; i <= 1; i++) {
            if (k + i == 0)
                continue;
            int next = stones[index] + k + i;
            if (map2.containsKey(next)) {
                boolean cur = dfs2(stones, length, map2.get(next), k + i);
                cache.put(key, cur);
                if (cur)
                    return true;
            }
        }

        cache.put(key, false);
        return false;
    }

    // 参考：https://leetcode-cn.com/problems/frog-jump/solution/gong-shui-san-xie-yi-ti-duo-jie-jiang-di-74fw/
    public boolean canCross3(int[] stones) {

        int n = stones.length;

        if (stones[1] != 1)
            return false;
        // f[i][k]：当前在第 i 个位置，并且是以步长 k 跳到位置 i 时，是否到达最后一块石子
        boolean[][] f = new boolean[n + 1][n + 1];
        f[1][1] = true;
        for (int i = 2; i < n; i++) {
            for (int j = 1; j < i; j++) {
                int k = stones[i] - stones[j];
                // 从位置 j 到位置 i 是需要步长为 k 的跳跃
                // 而从位置 j 发起的跳跃最多不超过 j + 1
                // 因为每次跳跃，下标至少增加 1，而步长最多增加 1
                if (k <= j + 1) {
                    f[i][k] = f[j][k - 1] || f[j][k] || f[j][k + 1];
                }
            }
        }

        for (int i = 1; i < n; i++)
            if (f[n - 1][i])
                return true;

        return false;
    }

}

