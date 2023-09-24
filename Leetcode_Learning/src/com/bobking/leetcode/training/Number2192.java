package com.bobking.leetcode.training;

import java.util.*;

/**
 * @author BobKing
 * @create 2022-10-30 15:00
 */
public class Number2192 {

    // 参考：https://leetcode.cn/problems/all-ancestors-of-a-node-in-a-directed-acyclic-graph/solution/java-tuo-bu-pai-xu-jian-dan-si-lu-by-rel-95yw/
    public List<List<Integer>> getAncestors(int n, int[][] edges) {

        // 构建两个线性表，一个作为返回值，另一个使用 TreeSet，可以在去重的同时进行元素的排序
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Set<Integer>> demo = new ArrayList<Set<Integer>>();

        // 构建邻接矩阵(邻接表会更好)，以及入度数组
        int[] system = new int[n];
        int[][] grid = new int[n][n];

        // 遍历edges，分别构建图中的边的关系以及各个节点的入度
        for (int[] edge : edges) {
            system[edge[1]]++;
            grid[edge[0]][edge[1]] = 1;
        }

        // 将图中入度为 0 的节点入队列，同时填充好最初构建的两个线性表
        Queue<Integer> queue = new LinkedList<Integer>();

        for (int i = 0; i < n; i++) {
            if (system[i] == 0) {
                queue.offer(i);
                system[i]--;
            }
            ans.add(new ArrayList<Integer>());
            demo.add(new TreeSet<Integer>());
        }

        // 分别对整个入度数组进行遍历，若入度为 0，则加入队列，同时将该父节点以及该父节点的所有父节点加入线性表中
        while (!queue.isEmpty()) {

            int size = queue.size();

            while (size-- > 0) {
                int idx = queue.poll();
                for (int i = 0; i < n; i++) {
                    if (grid[idx][i] == 1) {
                        system[i]--;
                        demo.get(i).add(idx);
                        demo.get(i).addAll(demo.get(idx));
                    }
                    if (system[i] == 0) {
                        queue.offer(i);
                        system[i]--;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++)
            ans.get(i).addAll(demo.get(i));

        return ans;
    }
}
