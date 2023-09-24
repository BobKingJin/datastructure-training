package com.bobking.leetcode.training;

import java.util.*;

/**
 * @author BobKing
 * @create 2023-02-02 10:05
 */
public class Number1129 {

    // 参考：https://leetcode.cn/problems/shortest-path-with-alternating-colors/solution/by-bai-mu-ying-li-dra-eniac-jehj/
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {

        List<List<int[]>> list = new ArrayList<List<int[]>>();
        for (int i = 0; i < n; i++)
            list.add(new ArrayList<int[]>());

        for (int[] a : redEdges) {
            int num1 = a[0];
            int num2 = a[1];
            // num1 红色指向 num2
            list.get(num1).add(new int[]{num2, 0});
        }
        for (int[] b : blueEdges) {
            int num1 = b[0];
            int num2 = b[1];
            // num1蓝色指向num2
            list.get(num1).add(new int[]{num2, 1});
        }
        // 0 : r 1 : b
        boolean[][] visited = new boolean[n][2];
        Queue<int[]> queue = new LinkedList<int[]>();
        // node, distance, color, 由什么color, 经过多少 distance, 到达node
        queue.offer(new int[]{0, 0, 0});
        queue.offer(new int[]{0, 0, 1});
        visited[0][0] = true;
        visited[0][1] = true;
        int[] res = new int[n];
        Arrays.fill(res, Integer.MAX_VALUE);
        res[0] = 0;
        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int node = arr[0], distance = arr[1], col = arr[2];
            for (int[] to : list.get(node)) {
                int toNode = to[0];
                int toCol = to[1];
                if (col != toCol && !visited[toNode][toCol]) {
                    // col != tocol保证到达前个节点与到达当前节点颜色不同，即红蓝交替; !visited[toNode][toCol]保证未以该颜色到达该节点
                    res[toNode] = Math.min(res[toNode], distance + 1);
                    queue.offer(new int[]{toNode, distance + 1, toCol});
                    visited[toNode][toCol] = true;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (res[i] == Integer.MAX_VALUE)
                res[i] = -1;
        }
        return res;
    }
}
