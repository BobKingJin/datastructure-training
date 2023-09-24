package com.bobking.leetcode.training;

public class Number684 {

    public int[] findRedundantConnection(int[][] edges) {

        // 有题目可知边的个数为 N，元素在 1 - N 之间
        int N = edges.length;
        // 创建一个领导表，表示每个节点的上级领导
        int[] leader = new int[N + 1];
        int[] res = new int[2];
        // 初始化领导表：每个节点的上级领导是自身（此时每个节点都是掌门）
        for (int i = 0; i <= N; i++)
            leader[i] = i;

        // 遍历每一条边 ： 将边上的两个节点所在门派合并
        for (int[] edge : edges) {
            // 左节点的掌门 root0
            int root0 = findRoot(edge[0], leader);
            // 右节点的掌门 root1
            int root1 = findRoot(edge[1], leader);
            // 两个掌门不一样，则合并
            if (root0 != root1) {
                // 谁是新掌门无关紧要，选一个就行
                leader[root0] = root1;
            } else {  // 左右节点的掌门相同，这条边就是冗余的，无需合并
                return edge;
            }
        }
        return res;
    }

    // 找某个节点的掌门
    private int findRoot(int son, int[] leader) {

        int root = son;

        // 只有掌门的领导是掌门自己
        while (root != leader[root])
            root = leader[root];

        return root;
    }
}
