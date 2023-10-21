package com.bobking.leetcode.training;

import java.util.*;

/**
 * @author BobKing
 * @create 2023-10-21 10:03
 */
public class Number2316 {

    // 参考: https://leetcode.cn/problems/count-unreachable-pairs-of-nodes-in-an-undirected-graph/solutions/1625315/by-endlesscheng-7l50/?envType=daily-question&envId=2023-10-21
    public long countPairs1(int n, int[][] edges) {

        List<Integer>[] g = new ArrayList[n];

        Arrays.setAll(g, e -> new ArrayList<Integer>());

        for (int[] e : edges) {
            int x = e[0];
            int y = e[1];
            g[x].add(y);
            g[y].add(x);
        }

        boolean[] vis = new boolean[n];
        long ans = 0;

        for (int i = 0, total = 0; i < n; i++) {
            // 未访问的点: 说明找到了一个新的连通块
            if (!vis[i]) {
                // 连通块的大小为 size
                int size = dfs(i, g, vis);
                ans += (long) size * total;
                // total 维护前面求出的连通块的大小之和
                total += size;
            }
        }
        return ans;
    }

    private int dfs(int x, List<Integer>[] g, boolean[] vis) {

        // 避免重复访问同一个点
        vis[x] = true;
        int size = 1;

        for (int y : g[x]) {
            if (!vis[y])
                size += dfs(y, g, vis);
        }

        return size;
    }

    private class UF {
        private int count;
        private int[] parent;
        private int[] size;
        public UF(int n) {
            this.count = n;
            this.parent = new int[n];
            this.size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }
        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ)
                return ;

            if (size[rootP] < size[rootQ]) {
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
            } else {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            }
            this.count--;
        }
        public boolean connected(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            return rootP == rootQ;
        }
        public int count() {
            return this.count;
        }
        // 增加了一个函数
        // 返回 size[]
        public int[] size() {
            return this.size;
        }
        public int find(int x) {
            // 路径压缩
            if (parent[x] != x)
                parent[x] = find(parent[x]);

            return parent[x];
        }
    }

    public long countPairs2(int n, int[][] edges) {

        UF uf = new UF(n);
        for (int[] edge : edges)
            uf.union(edge[0], edge[1]);

        int[] size = uf.size();
        // 记录所有分支的大小
        List<Integer> list = new ArrayList<Integer>();
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < n; i++) {
            // 找到节点 i 的根节点
            // 注意：只有每个连通分量的根节点的 size[] 才可以代表该连通分量中的节点数
            int p = uf.find(i);
            // 已经加入 list 的节点直接跳过
            if (!set.contains(p))
                list.add(size[p]);
            set.add(p);
        }
        long ans = 0;
        for (int sz : list)
            ans += (long) sz * (n - sz);
        return ans / 2;
    }
}

