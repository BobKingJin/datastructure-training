package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-07-16 10:22
 */
public class Number685 {

    private class DSU{

        private int[] parents;
        private int[] size;
        // 连通分量的个数
        public int n;

        DSU(int n){
            this.n = n;
            size = new int[n];
            parents = new int[n];
            for(int i=0; i<n; i++){
                parents[i] = i;
                size[i] = 1;
            }
        }

        public int find(int x){
            if(parents[x]!=x)
                parents[x] = find(parents[x]);

            return parents[x];
        }

        public boolean union(int x, int y){

            int u = find(x);
            int v = find(y);

            if(u==v)
                return false;

            parents[u] = v;
            size[v] += size[u];
            n--;
            return true;
        }

        public boolean isConnected(int x, int y){
            return find(x)==find(y);
        }

        public int getSize(int x){
            return size[find(x)];
        }
    }

    // 参考：https://leetcode.cn/problems/redundant-connection-ii/solution/685-rong-yu-lian-jie-iibing-cha-ji-de-ying-yong-xi/
    public int[] findRedundantDirectedConnection(int[][] edges) {

        int n = edges.length;
        // indegrees[i] 表示入为第 i 个节点的 edge 在 edges 中的索引
        Integer[] indegrees = new Integer[n + 1];
        for (int i = 0; i < n; i++) {
            int[] e = edges[i];
            if (indegrees[e[1]] == null)
                indegrees[e[1]] = i;
            else {
                // 不为 null, 说明 e[1] 的入度为 2
                // 2 条边索引分别是 indegrees[e[1]] 和 i
                // 从后往前判断, 将其删除能否构成树(1 个连通分量)
                DSU dsu = new DSU(n + 1);
                dsu.union(0, 1);
                for (int j = 0; j < n; j++) {
                    if (j == i)
                        continue;
                    dsu.union(edges[j][0], edges[j][1]);
                }
                return dsu.n == 1 ? e : edges[indegrees[e[1]]];
            }
        }
        // 有环的情况, 如果连接 2 点之前就已经连通了, 说明当前边就是最后那个多余的
        DSU dsu1 = new DSU(n + 1);
        dsu1.union(0, 1);
        for (int i = 0; i < n; i++) {
            int[] e = edges[i];
            if (dsu1.isConnected(e[0], e[1]))
                return e;
            dsu1.union(e[0], e[1]);
        }
        return new int[2];
    }
}
