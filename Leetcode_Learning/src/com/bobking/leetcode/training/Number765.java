package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2021-07-04 15:15
 */
public class Number765 {

    // 并查集参考：程序猿代码指南P476
    // 参考：https://leetcode-cn.com/problems/couples-holding-hands/solution/qing-lu-qian-shou-by-leetcode-gl1c/
    public int minSwapsCouples(int[] row) {

        if (row == null || row.length < 2)
            return 0;

        int len = row.length;
        int N = len / 2;

        UnionFind unionFind = new UnionFind(N);
        for (int i = 0; i < len; i += 2)
            unionFind.union(row[i] / 2, row[i + 1] / 2);

        return N - unionFind.getCount();
    }

    private class UnionFind {

        private int[] parent;

        // 并查集中连同分量的个数
        private int count;

        public int getCount() {
            return count;
        }

        // 初始化
        public UnionFind(int n) {

            this.count = n;
            this.parent = new int[n];
            for (int i = 0; i < n; i++)
                parent[i] = i;
        }

        public int find(int x) {

            while (x != parent[x]) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        public void union(int x, int y) {

            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY)
                return;

            parent[rootX] = rootY;
            count--;
        }
    }

}
