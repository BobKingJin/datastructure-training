package com.bobking.leetcode.training;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author BobKing
 * @create 2023-05-19 7:21
 */
public class Number1765 {

    // 参考：https://leetcode.cn/problems/map-of-highest-peak/solution/gong-shui-san-xie-duo-yuan-bfs-yun-yong-8sw0f/
    public int[][] highestPeak(int[][] isWater) {

        int m = isWater.length;
        int n = isWater[0].length;
        int[][] ans = new int[m][n];

        Deque<int[]> d = new ArrayDeque<int[]>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isWater[i][j] == 1)
                    d.addLast(new int[]{i, j});
                ans[i][j] = isWater[i][j] == 1 ? 0 : -1;
            }
        }

        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while (!d.isEmpty()) {

            int[] info = d.pollFirst();
            int x = info[0];
            int y = info[1];
            for (int[] di : dirs) {
                int nx = x + di[0];
                int ny = y + di[1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n)
                    continue;
                if (ans[nx][ny] != -1)
                    continue;
                ans[nx][ny] = ans[x][y] + 1;
                d.addLast(new int[]{nx, ny});
            }
        }
        return ans;
    }
}
