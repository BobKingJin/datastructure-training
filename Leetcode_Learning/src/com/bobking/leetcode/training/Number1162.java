package com.bobking.leetcode.training;

import java.util.ArrayDeque;
import java.util.Queue;

public class Number1162 {

    // 参考：https://leetcode-cn.com/problems/binary-tree-level-order-traversal/solution/bfs-de-shi-yong-chang-jing-zong-jie-ceng-xu-bian-l/
    public int maxDistance(int[][] grid) {

        int N = grid.length;

        Queue<int[]> queue = new ArrayDeque<int[]>();
        // 将所有的陆地格子加入队列
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 1)
                    queue.add(new int[]{i, j});
            }
        }

        // 如果地图上只有陆地或者海洋，返回 -1
        if (queue.isEmpty() || queue.size() == N * N)
            return -1;

        int[][] moves = {
                {-1, 0}, {1, 0}, {0, -1}, {0, 1}
        };

        // 记录当前遍历的层数（距离）
        int distance = -1;
        while (!queue.isEmpty()) {

            distance++;
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                int[] node = queue.poll();
                int r = node[0];
                int c = node[1];
                for (int[] move : moves) {
                    int r2 = r + move[0];
                    int c2 = c + move[1];
                    if (inArea(grid, r2, c2) && grid[r2][c2] == 0) {
                        grid[r2][c2] = 2;
                        queue.add(new int[]{r2, c2});
                    }
                }
            }
        }

        return distance;
    }

    // 判断坐标 (r, c) 是否在网格中
    private boolean inArea(int[][] grid, int r, int c) {
        return 0 <= r && r < grid.length
                && 0 <= c && c < grid[0].length;
    }
}
