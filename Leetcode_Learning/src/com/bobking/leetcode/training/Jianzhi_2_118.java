package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-02-24 22:07
 */
public class Jianzhi_2_118 {

    // 参考：https://leetcode.cn/problems/7LpjUW/solution/duo-yu-de-bian-by-leetcode-solution-pnt2/
    public int[] findRedundantConnection(int[][] edges) {

        int n = edges.length;
        int[] parent = new int[n + 1];

        for (int i = 1; i <= n; i++)
            parent[i] = i;

        for (int i = 0; i < n; i++) {
            int[] edge = edges[i];
            int node1 = edge[0];
            int node2 = edge[1];
            if (find(parent, node1) != find(parent, node2)) {
                union(parent, node1, node2);
            } else {
                return edge;
            }
        }
        return new int[0];
    }

    private void union(int[] parent, int index1, int index2) {
        parent[find(parent, index1)] = find(parent, index2);
    }

    private int find(int[] parent, int index) {
        if (parent[index] != index)
            parent[index] = find(parent, parent[index]);
        return parent[index];
    }
}
