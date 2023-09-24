package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-04-02 20:07
 */
public class Number1319 {

    int count;

    // 参考：https://leetcode.cn/problems/number-of-operations-to-make-network-connected/solution/wang-luo-lian-jie-bing-cha-ji-by-yexiso-1nd4/
    public int makeConnected(int n, int[][] connections) {

        if (connections.length < n - 1)
            return -1;
        int size[] = new int[n];
        int parent[] = new int[n];
        this.count = n;

        for (int i = 0; i < n; i++) {
            size[i] = 1;
            parent[i] = i;
        }

        for (int[] connection : connections) {
            int x = connection[0];
            int y = connection[1];
            union(x, y, parent, size);
        }
        return --count;
    }

    private void union(int x, int y, int parent[], int size[]) {
        int fx = find(x, parent);
        int fy = find(y, parent);
        if (fx == fy) {
            return;
        } else {
            if (size[fx] >= size[fy]) {
                parent[fy] = fx;
                size[fx] += size[fy];
            } else {
                parent[fx] = fy;
                size[fy] += size[fx];
            }
            count--;
        }
    }

    private int find(int x, int parent[]) {
        if (parent[x] != x) {
            int father = find(parent[x], parent);
            parent[x] = father;
        }
        return parent[x];
    }
}
