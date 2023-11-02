package com.bobking.leetcode.training;

import java.util.*;

/**
 * @author BobKing
 * @create 2023-11-01 8:21
 */
public class Number2127 {

    // 参考: https://leetcode.cn/problems/maximum-employees-to-be-invited-to-a-meeting/solutions/1187830/nei-xiang-ji-huan-shu-tuo-bu-pai-xu-fen-c1i1b/?envType=daily-question&envId=2023-11-01
    public int maximumInvitations(int[] favorite) {

        int n = favorite.length;

        int[] deg = new int[n];

        for (int f : favorite)
            // 统计基环树每个节点的入度
            deg[f]++;
        // 反图
        List<Integer>[] rg = new List[n];
        Arrays.setAll(rg, e -> new ArrayList<Integer>());
        Deque<Integer> q = new ArrayDeque<Integer>();

        for (int i = 0; i < n; i++) {
            if (deg[i] == 0)
                q.add(i);
        }
        // 拓扑排序，剪掉图上所有树枝
        while (!q.isEmpty()) {
            int x = q.poll();
            // x 只有一条出边
            int y = favorite[x];
            rg[y].add(x);
            if (--deg[y] == 0)
                q.add(y);
        }

        int maxRingSize = 0;
        int sumChainSize = 0;

        for (int i = 0; i < n; i++) {

            if (deg[i] == 0)
                continue;
            // 遍历基环上的点
            // 将基环上的点的入度标记为 0，避免重复访问
            deg[i] = 0;
            // 基环长度
            int ringSize = 1;

            for (int x = favorite[i]; x != i; x = favorite[x]) {
                // 将基环上的点的入度标记为 0，避免重复访问
                deg[x] = 0;
                ringSize++;
            }
            // 基环长度为 2
            if (ringSize == 2) {
                // 累加两条最长链的长度
                sumChainSize += rdfs(i, rg) + rdfs(favorite[i], rg);
            } else {
                // 取所有基环长度的最大值
                maxRingSize = Math.max(maxRingSize, ringSize);
            }
        }
        return Math.max(maxRingSize, sumChainSize);
    }

    // 通过反图 rg 寻找树枝上最深的链
    private int rdfs(int x, List<Integer>[] rg) {

        int maxDepth = 1;

        for (int son : rg[x])
            maxDepth = Math.max(maxDepth, rdfs(son, rg) + 1);

        return maxDepth;
    }

}
