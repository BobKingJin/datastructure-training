package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-07-30 10:29
 */
public class Number952 {

    private class UnionFind {

        private int[] parent;

        public UnionFind(int n) {
            parent = new int[n];
            // 初始化
            for (int i = 0; i < n; i++)
                parent[i] = i;
        }

        // 使用了路径压缩
        public int find(int x) {
            while (parent[x] != x) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        // 没有实现按秩合并
        public void union(int x, int y) {

            int rootX = find(x);
            int rootY = find(y);

            if (rootX != rootY)
                parent[rootX] = rootY;
        }
    }

    // 参考：https://leetcode.cn/problems/largest-component-size-by-common-factor/solution/bing-cha-ji-java-python-by-liweiwei1419/
    public int largestComponentSize(int[] nums) {

        int maxVal = 0;
        for (int num : nums)
            maxVal = Math.max(maxVal, num);

        // 0 位置不使用，因此需要 + 1
        UnionFind unionFind = new UnionFind(maxVal + 1);

        for (int num : nums) {
            double upBound = Math.sqrt(num);
            for (int i = 2; i <= upBound; i++) {
                if (num % i == 0) {
                    unionFind.union(num, i);
                    unionFind.union(num, num / i);
                }
            }
        }

        // 将候选数组映射成代表元，统计代表元出现的次数，找出最大者
        int[] cnt = new int[maxVal + 1];
        int res = 0;
        for (int num : nums) {
            int root = unionFind.find(num);
            cnt[root]++;
            res = Math.max(res, cnt[root]);
        }

        return res;
    }
}
