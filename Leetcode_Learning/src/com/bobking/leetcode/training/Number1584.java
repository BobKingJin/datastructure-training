package com.bobking.leetcode.training;

import java.util.*;

/**
 * @author BobKing
 * @create 2022-09-10 10:43
 */
public class Number1584 {

    private class DisjointSetUnion {

        int[] f;
        int[] rank;
        int n;

        public DisjointSetUnion(int n) {
            this.n = n;
            this.rank = new int[n];
            Arrays.fill(this.rank, 1);
            this.f = new int[n];
            for (int i = 0; i < n; i++)
                this.f[i] = i;
        }

        public int find(int x) {
            return f[x] == x ? x : (f[x] = find(f[x]));
        }

        public boolean unionSet(int x, int y) {

            int fx = find(x);
            int fy = find(y);
            if (fx == fy)
                return false;

            if (rank[fx] < rank[fy]) {
                int temp = fx;
                fx = fy;
                fy = temp;
            }

            rank[fx] += rank[fy];
            f[fy] = fx;
            return true;
        }
    }

    private class Edge {

        int len;
        int x;
        int y;

        public Edge(int len, int x, int y) {
            this.len = len;
            this.x = x;
            this.y = y;
        }
    }

    // 参考：https://leetcode.cn/problems/min-cost-to-connect-all-points/solution/lian-jie-suo-you-dian-de-zui-xiao-fei-yo-kcx7/
    // 参考：https://leetcode.cn/problems/min-cost-to-connect-all-points/solution/prim-and-kruskal-by-yexiso-c500/
    public int minCostConnectPoints(int[][] points) {

        // 该算法的基本思想是从小到大加入边，是一个贪心算法

        int n = points.length;
        DisjointSetUnion dsu = new DisjointSetUnion(n);
        List<Edge> edges = new ArrayList<Edge>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++)
                edges.add(new Edge(dist(points, i, j), i, j));
        }

        Collections.sort(edges, new Comparator<Edge>() {
            public int compare(Edge edge1, Edge edge2) {
                return edge1.len - edge2.len;
            }
        });

        int res = 0;
        int num = 1;

        for (Edge edge : edges) {
            int len = edge.len;
            int x = edge.x;
            int y = edge.y;
            if (dsu.unionSet(x, y)) {
                res += len;
                num++;
                if (num == n)
                    break;
            }
        }

        return res;
    }

    private int dist(int[][] points, int x, int y) {
        return Math.abs(points[x][0] - points[y][0]) + Math.abs(points[x][1] - points[y][1]);
    }
}
