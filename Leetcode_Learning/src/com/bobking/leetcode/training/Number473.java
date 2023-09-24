package com.bobking.leetcode.training;

import java.util.Arrays;

public class Number473 {

    int[] ms;
    int t;

    // 参考：https://leetcode.cn/problems/matchsticks-to-square/solution/by-ac_oier-k8i7/
    public boolean makesquare(int[] matchsticks) {

        ms = matchsticks;
        int sum = 0;

        for (int i : ms)
            sum += i;

        t = sum / 4;
        if (t * 4 != sum)
            return false;

        Arrays.sort(ms);
        // 从 大 -> 小
        return dfs(ms.length - 1, new int[4]);
    }

    private boolean dfs(int idx, int[] cur) {

        // 考虑如何进行「剪枝」
        // 首先一个较为明显的剪枝操作是进行「可行性剪枝」：在决策 matchsticks[idx] 时，如果将其累加到某个 cur[i] 之后
        // 会导致 cur[i] > t，则说明必然不会是合法方案，该分支不再往后搜索
        // 另外一个较为 trick 的剪枝是通过「调整搜索顺序/检查当前桶的总长度」来进行「重复性剪枝」
        // 可以先对 ms 排倒序，进行「从大到小」的暴搜。本质上，是将一些小火柴重复放到某几个桶的搜索路径（其实对应的是相同的分配方案）放到了最后处理
        // 同时，当要放置 matchsticks[idx] 的火柴时，如果存在多个桶总长度相等，例如 cur[i] = cur[j]
        // 将 matchsticks[idx] 放置在 cur[i] 或 cur[j] 也是等价的

        if (idx == -1)
            return true;

        out:
        for (int i = 0; i < 4; i++) {
            // 注意理解这个位置的剪枝
            for (int j = 0; j < i; j++) {
                if (cur[j] == cur[i])
                    continue out;
            }
            int u = ms[idx];
            if (cur[i] + u > t)
                continue;
            cur[i] += u;
            if (dfs(idx - 1, cur))
                return true;
            // 回溯
            cur[i] -= u;
        }

        return false;
    }
}
