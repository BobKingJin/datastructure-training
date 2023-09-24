package com.bobking.leetcode.training;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author BobKing
 * @create 2022-09-24 11:23
 */
public class Number1091 {

    // 参考：https://leetcode.cn/problems/shortest-path-in-binary-matrix/solution/bfszui-duan-lu-jing-wen-ti-bfsdfsde-si-k-ngc5/
    public int shortestPathBinaryMatrix(int[][] grid) {

        // 在二维矩阵中搜索，什么时候用 BFS，什么时候用 DFS？
        // 1.如果只是要找到某一个结果是否存在，那么 DFS 会更高效
        // 因为 DFS 会首先把一种可能的情况尝试到底，才会回溯去尝试下一种情况，只要找到一种情况，就可以返回了
        // 但是 BFS 必须所有可能的情况同时尝试，在找到一种满足条件的结果的同时，也尝试了很多不必要的路径
        // 2.如果是要找所有可能结果中最短的，那么 BFS 会更高效。因为 DFS 是一种一种的尝试，在把所有可能情况尝试完之前，无法确定哪个是最短
        // 所以 DFS 必须把所有情况都找一遍，才能确定最终答案（DFS的优化就是剪枝，不剪枝很容易超时）
        // 而 BFS 从一开始就是尝试所有情况，所以只要找到第一个达到的那个点，那就是最短的路径，可以直接返回了，其他情况都可以省略了，所以这种情况下，BFS 更高效


        int[][] dir = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, 1}, {1, -1}};
        int m = grid.length;
        int n = m;
        boolean[][] vis = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<int[]>();
        int dis = 1;

        if (grid[0][0] != 0)
            return -1;

        if (m == 1)
            return 1;

        queue.offer(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int c = 0; c < size; ++c) {
                int[] poll = queue.poll();
                int i = poll[0];
                int j = poll[1];
                for (int k = 0; k < 8; ++k) {
                    int ni = i + dir[k][0];
                    int nj = j + dir[k][1];
                    if (ni >= 0 && ni < m && nj >= 0 && nj < n && grid[ni][nj] == 0 && !vis[ni][nj]) {

                        if (ni == m - 1 && nj == n - 1)
                            return dis + 1;

                        queue.offer(new int[]{ni, nj});
                        vis[ni][nj] = true;
                    }
                }
            }
            // 这一圈都加 1
            dis += 1;
        }

        return -1;
    }
}
